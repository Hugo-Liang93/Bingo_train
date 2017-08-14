package demo.controller;

import demo.model.Feedback;
import demo.model.FeedbackObjectives;
import demo.model.FeedbackRemark;
import demo.model.TrainingCourse;
import leap.lang.http.HTTP;
import leap.lang.http.client.HttpRequest;
import leap.orm.query.CriteriaQuery;
import leap.orm.query.EntityQuery;
import leap.orm.query.Query;
import leap.web.annotation.RequestMapping;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.*;

import static leap.orm.model.Model.query;

/**
 * Created by MoSon on 2017/7/29.
 */
public class FeedbackModelController {

    /**
     * 查询所有的反馈信息
     * @return
     */
    public List<Feedback>   getFeedbacks(){
        CriteriaQuery<Feedback> cq = Feedback.<Feedback>query();
        List<Feedback> list = cq.list();
        System.out.println(list);
        return list;
    }


    /**
     * json格式
     * {
     "result" :[
     {
     "question" : "您认为培训内容是否系统和完整？",
     "type" : 0
     },
     {
     "question" : "培训资料是否实用？",
     "type" : 0
     },
     {
     "question" : "对培训资料有哪些改善的建议？",
     "type" : 1
     }
     ],
     "grade" : 8
     }
     * @param json
     */
    @RequestMapping(method = HTTP.Method.POST)
    public String createFeedback(String json){
        System.out.println(json);
//        if(json != null)
        String string = "{    \"result\" :[    {    \"question\" : \"您认为讲师的表达能力如何？\",    \"type\" : 0    },    {    \"question\" : \"讲师讲课的条理清晰程度如何？\",    \"type\" : 0    },    {    \"question\" : \"讲师在培训中结合案例分析的能力如何？\",    \"type\" : 0    },{    \"question\" : \"您认为讲师活跃课堂的能力如何？\",    \"type\" : 0    },{    \"question\" : \"您对培训讲师有那些改善建议？\",    \"type\" : 1    },{    \"question\" : \"您觉得课程安排是否合理？\",    \"type\" : 0    },{    \"question\" : \"您对课程安排有什么改善建议？\",    \"type\" : 1    },{    \"question\" : \"您认为培训环境如何？\",    \"type\" : 0    },{    \"question\" : \"您对培训环境有什么改善建议？\",    \"type\" : 1    },{    \"question\" : \"请您对此次培训的整体效果评分？\",    \"type\" : 0    }    ],    \"grade\" : 8    }";
        JSONObject jsonObject = JSONObject.fromObject(string);
        Object result = jsonObject.get("result");
        Object grade = jsonObject.get("grade");
        JSONArray feedbackList = JSONArray.fromObject(result);
        for (int i=0; i<feedbackList.size(); i++) {
            Feedback feedback = new Feedback();
            Object o = feedbackList.get(i);
            JSONObject fb = JSONObject.fromObject(o);
            Object question = fb.get("question");
            Object type = fb.get("type");
            feedback.setQuestion(question.toString());
            feedback.setType(Integer.parseInt(type.toString()));
            feedback.setMaxGrade(Integer.parseInt(grade.toString()));
            feedback.create();
        }
        return "创建成功";
    }


    //查询所有的数据
    public List<Feedback> queryFeedback(HttpRequest request){
        CriteriaQuery<Feedback> cq = query();
         cq.select();
        List<Feedback> list = cq.list();
        return list;
    }






