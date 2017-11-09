package com.eshop.common.utils;

import java.io.Serializable;

/**
 * Created by ADMIN on 2017/10/26.
 */
public class EUITreeNode implements Serializable{
    private long id;
    private String text;
    private String state;

    public EUITreeNode(long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public EUITreeNode() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
