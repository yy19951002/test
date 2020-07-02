package com.huayun.test.model;

import java.io.Serializable;

public class JdbcConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String key;
    private String value;
    private String application;
    private String profile;
    private String label;
    private String desc;
    private String isview;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsview() {
        return isview;
    }

    public void setIsview(String isview) {
        this.isview = isview;
    }
}
