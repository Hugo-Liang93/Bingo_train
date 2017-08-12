package demo.util;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liangwenhui on 2017/8/2.
 */
public class jsonUitl {
    public static Map<String, Object> jsonToMap(String courseJson){
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            JSONObject jsonObject=new JSONObject(courseJson);
            Iterator ite = jsonObject.keys();
            while (ite.hasNext()){
                String key =ite.next().toString();
                Object value = jsonObject.get(key).toString();
                data.put(key,value);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
