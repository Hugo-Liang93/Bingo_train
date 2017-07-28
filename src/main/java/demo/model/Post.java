package demo.model;

/**
 * Created by liangwenhui on 2017/7/28.
 */
import java.sql.Timestamp;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;
@Table("leap_post")
public class Post extends Model {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="descript")
    private String descript;
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
    public String getDescript() {
        return descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
