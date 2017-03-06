package xyz.ftuan.platform.passport.model;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import xyz.ftuan.platform.passport.util.MD5Utils;

/**
 * Created by LUOXC on 2017/3/6.
 */
public class ApiResponse {

    public static final ApiResponse SUCCESS = new ApiResponseBuilder().build();

    private static int SUCCESS_CODE = 0;
    private static String DEFAULT_SUCCESS_MESSAGE = "ok";

    private int code;
    private String message;
    private Object data;
    private String sign;

    private ApiResponse() {
        this.code = SUCCESS_CODE;
        this.message = DEFAULT_SUCCESS_MESSAGE;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public static class ApiResponseBuilder {

        private ApiResponse apiResponse;

        public ApiResponseBuilder() {
            apiResponse = new ApiResponse();
        }

        /**
         * 设置错误码。默认200
         *
         * @param code 错误码
         * @return ApiResponseBuilder
         */
        public ApiResponseBuilder code(int code) {
            apiResponse.code = code;
            return this;
        }

        /**
         * 设置消息。默认[操作成功]
         *
         * @param message 错误消息
         * @return ApiResponseBuilder
         */
        public ApiResponseBuilder message(String message) {
            apiResponse.message = message;
            return this;
        }


        /**
         * 设置响应的具体内容
         *
         * @param data 响应的具体内容
         * @return 内容
         */
        public ApiResponseBuilder data(Object data) {
            apiResponse.data = data;
            return this;
        }

        /**
         * 构造响应
         *
         * @return 响应
         */
        public ApiResponse build() {
            if(apiResponse.data != null){
                apiResponse.sign = MD5Utils.encode(JSON.toJSONString(apiResponse));
            }
            return apiResponse;
        }

    }

}
