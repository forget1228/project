package com.xiaoji.model;

public class AbkUCache {
    private int instance_id;
    private String instance_url;
    private String instance_status;
    private String sa_prefix;
    private String template_id;
    private String group_name;
    private String template_name;
    private String file_name;
    private String create_date;
    private String update_date;

    @Override
    public String toString() {
        return "AbkUCache{" +
                "instance_id=" + instance_id +
                ", instance_url='" + instance_url + '\'' +
                ", instance_status='" + instance_status + '\'' +
                ", sa_prefix='" + sa_prefix + '\'' +
                ", template_id='" + template_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", template_name='" + template_name + '\'' +
                ", file_name='" + file_name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }

    public int getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(int instance_id) {
        this.instance_id = instance_id;
    }

    public String getInstance_url() {
        return instance_url;
    }

    public void setInstance_url(String instance_url) {
        this.instance_url = instance_url;
    }

    public String getInstance_status() {
        return instance_status;
    }

    public void setInstance_status(String instance_status) {
        this.instance_status = instance_status;
    }

    public String getSa_prefix() {
        return sa_prefix;
    }

    public void setSa_prefix(String sa_prefix) {
        this.sa_prefix = sa_prefix;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
