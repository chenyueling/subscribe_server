package com.chenyueling.subscribe.web;

import com.chenyueling.subscribe.common.HttpSessionFactory;
import com.chenyueling.subscribe.resource.FreemarkerResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.servlet.http.HttpSession;

/**
 * Test Application subclass for servlet3-webapp example.
 *
 * @author Adam Lindenthal (adam.lindenthal at oracle.com)
 */

public class AppResourceConfig extends ResourceConfig {

   public AppResourceConfig(){
       System.out.println("AppResourceConfig init...");
       packages("com.chenyueling.subscribe.resource");
    //   register(RequestContextFilter.class);
       register(new AbstractBinder() {
           @Override
           protected void configure() {
               bindFactory(HttpSessionFactory.class).to(HttpSession.class);
           }
       });

     //  super(FreemarkerResource.class);

     //  register(LoggingFilter.class);
       register(FreemarkerMvcFeature.class);
   }
}
