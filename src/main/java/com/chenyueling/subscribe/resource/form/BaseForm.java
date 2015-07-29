package com.chenyueling.subscribe.resource.form;

import javax.ws.rs.QueryParam;

/**
 * Created by chenyueling on 2015/4/29.
 */
public class BaseForm {

    @QueryParam("deviceCode")
    private String deviceCode;
    @QueryParam("sort")
    private String sort = "createTime";
    //databases all tables have createTime so default order by createTime
    @QueryParam("order")
    private String order = "desc";



    /**
     * this is page query parameter
     */
    @QueryParam("p")
    private int p = 1;
    @QueryParam("r")
    private int r = 5;



    public String getSort() {
        if(sort == null || "".equals(sort)){
            sort = "createTime";
        }
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        if(order == null || "".equals(order)){
            order = "desc";
        }
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getP() {
        if(p == 0){
            p = 1;
        }
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getR() {
        if(r == 0){
            r = 5;
        }
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }



    public class Limit{
        private int start;
        private int end;

        public int getStart() {
            return start;
        }
        public int getEnd() {
            return end;
        }
    }



    public Limit getLimit(){
         Limit limit = new Limit();
        limit.start = (getP()-1) * getR();
        limit.end = getP() * getR();
        return limit;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
