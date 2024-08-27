package com.itheima.springboot.pojo;

public class Jcgy {
    private int id;
    private String name;
    private String word;
    private int year;
    private String img;
    private String url;
    private byte status;

    public Jcgy() {
    }

    public Jcgy(int id, String name, String word, int year, String img, String url, byte status) {
        this.id = id;
        this.name = name;
        this.word = word;
        this.year = year;
        this.img = img;
        this.url = url;
        this.status = status;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * 设置
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * 获取
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * 设置
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * 获取
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取
     * @return status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(byte status) {
        this.status = status;
    }

}
