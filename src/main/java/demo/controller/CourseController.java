package demo.controller;

import demo.model.TrainingCourse;
import demo.model.User;
import demo.service.CourseService;
import demo.service.impl.courseServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liangwenhui on 2017/7/31.
 */
public class CourseController {
    courseServiceImpl courseService=new CourseService();
    public String createCourse(String courseJson) throws Exception{
        Boolean status=courseService.createCourse(courseJson);
        if(status){
            return "success";
        }else return "fail";
    }

    public User queryTeacher(String name){
        User us=courseService.queryTeacher(name);
        return us;
    }

    public Map<String,String> queryCourse(String courseName){
        return courseService.queryCourse(courseName);
    }

    public List<Map<String,String>> queryAllCourse(){
        return courseService.queryAllCourse();
    }

    public Map<String,Boolean> courseEnroll(String courseName,HttpServletRequest req){
        Map<String, Boolean> ma=new HashMap<String, Boolean>();
        User user= (User) req.getSession().getAttribute("user");

        ma.put("status",courseService.courseEnroll(courseName,user.getUserId()));
        return ma;
    }

    public List<Map<String,String>> querySignCourse(HttpServletRequest req){
        User user=(User)req.getSession().getAttribute("user");
        Integer userId=user.getUserId();
        return courseService.querySignCourse(userId);
    }

}
