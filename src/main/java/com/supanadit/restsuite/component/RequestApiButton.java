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
        setText("Send");
        this.requestTypeComboBox = requestTypeComboBox;

        this.inputTextURL = inputTextURL;

        client = new OkHttpClient();

        RequestTabPanel.headerTable.subscribe((e) -> {
            headerTable = e;
        });

        RequestTabPanel.bodyText.throttleWithTimeout(300, TimeUnit.MILLISECONDS).subscribe((e) -> {
            body = e;
        });

        RequestTabPanel.requestBodyTypeSubject.subscribe((e) -> {
            requestType = e;
        });

        RequestTabPanel.requestBodyRawTypeSubject.subscribe((e) -> {
            requestBodyRawType = e;
        });

        addActionListener((e) -> {
            DefaultTableModel modelHeader = null;
            if (headerTable != null) {
                modelHeader = (DefaultTableModel) headerTable.getModel();
            }
            Request.Builder requestBuilder = new Request.Builder();
            if (modelHeader != null) {
                for (int i = 0; i < modelHeader.getRowCount(); i++) {
                    requestBuilder.addHeader(modelHeader.getValueAt(i, 0).toString(), modelHeader.getValueAt(i, 1).toString());
                }
            }

            RequestType requestType = (RequestType) requestTypeComboBox.getSelectedItem();
            assert requestType != null;
            if (requestType.getName().equals(RequestType.GET().getName())) {
                // GET
                requestBuilder.get();
            } else if (requestType.getName().equals(RequestType.POST().getName())) {
                // POST
                MediaType JSON = MediaType.parse(requestBodyRawType.getHeader());
                RequestBody requestBody = RequestBody.create(body, JSON);
                requestBuilder.post(requestBody);
            } else if (requestType.getName().equals(RequestType.PUT().getName())) {
                // PUT
                MediaType JSON = MediaType.parse(requestBodyRawType.getHeader());
                RequestBody requestBody = RequestBody.create(body, JSON);
                requestBuilder.put(requestBody);
            } else if (requestType.getName().equals(RequestType.DELETE().getName())) {
                // DELETE
                if (!body.isBlank() && !body.isEmpty()) {
                    MediaType JSON = MediaType.parse(requestBodyRawType.getHeader());
                    RequestBody requestBody = RequestBody.create(body, JSON);
                    requestBuilder.delete(requestBody);
                } else {
                    requestBuilder.delete();
                }
            }

            Request request = requestBuilder.url(inputTextURL.getText()).build();
            try (Response response = client.newCall(request).execute()) {
                String[] headerSplit = response.headers().get("Content-Type").split(";", -1);
                if (headerSplit.length != 0) {
                    String header;
                    switch (headerSplit[0]) {
                        case "application/json":
                            header = SyntaxConstants.SYNTAX_STYLE_JSON;
                            break;
                        case "application/vnd.wap.xhtml+xml":
                            header = SyntaxConstants.SYNTAX_STYLE_HTML;
                            break;
                        default:
                            header = headerSplit[0];
                            break;
                    }
                    bodyPanel.setSyntax(header);
                } else {
                    bodyPanel.setSyntax(SyntaxConstants.SYNTAX_STYLE_NONE);
                }
                if (bodyPanel == null) {
                    System.out.println(Objects.requireNonNull(response.body()).string());
                } else {
                    bodyPanel.setText(Objects.requireNonNull(response.body()).string());
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
