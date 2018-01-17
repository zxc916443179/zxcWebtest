package com.test.app.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Json {
    /**
     * parse List or Object into jsonObject and jsonArray
     * @param object
     * @return
     */
    static public Object parseJson (Object object) {
        JSONArray jsonArray = new JSONArray();
        JSONObject js;
        if (object instanceof List) {
            for (Object item : (List) object) {
                jsonArray.add(parseJson(item));
            }
            return jsonArray;
        } else {
            js = JSONObject.parseObject(JSONObject.toJSONString(object));
            return js;
        }
    }
}
