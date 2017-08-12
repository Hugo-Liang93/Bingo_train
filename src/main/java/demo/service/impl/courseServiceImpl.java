package demo.service.impl;

import demo.model.TrainingCourse;
import demo.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by liangwenhui on 2017/8/2.
 */
public interface courseServiceImpl {
    Boolean createCourse(String courseJson);
    User queryTeacher(String name);
    User queryTeacherById(Integer id);
    Map<String,String> queryCourse(String courseName);
    Boolean courseEnroll(String courseName,String userId);
    List<Map<String,String>> queryAllCourse();
    List<Map<String,String>> querySignCourse(Integer userId);
}
