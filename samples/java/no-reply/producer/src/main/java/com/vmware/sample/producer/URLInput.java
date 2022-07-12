package com.vmware.sample.producer;

public class URLInput {
    private String url;
    public URLInput(){}

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "<URLInput: <url: " + url + ">>";
    }
}
