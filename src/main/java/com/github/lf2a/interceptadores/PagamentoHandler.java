package com.github.lf2a.interceptadores;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import java.io.Serializable;

/**
 * <h1>PagamentoHandler.java</h1>
 * Esta classe é uma classe ouvinte.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Logged
@SessionScoped
public class PagamentoHandler implements Serializable {

    public void pagarViaCredito(@Observes @Credit PagamentoEvent pagamentoEvent) {
        System.out.println("Handler: " + pagamentoEvent + "\n");
    }

    public void pagarViaDebito(@Observes @Debit PagamentoEvent pagamentoEvent) {
        System.out.println("Handler: " + pagamentoEvent + "\n");
    }
}
