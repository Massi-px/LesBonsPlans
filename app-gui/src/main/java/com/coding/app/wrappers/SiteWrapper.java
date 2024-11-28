package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;

import java.util.List;

public abstract class SiteWrapper {

    private String url;
    private String params;

    private List<String> results;

    public SiteWrapper(String url, String params) {
        this.url = url;
        this.params = params;
    }

    public abstract List<Annonce> search();

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
