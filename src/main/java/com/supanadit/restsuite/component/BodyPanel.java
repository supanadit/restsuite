package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestBodyRawType;
import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.subjects.BehaviorSubject;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;

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

    public BodyPanel(boolean withOptions) {
        super(new MigLayout());

        this.withOptions = withOptions;
        this.bodyTextArea = new BodyTextArea();

        spBody = new RTextScrollPane(this.bodyTextArea);
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
                subject.onNext(requestBodyType.getName().equals(RequestBodyType.RAW().getName()));
            });

            subject.subscribe((e) -> {
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
                this.bodyTextArea.setSyntaxEditingStyle(requestBodyRawType.getSyntax());
            });

            this.add(requestBodyTypeComboBox);
            this.add(requestBodyRawTypeComboBox, "wrap");
        } else {
            this.bodyTextArea.setSyntaxEditingStyle(this.defaultFormat);
        }
        this.add(spBody, "grow, push, span 3");
    }

    public void setText(String text) {
        this.bodyTextArea.setText(text);
    }
}
