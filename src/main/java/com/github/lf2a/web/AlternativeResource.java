package com.github.lf2a.web;

import com.github.lf2a.alternative.Carro;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * <h1>AlternativeResource.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Path("/alternative")
public class AlternativeResource {

    @Inject
    private Carro carro;

    @GET
    @Path("")
    public Response getCarroMsg() {
        return Response.ok(carro.ligar()).build();
    }
}
