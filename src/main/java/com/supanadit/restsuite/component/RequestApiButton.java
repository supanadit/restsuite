package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.*;
import com.supanadit.restsuite.panel.ApiPanel;
import com.supanadit.restsuite.panel.BodyPanel;
import com.supanadit.restsuite.panel.RequestTabPanel;
import io.reactivex.disposables.Disposable;
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
    protected BodyPanel bodyPanel;
    protected RequestTypeComboBox requestTypeComboBox;
    protected String bodyRawValue;
    protected RequestBodyType requestType;
    protected RequestBodyRawType requestBodyRawType;

    public RequestApiButton(ApiPanel apiPanel) {
        setText("Send");
        client = new OkHttpClient();
        addActionListener((e) -> {
            this.requestTypeComboBox = apiPanel.getRequestTypeComboBox();
            this.inputTextURL = apiPanel.getInputURL();

            RequestTabPanel requestTabPanel = apiPanel.getModel().getRequestTabPanel();

            bodyRawValue = requestTabPanel.getRequestModel().getBodyPanel().getRequestBodyRawValue();
            requestType = requestTabPanel.getRequestModel().getBodyPanel().getRequestBodyType();
            requestBodyRawType = requestTabPanel.getRequestModel().getBodyPanel().getRequestBodyRawType();

            Request.Builder requestBuilder = new Request.Builder();
            for (com.supanadit.restsuite.model.Request request : requestTabPanel.getRequestModel().getHeadersPanel().getRequestTable().getRequest()) {
                requestBuilder.addHeader(request.getKey(), request.getValue());
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
                    ArrayList<RequestBodyFormInputModel> listFormInput = requestTabPanel.getRequestModel().getBodyPanel().getBodyFormPanel().getModel().getAllFormInput();
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
