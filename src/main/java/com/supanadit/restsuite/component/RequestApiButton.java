package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestType;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RequestApiButton extends JButton {
    protected InputTextURL inputTextURL;
    protected OkHttpClient client;
    protected BodyPanel bodyPanel;
    protected RequestTypeComboBox requestTypeComboBox;
    protected JTable headerTable;
    protected String body = "";

    public RequestApiButton(InputTextURL inputTextURL, RequestTypeComboBox requestTypeComboBox) {
        this.setText("Send");
        this.requestTypeComboBox = requestTypeComboBox;

        this.inputTextURL = inputTextURL;

        this.client = new OkHttpClient();

        RequestTabPanel.headerTable.subscribe((e) -> {
            this.headerTable = e;
        });

        RequestTabPanel.bodyText.throttleWithTimeout(300, TimeUnit.MILLISECONDS).subscribe((e) -> {
            this.body = e;
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

            RequestType requestType = (RequestType) this.requestTypeComboBox.getSelectedItem();
            assert requestType != null;
            if (requestType.getName().equals(RequestType.GET().getName())) {
                // GET
                requestBuilder.get();
            } else if (requestType.getName().equals(RequestType.POST().getName())) {
                // POST
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(this.body, JSON);
                requestBuilder.post(requestBody);
            }

            Request request = requestBuilder.url(this.inputTextURL.getText()).build();
            try (Response response = client.newCall(request).execute()) {
                if (this.bodyPanel == null) {
                    System.out.println(Objects.requireNonNull(response.body()).string());
                } else {
                    this.bodyPanel.setText(Objects.requireNonNull(response.body()).string());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setBodyPanel(BodyPanel bodyPanel) {
        this.bodyPanel = bodyPanel;
    }
}
