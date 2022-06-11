package com.example.bookbub;
public class Books
{
    String book_img;
    boolean isPinned;
    public Books(String book_img,boolean isPinned) {
        this.book_img = book_img;
        this.isPinned=isPinned;
    }
    public String getBook_img() {
        return book_img;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }
}
