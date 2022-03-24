package com.scaffail.model;

public class Comment {

    private int id;
    private int bookFk;
    private String nickname;
    private String text;

    public Comment(int id, int bookFk, String nickname, String text) {

        this.id = id;
        this.bookFk = bookFk;
        this.nickname = nickname;
        this.text = text;
    }

    public int getBookFk() {

        return bookFk;
    }

    public int getId() {

        return id;
    }

    public String getNickname() {

        return nickname;
    }

    public String getText() {

        return text;
    }

    public void setBookFk(int bookFk) {

        this.bookFk = bookFk;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public void setText(String text) {

        this.text = text;
    }

    @Override
    public String toString() {

        return "Comment [id=" + id + ", bookFk=" + bookFk + ", nickname=" + nickname + ", text=" + text + "]";
    }

}
