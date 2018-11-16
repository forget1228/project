package com.xiaoji.model;

public class AbkRecording {
    private int abk_id;
    private String sa_prefix;
    private String template_class_name;
    private String template_id;
    private String template_name;
    private String template_generator;
    private String template_file;
    private String template_type;
    private String create_date;
    private String update_date;

    @Override
    public String toString() {
        return "AbkRecording{" +
                "abk_id=" + abk_id +
                ", sa_prefix='" + sa_prefix + '\'' +
                ", template_class_name='" + template_class_name + '\'' +
                ", template_id='" + template_id + '\'' +
                ", template_name='" + template_name + '\'' +
                ", template_generator='" + template_generator + '\'' +
                ", template_file='" + template_file + '\'' +
                ", template_type='" + template_type + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }

    public int getAbk_id() {
        return abk_id;
    }

    public void setAbk_id(int abk_id) {
        this.abk_id = abk_id;
    }

    public String getSa_prefix() {
        return sa_prefix;
    }

    public void setSa_prefix(String sa_prefix) {
        this.sa_prefix = sa_prefix;
    }

    public String getTemplate_class_name() {
        return template_class_name;
    }

    public void setTemplate_class_name(String template_class_name) {
        this.template_class_name = template_class_name;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_generator() {
        return template_generator;
    }

    public void setTemplate_generator(String template_generator) {
        this.template_generator = template_generator;
    }

    public String getTemplate_file() {
        return template_file;
    }

    public void setTemplate_file(String template_file) {
        this.template_file = template_file;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
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
