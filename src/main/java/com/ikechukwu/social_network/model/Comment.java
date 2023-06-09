package com.ikechukwu.social_network.model;

public class Comment {

    private int id;
    private int userId;
    private int postId;
    private String commentBody;

    public Comment() {
    }

    public Comment(int id, int userId, int postId, String commentBody) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
    }

    public Comment(int userId, int postId, String commentBody) {
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

}
