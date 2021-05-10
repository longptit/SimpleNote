package com.example.firstapp;

import android.widget.TextClock;

import org.w3c.dom.Text;

import java.io.Serializable;

public class Folder implements Serializable {
    private String title;
    private String detail;
    private int id;

    public Folder(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
    public Folder(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Folder(String title, String content, int id) {
        this.title = title;
        this.detail = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

