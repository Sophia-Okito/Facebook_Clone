package com.sophia.Facebook_Clone.DAO;

import com.sophia.Facebook_Clone.model.Comment;
import com.sophia.Facebook_Clone.model.Post;

import java.sql.*;

public class PostDAO {
    // sql queries
    private static final String getPostLikeSQL = "SELECT * FROM  postLikes WHERE postID = ? AND userID = ?";
    private static final String getCommentLikeSQL = "SELECT * FROM  commentLikes WHERE id = ? AND userID = ?";

    private static final String likePostSQl = "INSERT INTO postLikes (postID, userID) VALUES(?, ?)";
    private static final String likeCommentSQl = "INSERT INTO commentLikes (id, userID) VALUES(?, ?)";

    private static final String unlikePostSql = "DELETE  FROM postLikes  WHERE postID = ? AND userID = ?";
    private static final String unlikeCommentSql = "DELETE  FROM commentLikes  WHERE id = ? AND userID = ?";

    private static final  String updatePostLike  = "UPDATE posts SET totalLikes = ? WHERE postID = ?";
    private static final  String updateCommentLike  = "UPDATE comments SET totalLikes = ? WHERE id = ?";

    private static final  String updatePostUnlike  = "UPDATE posts SET totalLikes = ? WHERE postID = ?";
    private static final  String updateCommentUnlike  = "UPDATE posts SET commentLikes = ? WHERE id = ?";

    private static final String createPostSql = "INSERT INTO posts (userID, message) VALUES(?, ?)";
    private static final String createCommentSql = "INSERT INTO COMMENTS(postID, userID, comment) VALUES (?, ?, ?)";

    private static final String getPostTotalLikes = "SELECT totalLikes from  posts WHERE postID = ?";
    private static final String getCommentTotalLikes = "SELECT totalLikes from  comments WHERE id = ?";

    private static final  String updatePostSql  = "UPDATE posts SET message = ? WHERE postID = ?";
    private static final  String updateCommentSql  = "UPDATE comments SET comment = ? WHERE id = ?";

    private static final String deletePostSql = "UPDATE posts SET deleted = ?  WHERE postID = ?";
    private static final String deleteCommentSql = "DELETE  FROM comments  WHERE id = ?";


