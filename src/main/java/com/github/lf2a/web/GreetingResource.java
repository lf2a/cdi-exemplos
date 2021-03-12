package com.github.lf2a.web;

import com.github.lf2a.annotation.Informal;
import com.github.lf2a.annotation.MaxNumber;
import com.github.lf2a.annotation.RandomNumber;
import com.github.lf2a.greetings.Greeting;
import com.sun.jersey.api.view.Viewable;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <h1>GreetingResource.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 11/03/2021
 */
@Path("")
public class GreetingResource {

    @Inject
    private Greeting greeting;

    @Inject
    @Informal
    private Greeting informalGreeting;

    @Inject
    @MaxNumber
    private int maxNumber;

    @Inject
    @RandomNumber
    private int randomNumber;

    @GET
    @Path("")
    public Response get(@DefaultValue("formal") @QueryParam("g") String g) {

        if (g.equals("informal")) {
            return Response.ok(informalGreeting.sayHello("Luiz")).build();
        }

        return Response.ok(greeting.sayHello("Luiz")).build();
    }

    @GET
    @Path("/max")
    public Response getMaxNumber() {
        return Response.ok(maxNumber).build();
    }

    @GET
    @Path("/random")
    public Response getRandomNumber() {
        return Response.ok(randomNumber).build();
    }
}
