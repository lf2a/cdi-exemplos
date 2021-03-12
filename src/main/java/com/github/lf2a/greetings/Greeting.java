package com.github.lf2a.greetings;

import javax.enterprise.inject.Default;

/**
 * <h1>Greeting.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 11/03/2021
 */
@Default
public class Greeting {
    /**
     * Irá cumprimentar o usuário de maneira formal
     *
     * @param name O nome do usuário
     * @return O cumprimento
     */
    public String sayHello(String name) {
        return "Hello, " + name + ".";
    }
}
