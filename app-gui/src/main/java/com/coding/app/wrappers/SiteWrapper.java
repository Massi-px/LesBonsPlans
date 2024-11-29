package com.coding.app.wrappers;

import javafx.collections.ObservableList;

public abstract class SiteWrapper {

    private String url;
    private String params;

    public SiteWrapper(String url, String params) {
        this.url = url;
        this.params = params;
    }

    public abstract <T> void search(ObservableList<T> obs);

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
