package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.subjects.BehaviorSubject;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

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
        RTextScrollPane spBody = new RTextScrollPane(this.bodyTextArea);
        this.add(spBody, "grow, push, span 3");
    }

    public void setText(String text) {
        this.bodyTextArea.setText(text);
    }


}