    /**
     * json
     * [
     {
     "course_id" : 1,
     "num" : 1,
     "content" : 7,
     "type" : 0
     },
     {
     "course_id" : 1,
     "num" : 2,
     "content" : 7,
     "type" : 0
     },
     {
     "course_id" : 1,
     "num" : 3,
     "content" : "老师讲课大声些",
     "type" : 1
     }
     ]
     保存学生填写的信息
     * @return
     */
    public boolean save(String json){
        System.out.println("-----------json"+json);
        if(json != null){
//        String string = "[{\"course_id\" : 1,\"num\" : 1,\"content\" : 8,\"type\" : 0},{\"course_id\" : 1,\"num\" : 2,\"content\" : 8,\"type\" : 0},{\"course_id\" : 1,\"num\" : 3,\"content\" : \"老师讲得不太好\",\"type\" : 1}][{\"course_id\" : 1,\"num\" : 3,\"remark\" : \"老师讲课大声些\",\"type\" : 1},{\"course_id\" : 1,\"num\" : 2,\"grade\" : 7},]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        for (int i=0; i<jsonArray.size(); i++) {
            Object object = jsonArray.get(i);
            JSONObject feedback = JSONObject.fromObject(object);
            Object course_id = feedback.get("course_id");
            Object num = feedback.get("num");
            Object content = feedback.get("content");
            Object type = feedback.get("type");
            if (Integer.parseInt(type.toString()) == 0) {
                FeedbackObjectives fo = new FeedbackObjectives();
                fo.setCourseId(Integer.parseInt(course_id.toString()));
                fo.setNum(Integer.parseInt(num.toString()));
                fo.setGrade(Integer.parseInt(content.toString()));
                fo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                fo.create();
            } else {
                if (StringUtils.isNotBlank(content.toString())) {
                    FeedbackRemark fr = new FeedbackRemark();
                    fr.setCourseId(Integer.parseInt(course_id.toString()));
                    fr.setNum(Integer.parseInt(num.toString()));
                    fr.setRemark(content.toString());
                    fr.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    fr.create();
                }
            }
            }
        }
        return true;
    }

    //查询课程名称
    public String queryClassName(String courseId){
        if (courseId != null) {
            CriteriaQuery<TrainingCourse> tc = TrainingCourse.<TrainingCourse>query();
            tc.select("course_name").where("course_id = ? ", courseId);
            List<TrainingCourse> list = tc.list();
            String className = tc.toString();
            return className;
        }
        return null;
    }


    //查询选择题
    public  List<Map<String,Object>> queryObjectives(String courseId){
        if(courseId != null) {
            //查询有多少题号
            EntityQuery<FeedbackObjectives> query = FeedbackObjectives.<FeedbackObjectives>query("fo-distnct-num");
            List<FeedbackObjectives> list = query.list();

            //根据题号查询每一题的平均分
            List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
            for (FeedbackObjectives feedbackObjectives : list){
                Integer num = feedbackObjectives.getNum();
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("courseId",courseId);
                params.put("num",num);
                Query<FeedbackObjectives> qfo = FeedbackObjectives.<FeedbackObjectives>query("fo-avg-grade").params(params);
                List<FeedbackObjectives> lfo = qfo.list();
                FeedbackObjectives feedbackObjectives1 = lfo.get(0);
                Map<String, Object> fields = feedbackObjectives1.fields();
                Object grade = fields.get("avg(grade)");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("num",num);
                map.put("avgGrade",grade);
                result.add(map);
            }
            return result;
        }
        return null;
    }

    //查询备注
    public  List<Map<String,Object>> queryRemark(String courseId){
        if(courseId != null) {
            CriteriaQuery<FeedbackRemark> cq = FeedbackRemark.<FeedbackRemark>query();
            cq.where("course_id = ? ", courseId).orderBy("num");
            List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();;
            List<FeedbackRemark> frList = cq.list();
            for (FeedbackRemark fr : frList){
                Map<String,Object> map = new LinkedHashMap<String,Object>();
                Integer num = fr.getNum();
                String remark = fr.getRemark();
                map.put("num",num);
                map.put("remark",remark);
                list.add(map);
            }
            System.out.println(list);
            return list;
        }
        return null;
    }


    public static void main(String[] args) {
       Object type = "0";
        if(type.equals("0")){
            System.out.println("--0");
        }else {
            System.out.println("--1");
        }

    }
}
