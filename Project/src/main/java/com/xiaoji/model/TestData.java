package com.xiaoji.model;

public class TestData {
    private String groupSubdomain ;
    private String groupSAPrefix ;
    private String templateId ;
    private String data ;

    public TestData(String groupSubdomain, String groupSAPrefix, String templateId, String data) {
        this.groupSubdomain = groupSubdomain;
        this.groupSAPrefix = groupSAPrefix;
        this.templateId = templateId;
        this.data = data;
    }

    public String getGroupSubdomain() {
        return groupSubdomain;
    }

    public void setGroupSubdomain(String groupSubdomain) {
        this.groupSubdomain = groupSubdomain;
    }

    public String getGroupSAPrefix() {
        return groupSAPrefix;
    }

    public void setGroupSAPrefix(String groupSAPrefix) {
        this.groupSAPrefix = groupSAPrefix;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
