package com.github.lf2a.greetings;

import com.github.lf2a.annotation.Informal;
import com.github.lf2a.annotation.MaxNumber;
import com.github.lf2a.annotation.RandomNumber;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Random;

/**
 * <h1>Printer.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 11/03/2021
 */
@Named
@RequestScoped
public class Printer {
    /**
     * Acessando este bean no JSF
     * <p>
     * <h:form id="greetme">
     * <p>
     * <h:outputLabel value="Enter your name: " for="name"/>
     * <h:inputText id="name" value="#{printer.name}"/>
     * </p>
     * <p>
     * <h:commandButton value="Say Hello" action="#{printer.createSalutation}"/>
     * </p>
     * <p>
     * <h:outputText value="#{printer.salutation}"/>
     * </p>
     * </h:form>
     */

    @Inject
    @Informal
    private Greeting greeting;

    private String name;
    private String salutation;

    public String getSalutation() {
        return salutation;
    }

    public String getName() {
        return name;
    }

    @Produces
    @MaxNumber
    /**
     * Os métodos do produtor fornecem uma maneira de injetar objetos que não são beans,
     * objetos cujos valores podem variar em tempo de execução e objetos que requerem
     * inicialização customizada.
     *
     * Para estes casos deve-se usar a anotação @javax.enterprise.inject.Produces
     */
    public int getMaxNumber() {
        return Integer.MAX_VALUE;
    }

    @Produces
    @RandomNumber
    /**
     * Se o valor puder variar no tempo de execução, o processo será um pouco diferente.
     * Por exemplo, o código a seguir define um método produtor que gera um número
     * aleatório definido por um qualificador chamado @RandomNumber
     */
    public int getRandomNumber() {
        return new Random(System.currentTimeMillis()).nextInt();
    }

    @PostConstruct
    /**
     * A inicialização de um bean gerenciado especifica o método de retorno de chamada do ciclo
     * de vida que a estrutura CDI deve chamar após a injeção de dependência, mas antes que a
     * classe seja colocada em serviço.
     * Quando o bean gerenciado é injetado em um componente, o CDI chama o método depois que
     * todas as injeções ocorrem e depois que todos os inicializadores são chamados.
     */
    public void insert() {
        this.name = "Luiz";
        this.salutation = greeting.sayHello(name);
    }

    @PreDestroy
    /**
     * A preparação para a destruição de um bean gerenciado especifica o método de retorno de
     * chamada do ciclo de vida que sinaliza que um componente do aplicativo está prestes a
     * ser destruído pelo container.
     * Neste método, execute qualquer limpeza necessária antes que o bean seja destruído,
     * como liberar um recurso que o bean estava mantendo.
     *
     * O CDI chama esse método antes de começar a destruir o bean.
     */
    public void clear() {
    }
}
