package com.sophia.Facebook_Clone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data

public class Comment {
    private int id;
    private int postID;
    private int userID;
    private String comment;
    private Timestamp date;
    private  int totalLikes;

    public Comment(int postID, int userID, String comment) {
        this.postID = postID;
        this.userID = userID;
        this.comment = comment;
    }

    public Comment(int id, int postID, int userID, String comment) {
        this.id = id;
        this.postID = postID;
        this.userID = userID;
        this.comment = comment;
    }

    public Comment(int postID, int userID, String comment, Timestamp date) {
        this.postID = postID;
        this.userID = userID;
        this.comment = comment;
        this.date = date;
    }
}
