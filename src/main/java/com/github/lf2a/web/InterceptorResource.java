package com.github.lf2a.web;

import com.github.lf2a.interceptadores.PagamentoBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

/**
 * <h1>InterceptorResource.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Path("/interceptor")
public class InterceptorResource {

    @Inject
    private PagamentoBean pagamentoBean;

    @GET
    @Path("")
    public Response interceptor(
            @QueryParam("p") int tipoPagamento,
            @QueryParam("v") String valor
    ) {
        pagamentoBean.setPaymentOption(tipoPagamento);
        pagamentoBean.setValor(valor);

        return Response.ok(pagamentoBean.pagar().toString()).build();
    }
}
