package com.github.lf2a.alternative;

import javax.enterprise.inject.Alternative;

/**
 * <h1>TestCarroImpl.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Alternative
public class TestCarroImpl implements Carro {

    @Override
    public String ligar() {
        return "Testando o motor do carro";
    }
}
