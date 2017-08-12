package demo.model;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.model.Model;

import java.sql.Timestamp;

/**
 * Created by liangwenhui on 2017/7/31.
 */
public class SignIn extends Model{
    @Id
    @Column(name="sign_in_id")
    private Integer signInId;
    private Integer userId;
    private Integer courseId;
    private Timestamp createdAt;

    public Integer getSignInId() {
        return signInId;
    }

    public void setSignInId(Integer signInId) {
        this.signInId = signInId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
