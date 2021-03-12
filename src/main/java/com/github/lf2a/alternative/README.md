# @Alternative

Quando você tem mais de uma versão de um bean que usa para propósitos diferentes, pode escolher entre eles durante a fase de desenvolvimento injetando um qualificador ou outro.

Em vez de alterar o código-fonte de seu aplicativo, no entanto, você pode fazer a escolha no momento da implantação usando alternativas.

Alternativas são comumente usadas para finalidades como as seguintes:
- Para lidar com a lógica de negócios específica do cliente que é determinada em tempo de execução
- Para especificar beans que são válidos para um cenário de implantação específico (por exemplo, quando as leis de imposto sobre vendas específicas do país exigem lógica comercial de imposto sobre vendas específico do país)
- Para criar versões fictícias (simuladas) de beans a serem usados para teste.

Para disponibilizar um bean para pesquisa, injeção ou resolução EL usando este mecanismo, dê a ele uma anotação `javax.enterprise.inject.Alternative` e, em seguida, use o elemento `alternatives` para especificá-lo no arquivo `beans.xml`.

Por exemplo, você pode desejar criar uma versão completa de um bean e também uma versão mais simples que você use apenas para certos tipos de teste.

```java
@Alternative
public class TestCarroImpl implements Carro {}
```

A versão completa não é anotada:

```java
@Alternative
public class CarroImpl implements Carro {}
```

O bean gerenciado injeta uma instância da interface `Carro`:

```java
@Inject
Carro carro
```

A versão alternativa do bean é usada pelo aplicativo apenas se essa versão for declarada da seguinte forma no arquivo `beans.xml`:

```xml
<beans>
    <alternatives>
        <class>com.github.lf2a.alternative.TestCarroImpl</class>
    </alternatives>
</beans>
```

Se o elemento `alternatives` estiver comentado no arquivo `beans.xml`, a classe `CarroImpl` será usada.

Você também pode ter vários beans que implementam a mesma interface, todos anotados com `@Alternative`. Nesse caso, você deve especificar no arquivo `beans.xml` qual desses beans alternativos deseja usar. Se também `CarroImpl` fossem anotados com `@Alternative`, um dos dois beans sempre teria que ser especificado no arquivo `beans.xml`.

As alternativas que você especifica no arquivo `beans.xml` se aplicam apenas às classes no mesmo arquivo. Use a anotação `@Priority` para especificar alternativas globalmente para um aplicativo que consiste em vários módulos, como no exemplo a seguir:

```java
@Alternative
@Priority(Interceptor.Priority.APPLICATION+10)
public class TestCarroImpl implements Carro {}
```

A alternativa com valor de prioridade mais alto é selecionada se vários beans alternativos que implementam a mesma interface forem anotados com `@Priority`. Você não precisa especificar a alternativa no arquivo `beans.xml` ao usar a anotação `@Priority`.

## Usando especialização

A especialização tem uma função semelhante à das alternativas, pois permite substituir um bean por outro. No entanto, você pode querer que um bean substitua o outro em todos os casos. Suponha que você definiu os seguintes dois beans:

```java
@Default @Asynchronous
public class AsynchronousService implements Service {}

@Alternative
public class MockAsynchronousService extends AsynchronousService {}
```

Se você declarasse `MockAsynchronousService` como alternativa em seu arquivo `beans.xml`, o seguinte ponto de injeção seria resolvido para `MockAsynchronousService`:

```java
@Inject
Service service;
```

O seguinte, no entanto, resolveria `AsynchronousService` em vez de `MockAsynchronousService`, porque `MockAsynchronousService` não tem o qualificador `@Asynchronous`:

```java
@Inject
@Asynchronous Service service;
```

Para ter certeza de que `MockAsynchronousService` sempre foi injetado, você teria que implementar todos os tipos de bean e qualificadores de bean `AsynchronousService`. No entanto, se `AsynchronousService` tiver declarado um método produtor ou método observador, mesmo esse mecanismo complicado não garantiria que o outro bean nunca fosse invocado. A especialização fornece um mecanismo mais simples.

A especialização acontece tanto no tempo de desenvolvimento quanto no tempo de execução. Se você declarar que um bean especializa outro, ele estende a outra classe de bean e, em tempo de execução, o bean especializado substitui completamente o outro bean. Se o primeiro bean for produzido por meio de um método produtor, você também deve sobrescrever o método produtor.

Você especializa um bean atribuindo a ele a anotação `javax.enterprise.inject.Specializes`. Por exemplo, você pode declarar um bean da seguinte maneira:

```java
@Specializes
public class MockAsynchronousService extends AsynchronousService {}
```

Nesse caso, a classe `MockAsynchronousService` sempre será chamada em vez da classe `AsynchronousService`.

Normalmente, um bean marcado com a anotação `@Specializes` também é uma alternativa e é declarado como uma alternativa no arquivo `beans.xml`. Esse bean deve substituir a implementação padrão, e a implementação alternativa herda automaticamente todos os qualificadores da implementação padrão, bem como seu nome EL, se houver.
