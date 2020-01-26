package com.supanadit.restsuite.helper;

// Author Supan Adit Pratama <supanadit@gmail.com>

import com.supanadit.restsuite.model.RequestModel;

import java.util.ArrayList;

public class UrlParser {
    String url;

    protected final String http = "http://";
    protected final String https = "https://";
    protected final String www = "wwww.";

    public UrlParser(String url) {
        this.url = url;
    }

    public boolean isHttp() {
        boolean isValidHttp = false;
        if (this.url.length() >= this.http.length()) {
            isValidHttp = this.url.substring(0, this.http.length()).equals(this.http);
        }
        return isValidHttp;
    }

    public boolean isHttps() {
        boolean isValidHttps = false;
        if (this.url.length() >= this.https.length()) {
            isValidHttps = this.url.substring(0, this.https.length()).equals(this.https);
        }
        return isValidHttps;
    }

    public boolean isUseWWW() {
        boolean isUserUrl = false;
        int index = -1;
        if (this.isHttp()) {
            index = this.http.length();
        } else if (this.isHttps()) {
            index = this.https.length();
        }

        if (index != -1) {
            index = index + (this.www.length() - 1);
            isUserUrl = this.url.length() > index;
        }
        return isUserUrl;
    }

    public boolean isHasCleanUrlTarget() {
        boolean isHasUrl = false;
        int index = -1;
        if (this.isHttp()) {
            index = this.http.length();
        } else if (this.isHttps()) {
            index = this.https.length();
        }

        if (index != -1) {
            if (this.isUseWWW()) {
                index = index + (this.www.length() - 1);
            }
            isHasUrl = this.url.length() > index;
        }
        return isHasUrl;
    }

    public boolean isHasDomain() {
        boolean isHasDomain = false;
        int index = -1;
        if (this.isHttp()) {
            index = this.http.length();
        } else if (this.isHttps()) {
            index = this.https.length();
        }

        if (index != -1) {
            if (this.isUseWWW()) {
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 2) {
                    isHasDomain = true;
                }
            }
        }
        return isHasDomain;
    }

    public boolean isValid() {
        boolean isValid = false;
        if (this.isHttp() || this.isHttps()) {
            if (this.isHasCleanUrlTarget()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public boolean hasQueryParams() {
        boolean isHasQueryParams = false;
        int index = -1;
        if (this.isHttp()) {
            index = this.http.length();
        } else if (this.isHttps()) {
            index = this.https.length();
        }

        if (index != -1) {
            if (this.isUseWWW()) {
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        int lengthLastPath = urlSplitPathDomain.length - 1;
                        String[] queryParams = urlSplitPathDomain[lengthLastPath].split("\\?");
                        if (queryParams.length >= 2) {
                            isHasQueryParams = true;
                        }
                    }
                }
            }
        }
        return isHasQueryParams;
    }

    public ArrayList<RequestModel> getQueryParams() {
        ArrayList<RequestModel> requestModel = new ArrayList<>();
        int index = -1;
        if (this.isHttp()) {
            index = this.http.length();
        } else if (this.isHttps()) {
            index = this.https.length();
        }

        if (index != -1) {
            if (this.isUseWWW()) {
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        int lengthLastPath = urlSplitPathDomain.length - 1;
                        String[] queryParams = urlSplitPathDomain[lengthLastPath].split("\\?");
                        if (queryParams.length >= 2) {
                            String[] params = queryParams[1].split("&");
                            for (int i = 0; i < params.length; i++) {
                                String[] keyValue = params[i].split("=");
                                RequestModel requestModelData;
                                if (keyValue.length > 1) {
                                    requestModelData = new RequestModel(keyValue[0], keyValue[1]);
                                } else {
                                    requestModelData = new RequestModel(keyValue[0], "");
                                }
                                requestModel.add(requestModelData);
                            }
                        }
                    }
                }
            }
        }
        return requestModel;
    }
}
