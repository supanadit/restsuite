package com.supanadit.restsuite.component.button;

import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.model.*;
import com.supanadit.restsuite.panel.api.ApiPanel;
import com.supanadit.restsuite.panel.api.request.TabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseBodyPanel;
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
    protected RequestBodyType requestType;
    protected RequestBodyRawType requestBodyRawType;

    public RequestApiButton(ApiPanel apiPanel) {
        setText("Send");
        client = new OkHttpClient();

        addActionListener((e) -> {
            setEnabled(false);
            setText("Requesting");

            this.requestTypeComboBox = apiPanel.getModel().getRequestMethodComboBox();
            this.inputTextURL = apiPanel.getModel().getUrl();

            TabPanel tabPanel = apiPanel.getModel().getTabPanel();

            bodyRawValue = tabPanel.getRequestModel().getBodyPanel().getRequestBodyRawValue();
            requestType = tabPanel.getRequestModel().getBodyPanel().getRequestBodyType();
            requestBodyRawType = tabPanel.getRequestModel().getBodyPanel().getRequestBodyRawType();

            Request.Builder requestBuilder = new Request.Builder();

            ArrayList<RequestHeadersFormInputModel> listHeaderFormInput = tabPanel.getRequestModel().getHeadersPanel().getHeadersFormPanel().getModel().getAllFormInput();
            for (RequestHeadersFormInputModel request : listHeaderFormInput) {
                if (!request.getKey().isEmpty() && !request.getValue().isEmpty()) {
                    requestBuilder.addHeader(request.getKey(), request.getValue());
                }
            }

            RequestType requestTypeRequest = (RequestType) requestTypeComboBox.getSelectedItem();
            if (requestTypeRequest != null) {
                RequestBody requestBody;

                if (requestType.getName().equals(RequestBodyType.RAW().getName())) {
                    MediaType mediaType = MediaType.parse(requestBodyRawType.getHeader());
                    requestBody = RequestBody.create(bodyRawValue, mediaType);
                } else {
                    MultipartBody.Builder builder = new MultipartBody.Builder();

                    builder.setType(MultipartBody.FORM);
                    ArrayList<RequestBodyFormInputModel> listFormInput = tabPanel.getRequestModel().getBodyPanel().getBodyFormPanel().getModel().getAllFormInput();
                    if (listFormInput.size() != 0) {
                        for (RequestBodyFormInputModel input : listFormInput) {
                            if (input.getType().equals(RequestBodyFormType.FIELD().getName())) {
                                builder.addFormDataPart(input.getKey(), input.getValue());
                            } else {
                                builder.addFormDataPart(input.getKey(), input.getValue(), RequestBody.create(new File(input.getValue()), MediaType.parse("application/octet-stream")));
                            }
                        }
                        requestBody = builder.build();
                    } else {
                        requestBody = RequestBody.create("", MediaType.parse(RequestBodyRawType.TEXT().getHeader()));
                    }
                }

                if (requestTypeRequest.getName().equals(RequestType.GET().getName())) {
                    // GET
                    requestBuilder.get();
                } else if (requestTypeRequest.getName().equals(RequestType.POST().getName())) {
                    // POST
                    requestBuilder.post(requestBody);
                } else if (requestTypeRequest.getName().equals(RequestType.PUT().getName())) {
                    // PUT
                    requestBuilder.put(requestBody);
                } else if (requestTypeRequest.getName().equals(RequestType.DELETE().getName())) {
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
