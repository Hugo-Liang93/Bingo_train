package demo.controller;

import demo.model.User;
import leap.orm.query.CriteriaQuery;

import java.util.List;

/**
 * Created by liangwenhui on 2017/7/28.
 */
public class UserModelController {
    public List<User> query(String name, Integer age, String loginId){
        if(name == null && age == null && loginId == null){
            return User.all();
        }
        if(name == null){
            name = "";
        }
        if(loginId == null){
            loginId = "";
        }
        CriteriaQuery<User> cq = User.<User>query();
        cq.where("name like ? and age like ? and loginId like ?",
                "%"+name+"%",age==null?"%%":"%"+age+"%","%"+loginId+"%");
        return cq.list();
    }
}
