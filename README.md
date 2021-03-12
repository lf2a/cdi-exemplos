# Contexts and Dependency Injection (CDI) - Exemplos

Contexts and Dependency Injection (CDI) permite que seus objetos tenham suas dependências fornecidas automaticamente, ao invés de criá-las ou recebê-las como parâmetros. O CDI também gerencia o ciclo de vida dessas dependências.

CDI é um conjunto de serviços que, usados juntos, tornam mais fácil para os desenvolvedores usarem enterprise beans junto com a tecnologia JavaServer Faces em aplicativos da web. Projetado para uso com objetos com estado, o CDI também tem muitos usos mais amplos, permitindo aos desenvolvedores uma grande flexibilidade para integrar vários tipos de componentes de uma forma fracamente acoplada, mas com segurança

Os serviços mais fundamentais fornecidos pelo CDI são os seguintes.
- Contextos: este serviço permite vincular o ciclo de vida e as interações de componentes com estado a contextos de ciclo de vida extensíveis, mas bem definidos.
- Injeção de dependência: este serviço permite que você injete componentes em um aplicativo de uma maneira segura de tipo e escolha no momento da implantação qual implementação de uma interface específica injetar.

Um tipo de bean define um tipo de bean visível para o cliente. Quase qualquer tipo de Java pode ser um tipo de bean.
- Um tipo de bean pode ser uma interface, uma classe concreta ou uma classe abstrata e pode ser declarado final ou ter métodos finais.
- Um tipo de bean pode ser um tipo parametrizado com parâmetros de tipo e variáveis ​​de tipo.
- Um tipo de bean pode ser um tipo de array. Dois tipos de array são considerados idênticos apenas se o tipo de elemento for idêntico.
- Um tipo de feijão pode ser um tipo primitivo. Os tipos primitivos são considerados idênticos aos seus tipos de wrapper correspondentes em java.lang.
- Um tipo de feijão pode ser um tipo bruto.

# Escopos

## Solicitação

#### @RequestScoped
A interação de um usuário com um aplicativo da web em uma única solicitação HTTP.

## Sessão

```java
@SessionScoped
```
A interação de um usuário com um aplicativo da web em várias solicitações HTTP.

## Aplicação

```java
@ApplicationScoped
```
Estado compartilhado em todas as interações dos usuários com um aplicativo da web.

## Dependente

```java
@Dependent
```
O escopo padrão se nenhum for especificado; significa que existe um objeto para servir exatamente a um cliente (bean) e tem o mesmo ciclo de vida desse cliente (bean).

## Conversação

```java
@ConversationScoped
```

A interação de um usuário com um servlet, incluindo aplicativos JavaServer Faces. O escopo da conversa existe dentro dos limites controlados pelo desenvolvedor que se estendem por várias solicitações para conversas de longa duração. Todas as conversas de longa duração têm como escopo uma determinada sessão de servlet HTTP e não podem cruzar os limites da sessão.

#
Todos os escopos predefinidos, exceto escopos contextuais `@Dependent`. CDI coloca beans de escopo contextual no contexto cujo ciclo de vida é definido pelas especificações Java EE. Por exemplo, um contexto de sessão e seus beans existem durante o tempo de vida de uma sessão HTTP. Referências injetadas aos grãos são contextualmente cientes. As referências sempre se aplicam ao bean que está associado ao contexto para o encadeamento que está fazendo a referência. O contêiner CDI garante que os objetos sejam criados e injetados no momento correto, conforme determinado pelo escopo especificado para esses objetos.

Os beans que usam escopo de sessão, aplicativo ou conversação devem ser serializáveis, mas os beans que usam escopo de solicitação não precisam ser serializáveis.

Para tornar um bean acessível por meio do EL, use o @Namedqualificador integrado:

```java
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Printer {

    @Inject
    @Informal
    private Greeting greeting;
}
```

O qualificador `@Named` permite que você acesse o bean usando o nome do bean, com a primeira letra em minúsculas. Por exemplo, uma página Facelets se referiria ao bean como `printer`.

Você pode especificar um argumento para o qualificador `@Named` para usar um nome não padrão:

```java
@Named("MyPrinter")
```

Com essa anotação, a página Facelets se referiria ao bean como `MyPrinter`.

#
Existem dois tipos de arquivos de bean: arquivos de bean explícitos e arquivos de bean implícitos.

Um arquivo de bean explícito é um arquivo que contém um descritor de implantação (beans.xml), que pode ser um arquivo vazio, não contém nenhum número de versão ou contém o número de versão 1.1 com o atributo `bean-discovery-mode` definido como `all`.

O CDI pode gerenciar e injetar qualquer bean em um arquivo explícito, exceto aqueles anotados com `@Vetoed`.

Um arquivo de bean implícito é um arquivo que contém alguns beans anotados com um tipo de escopo, não contém descritor de implementação (beans.xml) ou contém um descritor de implementação (beans.xml) com o atributo `bean-discovery-mode` definido como `annotated`.

Em um arquivo implícito, o CDI só pode gerenciar e injetar beans anotados com um tipo de escopo.
