package com.pulingle.gateway_zuul.domain.dto;

import java.io.Serializable;

/**
 * Created by 杨健 on 2018/3/30
 *
 * @Des: 接口返回信息体
 */

public class RespondBody implements Serializable{
    //接口调用状态1成功，0失败
    private String status;
    //调用返回失败信息，0000为成功
    private String msg;
    //数据载体
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
