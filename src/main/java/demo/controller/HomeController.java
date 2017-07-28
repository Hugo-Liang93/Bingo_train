package demo.controller;

import demo.model.User;
import leap.core.exception.RecordNotSavedException;
import leap.web.view.ViewData;
/**
 * Created by liangwenhui on 2017/7/21.
 */
public class HomeController {
    public void index(ViewData vd, String name){
        vd.put("name", name);
    }

    public User create(String name, Integer age, String loginId, String password){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setLoginId(loginId);
        user.setPassword(password);
        try {
            user.create();
            return user;
        } catch (RecordNotSavedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
