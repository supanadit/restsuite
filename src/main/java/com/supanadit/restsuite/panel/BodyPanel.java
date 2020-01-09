package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.BodyTextArea;
import com.supanadit.restsuite.component.RequestBodyRawTypeComboBox;
import com.supanadit.restsuite.component.RequestBodyTypeComboBox;
import com.supanadit.restsuite.component.RequestTable;
import com.supanadit.restsuite.listener.BodyTextListener;
import com.supanadit.restsuite.model.RequestBodyRawType;
import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
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
    protected RequestTable requestTable;
    protected RequestBodyTypeComboBox requestBodyTypeComboBox;
    protected RequestBodyRawTypeComboBox requestBodyRawTypeComboBox;

    final protected BehaviorSubject<Boolean> subject = BehaviorSubject.create();
    protected PublishSubject<RequestBodyRawType> requestBodyRawTypeSubject;
    protected PublishSubject<RequestBodyType> requestBodyTypeSubject;

    public BodyPanel(boolean withOptions, PublishSubject<String> bodySubject, PublishSubject<RequestBodyRawType> requestBodyRawTypeSubject, PublishSubject<RequestBodyType> requestBodyTypeSubject) {
        super(new MigLayout());

        this.withOptions = withOptions;
        this.bodyTextArea = new BodyTextArea();
        this.requestBodyRawTypeSubject = requestBodyRawTypeSubject;
        this.requestBodyTypeSubject = requestBodyTypeSubject;

        if (bodySubject != null) {
            this.bodyTextArea.getDocument().addDocumentListener(new BodyTextListener(this.bodyTextArea, bodySubject));
        }

        spBody = new RTextScrollPane(this.bodyTextArea);
        Gutter gutter = spBody.getGutter();

        Color lineColor = UIManager.getColor("Table.gridColor");

        gutter.setBorderColor(lineColor);
        Color fontColor = UIManager.getColor("TableHeader.foreground");
        gutter.setLineNumberColor(fontColor);

        requestTable = new RequestTable();

        if (this.withOptions) {
            requestBodyTypeComboBox = RequestBodyTypeComboBox.getComponent();
            requestBodyRawTypeComboBox = RequestBodyRawTypeComboBox.getComponent();

            if (requestBodyRawTypeComboBox.getItemCount() != 0) {
                RequestBodyRawType requestBodyRawType = requestBodyRawTypeComboBox.getItemAt(0);
                this.bodyTextArea.setSyntaxEditingStyle(requestBodyRawType.getSyntax());
            }

            requestBodyTypeComboBox.addActionListener((e) -> {
                RequestBodyType requestBodyType = (RequestBodyType) requestBodyTypeComboBox.getSelectedItem();
                if (requestBodyType != null) {
                    this.requestBodyTypeSubject.onNext(requestBodyType);
                }
                assert requestBodyType != null;
                subject.onNext(requestBodyType.getName().equals(RequestBodyType.RAW().getName()));
            });

            Disposable disposable = subject.subscribe((e) -> {
                if (e) {
                    requestBodyRawTypeComboBox.setEnabled(true);
                    this.raw = true;
                } else {
                    requestBodyRawTypeComboBox.setEnabled(false);
                    requestBodyRawTypeComboBox.setSelectedIndex(0);
                    this.raw = false;
                }
                if (this.raw) {
                    this.add(spBody, "grow, push, span 3");
                    this.remove(requestTable);
                } else {
                    this.remove(spBody);
                    this.add(requestTable, "grow, push, span 3");
                }
                this.updateUI();
            });

            requestBodyRawTypeComboBox.addActionListener((e) -> {
                RequestBodyRawType requestBodyRawType = (RequestBodyRawType) requestBodyRawTypeComboBox.getSelectedItem();
                if (requestBodyRawTypeSubject != null) {
                    assert requestBodyRawType != null;
                    this.requestBodyRawTypeSubject.onNext(requestBodyRawType);
                }
                assert requestBodyRawType != null;
                this.bodyTextArea.setSyntaxEditingStyle(requestBodyRawType.getSyntax());
            });

            this.add(requestBodyTypeComboBox);
            this.add(requestBodyRawTypeComboBox, "wrap");
        } else {
            this.bodyTextArea.setSyntaxEditingStyle(this.defaultFormat);
        }
        this.add(spBody, "grow, push, span 3");
    }

    public void setSyntax(String value) {
        this.bodyTextArea.setSyntaxEditingStyle(value);
    }

    public void setText(String text) {
        this.bodyTextArea.setText(text);
    }
}
