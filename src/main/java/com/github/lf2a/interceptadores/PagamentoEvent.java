package com.github.lf2a.interceptadores;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h1>PagamentoEvent.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
public class PagamentoEvent implements Serializable {

    private String tipoPagamento;
    private String valor;
    private LocalDateTime dataPagamento;

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
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

    @Override
    public String toString() {
        return "PagamentoEvento{" +
                "tipoPagamento='" + tipoPagamento + '\'' +
                ", valor='" + valor + '\'' +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
