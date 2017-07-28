package demo.model;

/**
 * Created by liangwenhui on 2017/7/28.
 */
import java.sql.Timestamp;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;
@Table("leap_user")
public class User extends Model {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Integer age;
    private String loginId;
    @Column(name="password")
    private String password;
    private Timestamp createdAt;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}