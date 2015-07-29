package com.chenyueling.subscribe.common;

import com.chenyueling.subscribe.server.entity.Admin;
import com.chenyueling.subscribe.server.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by chenyueling on 2015/4/24.
 */

public class HttpSessionHelper {

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_USERNAME = "USER_USERNAME";

    private static final String ADMIN_ID = "ADMIN_ID";
    private static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    private static final String ADMIN_NAME = "ADMIN_NAME";
    private static final String ADMIN_LAST_LOGIN_TIME ="ADMIN_LAST_LOGIN_TIME";



    private static HttpSession httpSession;

    public HttpSessionHelper(){
    }


    public static final HttpSession getSession(){
        System.out.println(httpSession);
        return httpSession;
    }

    public static final String getUserId(HttpSession httpSession){
        return (String)httpSession.getAttribute(USER_ID);
    }

    public static final String getUserName(HttpSession httpSession){
        return (String)httpSession.getAttribute(USER_NAME);
    }


    public static final void removeUser(HttpSession httpSession){
        httpSession.removeAttribute(USER_ID);
        httpSession.removeAttribute(USER_NAME);
    }
    public static final void putUser(HttpSession httpSession,User user){
        httpSession.setAttribute(USER_ID,user.getId());
        httpSession.setAttribute(USER_NAME,user.getName());
        httpSession.setAttribute(USER_USERNAME,user.getUsername());
    }



    public void setSession(HttpSession session) {
        this.httpSession = session;
    }

    public static void putAdmin(HttpSession httpSession,Admin admin) {
        httpSession.setAttribute(ADMIN_ID,admin.getId());
        httpSession.setAttribute(ADMIN_NAME,admin.getName());
        Date lastLoginTime = admin.getLastLoginTime();
        httpSession.setAttribute(ADMIN_LAST_LOGIN_TIME,lastLoginTime != null? DateUtis.formatDate(lastLoginTime):"first Login");
        httpSession.setAttribute(ADMIN_USERNAME,admin.getUserName());
    }


    public static String getAdminId(HttpSession httpSession){
        return (String)httpSession.getAttribute(ADMIN_ID);
    }

    public static String getAdminName(HttpSession httpSession){
        return (String)httpSession.getAttribute(ADMIN_NAME);
    }

    public static String getAdminLastLoginTime(HttpSession httpSession){
        return (String)httpSession.getAttribute(ADMIN_LAST_LOGIN_TIME);
    }

    public static String getAdminUsername(HttpSession httpSession){
        return (String)httpSession.getAttribute(ADMIN_USERNAME);
    }

    public static String getUserUserName(HttpSession httpSession) {
        return (String)httpSession.getAttribute(USER_USERNAME);
    }
}

