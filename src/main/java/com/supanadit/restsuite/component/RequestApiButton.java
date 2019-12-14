package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Objects;

public class RequestApiButton extends JButton {
    protected InputTextURL inputTextURL;
    protected OkHttpClient client;
    protected BodyPanel bodyPanel;
    protected RequestTypeComboBox requestTypeComboBox;
    protected JTable headerTable;

    public RequestApiButton(InputTextURL inputTextURL, RequestTypeComboBox requestTypeComboBox) {
        this.setText("Send");
        this.requestTypeComboBox = requestTypeComboBox;

        this.inputTextURL = inputTextURL;

        this.client = new OkHttpClient();

        RequestTabPanel.headerTable.subscribe((e) -> {
            this.headerTable = e;
        });

        this.addActionListener((e) -> {
            DefaultTableModel modelHeader = null;
            if (this.headerTable != null) {
                modelHeader = (DefaultTableModel) this.headerTable.getModel();
            }
            Request.Builder requestBuilder = new Request.Builder();
            if (modelHeader != null) {
                for (int i = 0; i < modelHeader.getRowCount(); i++) {
                    requestBuilder.addHeader(modelHeader.getValueAt(i, 0).toString(), modelHeader.getValueAt(i, 1).toString());
                }
            }
            Request request = requestBuilder.url(this.inputTextURL.getText()).build();

            RequestType requestType = (RequestType) this.requestTypeComboBox.getSelectedItem();
            assert requestType != null;
            if (requestType.getName().equals(RequestType.GET().getName())) {
                try (Response response = client.newCall(request).execute()) {
                    if (this.bodyPanel == null) {
                        System.out.println(Objects.requireNonNull(response.body()).string());
                    } else {
                        this.bodyPanel.setText(Objects.requireNonNull(response.body()).string());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void setBodyPanel(BodyPanel bodyPanel) {
        this.bodyPanel = bodyPanel;
    }
}
