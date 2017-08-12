package demo.util;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description   <操作json>
 * @about  
 * json必须使用的包是：
 * commons-lang-2.6.jar，
 * ezmorph-1.0.6.jar，
 * commons-beanutils-1.9.2.jar，
 * commons-collections-3.2.1.jar，
 * json-lib-2.4-jdk15.jar。
 * 
 * json必须使用commons-lang-2.6.jar版本，如果使用commons-lang3-3.4.jar会报错。
 * json必须使用commons-collections-3.2.1.jar版本，如果使用commons-collections4-4.1.jar会报错Caused by: java.lang.ClassNotFoundException: org.apache.commons.collections.map.ListOrderedMap。
 * @author   李丽全
 * @since   2016年8月29日 02:30:18
 */
public class JsonUtils {
    public static JSONArray getJSONArray(String json) {
		return JSONArray.fromObject(json);
	}
	
	public static JSONObject getJSONObject(String json) {
		return JSONObject.fromObject(json);
	}
	
	/**
	 * @description   <转换成list>
	 * @about   
	 * 使用json-lib的好处就是，数据在转换的过程中不会被改变。如果使用gson，那么
	 * 数据会变化，这样就不是我们想要的结果。例如：
	 * [{"name":"小明","age":15}]用gson转换之后输出的结果是{"name":"小明","age":15.0}
	 * 很明显，15变成了15.0，但是，用json-lib输出的结果跟原始的数据一样的。
	 * @param json
	 * @return
	 */
	public static List<String> getList(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<String> list = new ArrayList<String>();
		int length = jsonArray.size();
		for(int i = 0; i < length; i ++) {
			list.add(jsonArray.getString(i));
		}
		return list;
	}
}