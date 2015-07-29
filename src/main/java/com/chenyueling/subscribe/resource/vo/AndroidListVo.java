package com.chenyueling.subscribe.resource.vo;

import java.util.List;

/**
 * Created by chenyueling on 2015/5/12.
 */
public class AndroidListVo {
    private String total;
    private String result;
    private String code;

    private List data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
