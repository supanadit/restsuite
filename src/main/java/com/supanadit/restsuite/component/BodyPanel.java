package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.subjects.BehaviorSubject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class BodyPanel extends JPanel {
    protected BodyTextArea bodyTextArea;
    protected boolean withOptions;

    final protected BehaviorSubject<Boolean> subject = BehaviorSubject.create();

    public BodyPanel(boolean withOptions) {
        super(new MigLayout());

        this.withOptions = withOptions;
        this.bodyTextArea = new BodyTextArea();

        if (this.withOptions) {
            RequestBodyTypeComboBox requestBodyTypeComboBox = RequestBodyTypeComboBox.getComponent();
            RequestBodyRawTypeComboBox requestBodyRawTypeComboBox = RequestBodyRawTypeComboBox.getComponent();

            requestBodyTypeComboBox.addActionListener((e) -> {
                RequestBodyType requestBodyType = (RequestBodyType) requestBodyTypeComboBox.getSelectedItem();
                subject.onNext(requestBodyType.getName().equals(RequestBodyType.RAW().getName()));
            });
            subject.subscribe((e) -> {
                if (e) {
                    requestBodyRawTypeComboBox.setEnabled(true);
                } else {
                    requestBodyRawTypeComboBox.setEnabled(false);
                    requestBodyRawTypeComboBox.setSelectedIndex(0);
                }
            });
            this.add(requestBodyTypeComboBox);
            this.add(requestBodyRawTypeComboBox, "wrap");
        }
        this.add(this.bodyTextArea, "grow, push, span 3");
    }

    public void setText(String text) {
        this.bodyTextArea.bodyRequest.setText(text);
    }


}
