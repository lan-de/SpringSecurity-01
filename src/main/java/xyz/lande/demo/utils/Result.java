package xyz.lande.demo.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 统一返回
 */
@Data
public class Result {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();

    private Result(){}


    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public static Result code(Integer code){
        Result result = new Result();
        result.setCode(code);
        return result;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
