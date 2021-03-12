package com.github.lf2a.interceptadores;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h1>PagamentoBean.java</h1>
 * Este é um bean que pode ser usado em uma pagina jsf.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Named
@SessionScoped
public class PagamentoBean implements Serializable {

    private static final int DEBIT = 1;
    private static final int CREDIT = 2;
    private int paymentOption = DEBIT;

    private String valor;

    private LocalDateTime dataPagamento;

    @Inject
    @Credit
    Event<PagamentoEvent> creditEvent;

    @Inject
    @Debit
    Event<PagamentoEvent> debitEvent;

    public int getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(int paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Logged
    public PagamentoEvent pagar() {
        this.dataPagamento = LocalDateTime.now();

        switch (paymentOption) {
            case DEBIT:
                PagamentoEvent debitPayload = new PagamentoEvent();
                debitPayload.setTipoPagamento("Debit");
                debitPayload.setValor(valor);
                debitPayload.setDataPagamento(dataPagamento);
                debitEvent.fire(debitPayload);
                return debitPayload;

            case CREDIT:
                PagamentoEvent creditPayload = new PagamentoEvent();
                creditPayload.setTipoPagamento("Credit");
                creditPayload.setValor(valor);
                creditPayload.setDataPagamento(dataPagamento);
                creditEvent.fire(creditPayload);
                return creditPayload;

            default:
                System.out.println("Tipo de pagamento invalido");
        }
        return new PagamentoEvent();
    }

}
