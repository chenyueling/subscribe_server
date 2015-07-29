package com.chenyueling.subscribe.common;

import com.google.gson.Gson;

/**
 * Created by chenyueling on 2015/4/29.
 */
public class JsonUtils {
    private static final Gson gson = new Gson();
        public static final String format(Object o){
            return gson.toJson(o);
        }
}
