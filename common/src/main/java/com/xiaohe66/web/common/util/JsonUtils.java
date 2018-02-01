package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 数据格式处理类
 *
 * @author xiaohe
 * @time 17-3-3 003.
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 对象转json字符串
     * @param obj 待处理java的对象
     * @return 对象对应的json字符串
     */
    public static String toString(Object obj){
        String jsonStr = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return jsonStr;
    }

    /**
     * json字符串转json对象
     *
     * todo:逻辑暂未实现
     * @param jsonStr
     *          标准json字符串
     * @return
     *          JSONObject
     */
    public static JSONObject toObject(String jsonStr) {
        throw new XhException(CodeEnum.NOT_IMPLEMENTED, "not implemented");
    }

    /**
     * json字符串转json数组对象
     *
     * todo:逻辑暂未实现
     * @param jsonStr
     *          标准json字符串
     * @return
     *          JSONArray
     */
    public static JSONArray toArray(String jsonStr){
        throw new XhException(CodeEnum.NOT_IMPLEMENTED,"not implemented");
    }
}
