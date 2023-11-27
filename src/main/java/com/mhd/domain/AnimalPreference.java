package com.mhd.domain;

/**
 * @author MouHongDa
 * @date 2023/11/26 21:43
 */
public class AnimalPreference {
    private String userName;
    private String favoriteAnimal;
    private String reason;
    private String image;

    @Override
    public String toString() {
        return "AnimalPreference{" +
                "userName='" + userName + '\'' +
                ", favoriteAnimal='" + favoriteAnimal + '\'' +
                ", reason='" + reason + '\'' +
                ", imageUrl='" + image + '\'' +
                '}';
    }

    public AnimalPreference() {
    }


    public AnimalPreference(String userName, String favoriteAnimal, String reason, String imageUrl) {
        this.userName = userName;
        this.favoriteAnimal = favoriteAnimal;
        this.reason = reason;
        this.image = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
