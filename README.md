# Fundação Nova Escola Rest Application

Esta é uma aplicação web simples aplicadno conceitos de construção de API usando Java e Springboot.

Os dados utilizados são gravados apenas em memória e serão perdidos ao resetar o servidor.


## Executando
Para rodar a aplicação localmente, executa a classe principal localizada em **project-dir**\src\main\java\com\nova\escola\prova\ProvaApplication.java


## Inserindo dados
**host** = <http://localhost:8080>                        se estiver rodando a aplicação localmente

**host** = <https://canovasnovaescola.herokuapp.com>    caso esteja utilizando a aplicação de teste disponível no Heroku

Para inserir um cliente, faça uma requisição POST para **host**:/cliente/

Os parâmetros são:

nome **obrigatório**, email e dataNascimento no formato dd/mm/aaaa


<img src="https://imgbbb.com/images/2020/02/07/novaescolaex.png">


## Listando

Para ver todos os Clientes cadastrados, acesse a URl **host**/cliente/

É possível também informar parâmetros de paginação:

**host**/cliente?limite=**número de resultados por página**&pagina=**número da página, começando em 1**


Para recuperar informações de um cliente específico, envie uma requsução GET para:
**host**/cliente/**ID do Cliente**

## Alterando dados
Para alterar um Cliente, envie uma requisição PUT para
**host**/cliente?id=**ID do Cliente**, informando o JSON dos dados alterados, como no exemplo

<img src="https://imgbbb.com/images/2020/02/07/novaescolaputex.png">

## Removendo um Cliente

Para remover um cliente, basta enviar uma requisição DEL para o endereço:
**host**/cliente/**ID do Cliente**

## Integração Contínua
A cada novo commit no projeto, um novo build é disparado no Travis, como pode ser acompanhado em:

