package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.dao.UserDao;
import com.chenyueling.subscribe.server.entity.User;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * test it at http://localhost:8888/api-test/ask
 */
@Path("/api-test")
//@Component()
public class TestResource extends ResourceConfig{

    //@Autowired
    // @Resource(name="articleDao")
    //Resource

    /*@Inject
    private HttpSession hts;*/

    @Inject
    ArticleDao articleDao;

    @Inject
    UserDao userDao;

    @Context private HttpServletRequest httpRequest;



	@GET
	@Path("ask")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {

      /*  System.out.println(hts);*/
        System.out.println(articleDao);
        User user = userDao.findById("b672d410-6864-4798-bd08-428f26ba7922");
       /* Article article = new Article();
        article.setCreateDate(new Date());
        article.setId(UUID.randomUUID().toString());
        article.setTitle("title");
        article.setUrl("https://www.google.com");

        //article.setService();
        System.out.println(user.getName());
        System.out.println(user.getId());
        //userDao.save(user);*/

        /*Service service1 = serviceDao.findById("1");
        System.out.println(service.getUser().getName());
        System.out.println(service.getName());*/
        System.out.println(httpRequest);
      //  httpSession = httpRequest.getSession();
        //System.out.println(httpSession.getAttribute("xx"));

      //  httpSession.setAttribute("xx", "session");

        //

        return "It's working! :-)";


    }
}