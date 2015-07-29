package com.chenyueling.subscribe.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * example for an embedded http server with scala and jetty. TODO: see how to
 * define a basic root context, without introducing boilerplate
 */
public class MinimalHttpServer {

	public static void main(String[] args) {

		URI baseUri = UriBuilder.fromUri("http://localhost/").port(8888).build();
		ResourceConfig config = new ResourceConfig();
        config.packages("com");
        config.register(RequestContextFilter.class);
		/*Server server = JettyHttpContainerFactory.createServer(baseUri, config);*/
	}
}