package com.example.diary;

public class Note {
    String text, important;
    Boolean done;

    public Note(String text, String important, Boolean done) {
        this.text = text;
        this.important = important;
        this.done = done;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
