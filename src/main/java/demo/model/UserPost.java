package demo.model;

/**
 * Created by liangwenhui on 2017/7/28.
 */
import java.sql.Timestamp;

import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;
@Table("leap_user_post")
public class UserPost extends Model {
    @Id
    private String userId;
    @Id
    private String postId;

    private Timestamp createdAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}