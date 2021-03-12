package com.github.lf2a.greetings;

import com.github.lf2a.annotation.Informal;

/**
 * <h1>InformalGreeting.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 11/03/2021
 */
@Informal
public class InformalGreeting extends Greeting {
    /**
     * Irá cumprimentar o usuário de maneira informal
     *
     * @param name O nome do usuário
     * @return O cumprimento
     */
    public String sayHello(String name) {
        return "Hi, " + name + "!";
    }
}
