package com.sophia.Facebook_Clone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Post {
    private int postID;
    private int userID;
    private String message;
    private String deleted;
    private Timestamp date;
    private  int totalLikes;

    public Post(int userID, String message) {
        this.userID = userID;
        this.message = message;
    }

    public Post( int postID, int userID, String message) {
        this.userID = userID;
        this.message = message;
        this.postID = postID;
    }
}
