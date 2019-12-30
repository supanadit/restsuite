package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestBodyRawType;
import com.supanadit.restsuite.model.RequestBodyType;
import com.supanadit.restsuite.model.RequestType;
import com.supanadit.restsuite.panel.BodyPanel;
import com.supanadit.restsuite.panel.RequestTabPanel;
import okhttp3.*;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

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
    protected RequestBodyType requestType = RequestBodyType.RAW();
    protected RequestBodyRawType requestBodyRawType = RequestBodyRawType.JSON();

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

        RequestTabPanel.requestBodyTypeSubject.subscribe((e) -> {
            this.requestType = e;
        });

        RequestTabPanel.requestBodyRawTypeSubject.subscribe((e) -> {
            this.requestBodyRawType = e;
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
                MediaType JSON = MediaType.parse(this.requestBodyRawType.getHeader());
                RequestBody requestBody = RequestBody.create(this.body, JSON);
                requestBuilder.post(requestBody);
            } else if (requestType.getName().equals(RequestType.PUT().getName())) {
                // PUT
                MediaType JSON = MediaType.parse(this.requestBodyRawType.getHeader());
                RequestBody requestBody = RequestBody.create(this.body, JSON);
                requestBuilder.put(requestBody);
            } else if (requestType.getName().equals(RequestType.DELETE().getName())) {
                // DELETE
                if (!this.body.isBlank() && !this.body.isEmpty()) {
                    MediaType JSON = MediaType.parse(this.requestBodyRawType.getHeader());
                    RequestBody requestBody = RequestBody.create(this.body, JSON);
                    requestBuilder.delete(requestBody);
                } else {
                    requestBuilder.delete();
                }
            }

            Request request = requestBuilder.url(this.inputTextURL.getText()).build();
            try (Response response = client.newCall(request).execute()) {
                String[] headerSplit = response.headers().get("Content-Type").split(";", -1);
                if (headerSplit.length != 0) {
                    String header;
                    if ("application/json".equals(headerSplit[0])) {
                        header = SyntaxConstants.SYNTAX_STYLE_JSON;
                    } else {
                        header = headerSplit[0];
                    }
                    this.bodyPanel.setSyntax(header);
                } else {
                    this.bodyPanel.setSyntax(SyntaxConstants.SYNTAX_STYLE_NONE);
                }
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
