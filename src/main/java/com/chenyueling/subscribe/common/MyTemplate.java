package com.chenyueling.subscribe.common;

import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chenyueling on 2015/5/7.
 */
public class MyTemplate implements TemplateProcessor<String> {
    @Override
    public String resolve(String s, MediaType mediaType) {
        return null;
    }

    @Override
    public void writeTo(String s, Viewable viewable, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException {

    }
}
