package com.ikechukwu.social_network.model;

public class Post {

    private int id;
    private int userId;
    private String messageBody;
    private int numOfComments;
    private int numOfLikes;

    public Post() {
    }

    public Post(int id, int userId, String messageBody, int numOfComments, int numOfLikes) {
        super();
        this.id = id;
        this.userId = userId;
        this.messageBody = messageBody;
        this.numOfComments = numOfComments;
        this.numOfLikes = numOfLikes;
    }

    public Post(int userId, String messageBody, int numOfComments, int numOfLikes) {
        this.userId = userId;
        this.messageBody = messageBody;
        this.numOfComments = numOfComments;
        this.numOfLikes = numOfLikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public void setNumOfComments(int numOfComments) {
        this.numOfComments = numOfComments;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }
}
