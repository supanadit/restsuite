package com.supanadit.restsuite.component.button;

import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.model.*;
import com.supanadit.restsuite.panel.rest.RestPanel;
import com.supanadit.restsuite.panel.rest.request.TabPanel;
import com.supanadit.restsuite.panel.rest.request.tab.body.BodyFormInputPanel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersFormInputPanel;
import com.supanadit.restsuite.panel.rest.response.ResponseBodyPanel;
import okhttp3.Request;
import okhttp3.*;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RequestApiButton extends JButton {
    protected InputTextURL inputTextURL;
    protected OkHttpClient client;
    protected ResponseBodyPanel responseBodyPanel;
    protected RequestTypeComboBox requestTypeComboBox;
    protected String bodyRawValue;
    protected BodyTypeModel requestType;
    protected BodyRawTypeModel bodyRawTypeModel;

    public RequestApiButton(RestPanel restPanel) {
        setText("Send");
        client = new OkHttpClient();

        addActionListener((e) -> {
            setEnabled(false);
            setText("Requesting");

            // Get Request Type whether is POST, GET, PUT, or DELETE
            this.requestTypeComboBox = restPanel.requestTypeComboBox;
            // Get Value URL
            this.inputTextURL = restPanel.apiURL;

            // Create Tab Panel
            TabPanel tabPanel = restPanel.tabPanel;

            // Get the value of Body Raw
            bodyRawValue = tabPanel.bodyPanel.getRequestBodyRawValue();
            // Get the Request Type whether Form or Raw
            requestType = tabPanel.bodyPanel.getRequestBodyType();
            // Get Body Raw Type whether is JSON, Plain Text, HTML, XML or Javascript
            bodyRawTypeModel = tabPanel.bodyPanel.getRequestBodyRawType();

            // Create request Builder
            Request.Builder requestBuilder = new Request.Builder();

            // Get All Header List
            ArrayList<HeadersFormInputPanel> listHeaderFormInput = tabPanel.headersPanel.headersFormPanel.listInputPanel;
            // Set Request Headers
            for (HeadersFormInputPanel header : listHeaderFormInput) {
                // Get Key
                String key = header.getKeyField().getText();
                // Get Value of Key
                String value = header.getValueField().getText();
                // Verify that the Key and Value isn't empty
                if (!key.isEmpty() && !value.isEmpty()) {
                    // Add to the Request
                    requestBuilder.addHeader(key, value);
                }
            }

            RequestTypeModel requestTypeRequestModel = (RequestTypeModel) requestTypeComboBox.getSelectedItem();
            if (requestTypeRequestModel != null) {
                RequestBody requestBody;

                if (requestType.getName().equals(BodyTypeModel.RAW().getName())) {
                    MediaType mediaType = MediaType.parse(bodyRawTypeModel.getHeader());
                    requestBody = RequestBody.create(bodyRawValue, mediaType);
                } else {
                    MultipartBody.Builder builder = new MultipartBody.Builder();

                    builder.setType(MultipartBody.FORM);
                    ArrayList<BodyFormInputPanel> listFormInput = tabPanel.bodyPanel.bodyFormPanel.listInputPanel;
                    if (listFormInput.size() != 0) {
                        for (BodyFormInputPanel body : listFormInput) {
                            // Get Type
                            BodyFormTypeModel type = (BodyFormTypeModel) body.getTypeComboBox().getSelectedItem();
                            // Get Key
                            String key = body.getKeyField().getText();
                            // Get Value
                            String value = body.getValueField().getText();

                            assert type != null;

                            if (type.getName().equals(BodyFormTypeModel.FIELD().getName())) {
                                builder.addFormDataPart(key, value);
                            } else {
                                builder.addFormDataPart(key, value, RequestBody.create(new File(value), MediaType.parse("application/octet-stream")));
                            }
                        }
                        requestBody = builder.build();
                    } else {
                        requestBody = RequestBody.create("", MediaType.parse(BodyRawTypeModel.TEXT().getHeader()));
                    }
                }

                if (requestTypeRequestModel.getName().equals(RequestTypeModel.GET().getName())) {
                    // GET
                    requestBuilder.get();
                } else if (requestTypeRequestModel.getName().equals(RequestTypeModel.POST().getName())) {
                    // POST
                    requestBuilder.post(requestBody);
                } else if (requestTypeRequestModel.getName().equals(RequestTypeModel.PUT().getName())) {
                    // PUT
                    requestBuilder.put(requestBody);
                } else if (requestTypeRequestModel.getName().equals(RequestTypeModel.DELETE().getName())) {
                    // DELETE
                    if (!bodyRawValue.isBlank() && !bodyRawValue.isEmpty()) {
                        requestBuilder.delete(requestBody);
                    } else {
                        requestBuilder.delete();
                    }
                }
            }

            try {
                Request request = requestBuilder.url(inputTextURL.getText()).build();
                try (Response response = client.newCall(request).execute()) {
                    String[] headerSplit = Objects.requireNonNull(response.headers().get("Content-Type")).split(";", -1);
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
                        responseBodyPanel.setSyntax(header);
                    } else {
                        responseBodyPanel.setSyntax(SyntaxConstants.SYNTAX_STYLE_NONE);
                    }
                    if (responseBodyPanel == null) {
                        System.out.println(Objects.requireNonNull(response.body()).string());
                    } else {
                        responseBodyPanel.setText(Objects.requireNonNull(response.body()).string());
                    }
                } catch (IOException ex) {
                    responseBodyPanel.setText(ex.getMessage());
                }
            } catch (Exception ex) {
                responseBodyPanel.setText(ex.getMessage());
            }
            setText("Send");
            setEnabled(true);
        });
    }

    public void setResponseBodyPanel(ResponseBodyPanel responseBodyPanel) {
        this.responseBodyPanel = responseBodyPanel;
    }
}
