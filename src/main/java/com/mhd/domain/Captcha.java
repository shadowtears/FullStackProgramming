package com.mhd.domain;

/**
 * @author MouHongDa
 * @date 2023/11/25 21:25
 */
public class Captcha {
    private String img;
    private String code;

    public Captcha() {
    }

    public Captcha(String img, String code) {
        this.img = img;
        this.code = code;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
