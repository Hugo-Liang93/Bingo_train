package demo.controller;

import demo.model.EntryBlank;
import leap.core.exception.RecordNotSavedException;
import leap.web.view.ViewData;

import java.util.Map;

/**
 * Created by liangwenhui on 2017/7/21.
 */
public class HomeController {
    public void index(ViewData vd, String name){
        vd.put("name", name);
    }




}
