# Projeto Fábrica de Software - 2023

Objetivo deste projeto é capacitar os alunos da disciplina de Fábrica de Software a desenvolver um sistema web utilizando o Spring Framework.

## Diagrama de Entidades

![Diagrama de Entidades](/diagramas/diagramaentidades.png)


[Link]()
## Associação Muitos para Um (Cliente e Cidade)
1. A entidade Cliente possui uma associação @ManyToOne para a entidade Cidade [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/java/br/univille/projfabsoft2023/entity/Cliente.java)
2. Criar o repositório para a classe cidade [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/java/br/univille/projfabsoft2023/repository/CidadeRepository.java)
3. Caso o cadastro da cidade não esteja implementado, conectar no banco de dados H2 e realizar um INSERT na tabela de cidade [Link](http://localhost:8080/banco_de_dados)
4. Criar a interface do serviço para a classe Cidade com o método public List<Cidade> getAll(); [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/java/br/univille/projfabsoft2023/service/CidadeService.java)
5. Criar a classe de implementação do serviço da Cidade, utilizando o repositório, chame o método findAll() para retornar todas as instâncias da classe [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/java/br/univille/projfabsoft2023/service/impl/CidadeServiceImpl.java)
6. No Controlador do Cliente, incluir o serviço da classe Cidade [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/java/br/univille/projfabsoft2023/controller/ClienteController.java)
7. No método novo, executar o método getAll() do serviço da Cidade para obter a lista de Cidades, construir um objeto HashMap e incluir a instância do objeto Cliente e a lista de cidades
8. Alterar a tela do formulário do cliente para incluir um TAG Select para mostrar a lista de Cidades [Link](https://github.com/waltercoan/FABSOFT2023/blob/main/src/main/resources/templates/cliente/form.html)
9. No método alterar, executar o método getAll() do serviço da Cidade para obter a lista de Cidades, construir um objeto HashMap e incluir a instância do objeto Cliente e a lista de cidades


## Validação dos campos de entrada

1. Incluir no arquivo pom.xml a dependência a seguir, reiniciar o projeto Spring
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. Alterar o código das entidades para incluir as anotações do pacote (javax.validation.constraints) com as validações

```
@Column(length = 600)
@Size(min = 1, max = 40,message = "Tamanho do campo entre 1 e 40")
@NotBlank(message = "Campo nome não pode ser em branco")
private String nome;
```

3. Alterar o código do metodo SAVE do controlador para incluir a anotação @Valid no parâmetro da entidade, receber por parâmetro BindingResult e utilizar um if para verificar se os dados enviados pelo formulário não forem válidos retornar para a tela do formulário.
```
@PostMapping(params = "form")
public ModelAndView save(@Valid Cliente cliente,BindingResult bindingResult){

    if(bindingResult.hasErrors()){
        return new ModelAndView("cliente/form","cliente",cliente);
    }
    
    clienteService.save(cliente);
    return new ModelAndView("redirect:/cliente");
}
```

4. Incluir em cada form-group um tag HTML para mostrar a mensagem de erro do campo inválido utilizando o IF do thymeleaf para verificar se o campo retornou com erro.
```
<div class="form-group">
    <label for="txtNome">Nome</label>
    <input type="text" class="form-control"
        th:field="*{nome}" id="txtNome">
    <p th:if="${#fields.hasErrors('nome')}"
        th:errors="*{nome}"
        class="alert alert-danger"></p>
</div>
```