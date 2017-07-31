package demo.controller;

import demo.model.EntryBlank;
import leap.core.exception.RecordNotSavedException;
import leap.web.annotation.Controller;


/**
 * Created by liangwenhui on 2017/7/28.
 */
public class UserModelController {
    public EntryBlank create(Integer userId,Integer courseId){
        EntryBlank eb=new EntryBlank();
        eb.setUserId(userId);
        eb.setCourseId (courseId);
        try {
            eb.create();
            return eb;
        }catch (RecordNotSavedException e){
            e.printStackTrace();
            return null;
        }
    }
}
