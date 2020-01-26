package com.supanadit.restsuite.panel.api.response;

import com.supanadit.restsuite.component.combobox.RequestBodyRawTypeComboBox;
import com.supanadit.restsuite.component.combobox.RequestBodyTypeComboBox;
import com.supanadit.restsuite.component.textarea.BodyTextArea;
import com.supanadit.restsuite.model.RequestBodyRawTypeModel;
import com.supanadit.restsuite.model.RequestBodyTypeModel;
import com.supanadit.restsuite.panel.api.request.tab.body.BodyFormPanel;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class ResponseBodyPanel extends JPanel {
    protected BodyTextArea bodyTextArea;
    protected boolean withOptions;
    protected String defaultFormat = SyntaxConstants.SYNTAX_STYLE_NONE;
    protected RTextScrollPane spBody;
    protected BodyFormPanel bodyFormPanel;

    protected RequestBodyTypeComboBox requestBodyTypeComboBox;
    protected RequestBodyRawTypeComboBox requestBodyRawTypeComboBox;

    public ResponseBodyPanel(boolean withOptions) {
        super(new MigLayout());
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("TableHeader.foreground");

        this.withOptions = withOptions;

        bodyTextArea = new BodyTextArea();

        spBody = new RTextScrollPane(bodyTextArea);
        Gutter gutter = spBody.getGutter();
        gutter.setBorderColor(lineColor);
        gutter.setLineNumberColor(fontColor);

        bodyFormPanel = new BodyFormPanel();

        if (withOptions) {
            requestBodyTypeComboBox = RequestBodyTypeComboBox.getComponent();
            requestBodyRawTypeComboBox = RequestBodyRawTypeComboBox.getComponent();

            if (requestBodyRawTypeComboBox.getItemCount() != 0) {
                RequestBodyRawTypeModel requestBodyRawTypeModel = requestBodyRawTypeComboBox.getItemAt(0);
                bodyTextArea.setSyntaxEditingStyle(requestBodyRawTypeModel.getSyntax());
            }

            requestBodyTypeComboBox.addActionListener((e) -> {
                RequestBodyTypeModel requestBodyTypeModel = (RequestBodyTypeModel) requestBodyTypeComboBox.getSelectedItem();
                assert requestBodyTypeModel != null;
                if (requestBodyTypeModel.getName().equals(RequestBodyTypeModel.RAW().getName())) {
                    requestBodyRawTypeComboBox.setEnabled(true);
                    add(spBody, "grow, push, span 3");
                    remove(bodyFormPanel);
                } else {
                    requestBodyRawTypeComboBox.setEnabled(false);
                    requestBodyRawTypeComboBox.setSelectedIndex(0);
                    remove(spBody);
                    add(bodyFormPanel, "grow, push, span 3");
                }
                updateUI();
            });

            requestBodyRawTypeComboBox.addActionListener((e) -> {
                RequestBodyRawTypeModel requestBodyRawTypeModel = (RequestBodyRawTypeModel) requestBodyRawTypeComboBox.getSelectedItem();
                assert requestBodyRawTypeModel != null;
                bodyTextArea.setSyntaxEditingStyle(requestBodyRawTypeModel.getSyntax());
            });

            add(requestBodyTypeComboBox);
            add(requestBodyRawTypeComboBox, "wrap");
        } else {
            bodyTextArea.setSyntaxEditingStyle(defaultFormat);
        }
        add(spBody, "grow, push, span 3");
    }

    public BodyFormPanel getBodyFormPanel() {
        return bodyFormPanel;
    }

    public RequestBodyTypeModel getRequestBodyType() {
        return (RequestBodyTypeModel) requestBodyTypeComboBox.getSelectedItem();
    }

    public RequestBodyRawTypeModel getRequestBodyRawType() {
        return (RequestBodyRawTypeModel) requestBodyRawTypeComboBox.getSelectedItem();
    }

    public String getRequestBodyRawValue() {
        return bodyTextArea.getText();
    }

    public void setSyntax(String value) {
        bodyTextArea.setSyntaxEditingStyle(value);
    }

    public void setText(String text) {
        bodyTextArea.setText(text);
    }
}
