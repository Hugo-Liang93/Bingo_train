package demo.service;

import demo.model.SignIn;
import demo.model.TrainingCourse;
import demo.model.User;
import demo.service.impl.courseServiceImpl;
import demo.util.jsonUitl;
import leap.orm.query.CriteriaQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangwenhui on 2017/8/2.
 */
public class CourseService implements courseServiceImpl {
    public Boolean createCourse(String courseJson) {
        Map<String, Object> courseMap=jsonUitl.jsonToMap(courseJson);
        try{
            TrainingCourse.create(courseMap);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User queryTeacher(String name) {
        CriteriaQuery<User> cq = User.<User>query();
        cq.where("user_name like ? ",
                "%"+name);
        return cq.first();
    }

    public User queryTeacherById(Integer id) {
        return User.find(id);
    }

    public Map<String, String> queryCourse(String courseName) {
        Map<String,String> ma=new HashMap<String, String>();
        CriteriaQuery<TrainingCourse> cq = TrainingCourse.query();
        cq.where("courseName like ?" ,
                "%"+courseName);
        TrainingCourse tr=cq.first();
        User us=queryTeacherById(tr.getLecturerId());
        ma.put("courseName",tr.getCourseName());
        ma.put("teacherName",us.getUserName());
        ma.put("departmentName",us.getDepartmentName());
        ma.put("phone",us.getTel());
        ma.put("email",us.getMail());
        ma.put("courseIntroduce",tr.getCourseIntroduction());
        ma.put("trainingTime",tr.getTrainingTime().toString());
        ma.put("trainingLast",tr.getTrainLast());
        return ma;
    }

    public Boolean courseEnroll(String courseName,Integer  userId) {
        CriteriaQuery<TrainingCourse> cq = TrainingCourse.query();
        cq.where("courseName like ?" ,
                "%"+courseName);
        Integer courseId=cq.first().getCourseId();
        SignIn courseSign=new SignIn();
        courseSign.setUserId(userId);
        courseSign.setCourseId(courseId);
        try {
            courseSign.create();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String,String>> queryAllCourse() {
        List<Map<String,String>> lm=new ArrayList<Map<String, String>>();
        List<TrainingCourse> ls=TrainingCourse.findAll();
        for (TrainingCourse tr:ls
             ) {
            Map<String,String> ma=new HashMap<String, String>();
            User te=queryTeacherById(tr.getLecturerId());
            ma.put("teacherName",te.getUserName());
            ma.put("courseName",tr.getCourseName());
            ma.put("trainingTime",tr.getTrainingTime().toString());
            lm.add(ma);
        }
        return lm;
    }

    public List<Map<String, String>> querySignCourse(Integer userId) {
        CriteriaQuery<SignIn> cq = SignIn.query();
        cq.where("userId like ? ",
                "%"+userId);
        List<SignIn> si=cq.list();
        //找到了课程ID想获得下面数据
        List<Map<String,String>> lm=new ArrayList<Map<String, String>>();
        List<TrainingCourse> ls=TrainingCourse.findAll();
        //查询所有报名课程
        for (SignIn ssi:si
             ) {
            Map<String,String> ma=new HashMap<String, String>();
            TrainingCourse tc=TrainingCourse.find(ssi.getCourseId());
            User te=queryTeacherById(tc.getLecturerId());
            ma.put("teacherName",te.getUserName());
            ma.put("courseName",tc.getCourseName());
            ma.put("trainingTime",tc.getTrainingTime().toString());
            lm.add(ma);
        }
        return lm;
    }
}
