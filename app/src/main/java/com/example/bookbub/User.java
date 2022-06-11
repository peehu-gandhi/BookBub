package com.example.bookbub;

public class User {
    private String name, no, passwd;
    private int books_pub,quotes_shared;
    public User(  String no, String passwd,String name,int books_pub,int quotes_shared)
    {
        this.no = no;
        this.passwd = passwd;
        this.books_pub=books_pub;
        this.quotes_shared=quotes_shared;
        this.name=name;
    }

    public int getBooks_pub() {
        return books_pub;
    }

    public void setBooks_pub(int books_pub) {
        this.books_pub = books_pub;
    }

    public int getQuotes_shared() {
        return quotes_shared;
    }

    public void setQuotes_shared(int quotes_shared) {
        this.quotes_shared = quotes_shared;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}