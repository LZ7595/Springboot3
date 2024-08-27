package com.itheima.springboot.pojo;

public class Rcdh {
    private int id;
    private String name;
    private String word;
    private int num;
    private String img;
    private String url;
    private byte status;

    public Rcdh() {
    }

    public Rcdh(int id, String name, String word, int num, String img, String url, byte status) {
        this.id = id;
        this.name = name;
        this.word = word;
        this.num = num;
        this.img = img;
        this.url = url;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
