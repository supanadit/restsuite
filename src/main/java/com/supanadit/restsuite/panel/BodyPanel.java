package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.BodyTextArea;
import com.supanadit.restsuite.component.RequestBodyRawTypeComboBox;
import com.supanadit.restsuite.component.RequestBodyTypeComboBox;
import com.supanadit.restsuite.listener.BodyTextListener;
import com.supanadit.restsuite.model.BodySubjectModel;
import com.supanadit.restsuite.model.RequestBodyRawType;
import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {
    protected BodyTextArea bodyTextArea;
    protected boolean withOptions;
    protected String defaultFormat = SyntaxConstants.SYNTAX_STYLE_NONE;
    protected boolean raw = true;
    protected RTextScrollPane spBody;
    protected BodyFormPanel bodyFormPanel;
    protected RequestBodyTypeComboBox requestBodyTypeComboBox;
    protected RequestBodyRawTypeComboBox requestBodyRawTypeComboBox;

    final protected BehaviorSubject<Boolean> subject = BehaviorSubject.create();
    protected BodySubjectModel bodySubjectModel;

    public BodyPanel(boolean withOptions, BodySubjectModel bodySubjectModel) {
        super(new MigLayout());
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("TableHeader.foreground");

        this.bodySubjectModel = bodySubjectModel;
        this.withOptions = withOptions;

        bodyTextArea = new BodyTextArea();

        if (bodySubjectModel != null) {
            bodyTextArea.getDocument().addDocumentListener(new BodyTextListener(bodyTextArea, bodySubjectModel.getBodyRaw()));
        }

        spBody = new RTextScrollPane(bodyTextArea);
        Gutter gutter = spBody.getGutter();
        gutter.setBorderColor(lineColor);
        gutter.setLineNumberColor(fontColor);

        bodyFormPanel = new BodyFormPanel(bodySubjectModel);

        if (withOptions) {
            requestBodyTypeComboBox = RequestBodyTypeComboBox.getComponent();
            requestBodyRawTypeComboBox = RequestBodyRawTypeComboBox.getComponent();

            if (requestBodyRawTypeComboBox.getItemCount() != 0) {
                RequestBodyRawType requestBodyRawType = requestBodyRawTypeComboBox.getItemAt(0);
                bodyTextArea.setSyntaxEditingStyle(requestBodyRawType.getSyntax());
            }

            requestBodyTypeComboBox.addActionListener((e) -> {
                RequestBodyType requestBodyType = (RequestBodyType) requestBodyTypeComboBox.getSelectedItem();
                if (requestBodyType != null && bodySubjectModel != null) {
                    bodySubjectModel.getRequestBodyTypeSubject().onNext(requestBodyType);
                }
                assert requestBodyType != null;
                subject.onNext(requestBodyType.getName().equals(RequestBodyType.RAW().getName()));
            });

            Disposable disposable = subject.subscribe((e) -> {
                if (e) {
                    requestBodyRawTypeComboBox.setEnabled(true);
                    raw = true;
                } else {
                    requestBodyRawTypeComboBox.setEnabled(false);
                    requestBodyRawTypeComboBox.setSelectedIndex(0);
                    raw = false;
                }
                if (raw) {
                    add(spBody, "grow, push, span 3");
                    remove(bodyFormPanel);
                } else {
                    remove(spBody);
                    add(bodyFormPanel, "grow, push, span 3");
                }
                updateUI();
            });

            requestBodyRawTypeComboBox.addActionListener((e) -> {
                RequestBodyRawType requestBodyRawType = (RequestBodyRawType) requestBodyRawTypeComboBox.getSelectedItem();
                if (bodySubjectModel != null && requestBodyRawType != null) {
                    bodySubjectModel.getRequestBodyRawTypeSubject().onNext(requestBodyRawType);
                }
                assert requestBodyRawType != null;
                bodyTextArea.setSyntaxEditingStyle(requestBodyRawType.getSyntax());
            });

            add(requestBodyTypeComboBox);
            add(requestBodyRawTypeComboBox, "wrap");
        } else {
            bodyTextArea.setSyntaxEditingStyle(defaultFormat);
        }
        add(spBody, "grow, push, span 3");
    }

    public void setSyntax(String value) {
        bodyTextArea.setSyntaxEditingStyle(value);
    }

    public void setText(String text) {
        bodyTextArea.setText(text);
    }
}
