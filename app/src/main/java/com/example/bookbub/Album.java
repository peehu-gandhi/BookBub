package com.example.bookbub;
public class Album {
    private String thumbnail;
    public Album() {
    }
    public Album(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}