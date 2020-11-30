package test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class TestJson {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("first", "1|1|微信面对面收款%1000107101%02%99%%0000%1#1#扫二维码付款-给小闵^CNY3.00^1|");
		map.put("first", null);
		System.out.println(JSON.toJSONString(map));
		System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue));
		Gson gson = new Gson();
		Gson gson1 = new GsonBuilder()
		        .serializeNulls()
		        .create();
		
		System.out.println(gson.toJson(map));
		System.out.println(gson1.toJson(map));
		
		String str = "{\"first\":\"1|1|微信面对面收款%1000107101%02%99%%0000%1#1#扫二维码付款-给\\u007F小闵^CNY3.00^1|\"}";
		System.out.println(JSON.parse(str));
	}
}
