package com.itheima.springboot.pojo;

public class Rwjs {
    private int id;
    private String name;
    private String img;
    private String word;

    public Rwjs() {

    }

    public Rwjs(int id, String name, String img, String word) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.word = word;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
