package com.example.games;

public class Game {
    private int id;
    private String name;
    private String developer;
    private int rating;
    private String imageUrl;
    private String description;
    private Boolean isExpanded;

    public Game(int id, String name, String developer, int rating, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        isExpanded = false;
    }



    public Boolean getExtended() {
        return isExpanded;
    }

    public void setExtended(Boolean extended) {
        isExpanded = extended;
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

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", rating=" + rating +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
