package com.zzh.music.model;

import java.util.List;

/**
 * Created by ZZH on 16/7/14.
 *
 * @Date: 16/7/14
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 基类
 */
public abstract class BaseModel<T> {
    private String status;
    private String msg;
    private List<T> contents;
    private T content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}