    // method to create a new post
    public static boolean createPost(Post post){
        boolean status = false;
        try (Connection connection =DBConnection.getConnection();
             PreparedStatement preparedStatement =  connection.prepareStatement(createPostSql);) {
            preparedStatement.setInt(1, post.getUserID());
            preparedStatement.setString(2, post.getMessage());
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " affected row");
            status =  true;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Could Not Create Post");
        }
        return status;
    }

    // method to like or Unlike a post
    public static void likeOrUnlikePost(int postID, int userID) throws SQLException, ClassNotFoundException {
        int totalLikes = getPostTotalLikes(postID);
        // check if the user has already liked the post
        try(Connection connection =  DBConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(getPostLikeSQL);
            preparedStatement.setInt(1, postID);
            preparedStatement.setInt(2, userID);
            ResultSet resultSet  = preparedStatement.executeQuery();
            if(resultSet.next()){
                // unlike the post
                PreparedStatement preparedStatement1 = connection.prepareStatement(unlikePostSql);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updatePostUnlike);
                preparedStatement1.setInt(1, postID);
                preparedStatement1.setInt(2, userID);
                preparedStatement1.execute();
                preparedStatement2.setInt(2, postID );
                preparedStatement2.setInt(1, (totalLikes - 1 ));
                preparedStatement2.execute();

            } else {
                // like the post
                PreparedStatement preparedStatement1 = connection.prepareStatement(likePostSQl);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updatePostLike);
                preparedStatement1.setInt(1, postID);
                preparedStatement1.setInt(2, userID);
                preparedStatement1.execute();
                preparedStatement2.setInt(2, postID );
                preparedStatement2.setInt(1, (totalLikes + 1) );
                preparedStatement2.execute();
            }
        }
    }

    // method to like or Unlike a comment
    public static void likeOrUnlikeComment(int id, int userID) throws SQLException, ClassNotFoundException {
        int totalLikes = getCommentTotalLikes(id);
        try(Connection connection =  DBConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(getCommentLikeSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userID);
            ResultSet resultSet  = preparedStatement.executeQuery();
            if(resultSet.next()){
                // unlike the post
                PreparedStatement preparedStatement1 = connection.prepareStatement(unlikeCommentSql);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateCommentUnlike);
                preparedStatement1.setInt(1, id);
                preparedStatement1.setInt(2, userID);
                preparedStatement1.execute();
                preparedStatement2.setInt(2, id );
                preparedStatement2.setInt(1, (totalLikes - 1 ));
                preparedStatement2.execute();

            } else {
                // like the post
                PreparedStatement preparedStatement1 = connection.prepareStatement(likeCommentSQl);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateCommentLike);
                preparedStatement1.setInt(1, id);
                preparedStatement1.setInt(2, userID);
                preparedStatement1.execute();
                preparedStatement2.setInt(2, id );
                preparedStatement2.setInt(1, (totalLikes + 1) );
                preparedStatement2.execute();

            }
        }
    }

    // method to create a new comment
    public static boolean createComment(Comment comment)  {
        // get object connection
        boolean successful =  false;
        try {
            Connection connection = DBConnection.getConnection();
            // create a prepared statement
            PreparedStatement preparedStatement =  connection.prepareStatement(createCommentSql);
            // set values for the parameters
            preparedStatement.setInt(1, comment.getPostID());
            preparedStatement.setInt(2, comment.getUserID());
            preparedStatement.setString(3, comment.getComment());
            // execute query;
            boolean createStatement = preparedStatement.execute();
            successful  = true;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return successful;
    }

    // method to edit a new post
    public  static boolean updatePost(Post post) {
        boolean successful =  false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updatePostSql);
            preparedStatement.setString(1, post.getMessage());
            preparedStatement.setInt(2, post.getPostID());
            // execute the command
            boolean updatePost = preparedStatement.execute();
            successful = true;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return  successful;
    }

    // method to edit a new comment
    public  static boolean updateComment(Comment comment) {
        boolean successful =  false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCommentSql);
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setInt(2, comment.getId());
            boolean updateComment = preparedStatement.execute();
            successful = true;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return  successful;
    }

    // method to delete a new post
    public static boolean deletePost(int postID){
        boolean successful =  false;
        // create a prepared statement
        try (Connection connection  = DBConnection.getConnection()){
            PreparedStatement preparedStatement =  connection.prepareStatement(deletePostSql);
            preparedStatement.setString(1,"true");
            preparedStatement.setInt(2, postID);
            boolean deletePost =  preparedStatement.execute();
            if (deletePost){
                successful =  true;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return  successful;
    }

    // method to get total likes of a post
    public static int getPostTotalLikes(int postID)  {
        int totalLikes = 0;
        try(Connection connection  = DBConnection.getConnection()) {
            PreparedStatement preparedStatement =  connection.prepareStatement(getPostTotalLikes);
            preparedStatement.setInt(1, postID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                totalLikes =  Integer.parseInt(resultSet.getString("totalLikes"));
                System.out.println("Post total likes = " + totalLikes);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to get post Content");
        }

        return totalLikes;
    }

    // method to get total likes of a comment
    public static int getCommentTotalLikes(int id)  {
        int totalLikes = 0;
        try(Connection connection  = DBConnection.getConnection()) {
            PreparedStatement preparedStatement =  connection.prepareStatement(getCommentTotalLikes);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                totalLikes =  Integer.parseInt(resultSet.getString("totalLikes"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to get post Content");
        }

        return totalLikes;
    }

    // method to delete a new comment
    public static boolean deleteComment(int id) {
        boolean successful = false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCommentSql);
            preparedStatement.setInt(1, id);
            boolean deletePost = preparedStatement.execute();
            if (deletePost) {
                successful = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return successful;
    }
}
