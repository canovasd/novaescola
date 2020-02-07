# Funda��o Nova Escola Rest Application

Esta � uma aplica��o web simples aplicadno conceitos de constru��o de API usando Java e Springboot.

Os dados utilizados s�o gravados apenas em mem�ria e ser�o perdidos ao resetar o servidor.


## Executando
Para rodar a aplica��o localmente, executa a classe principal localizada em **project-dir**\src\main\java\com\nova\escola\prova\ProvaApplication.java


## Inserindo dados
**host** = <http://localhost:8080>                        se estiver rodando a aplica��o localmente

**host** = <https://canovasnovaescola.herokuapp.com>    caso esteja utilizando a aplica��o de teste dispon�vel no Heroku

Para inserir um cliente, fa�a uma requisi��o POST para **host**:/cliente/

Os par�metros s�o:

nome **obrigat�rio**, email e dataNascimento no formato dd/mm/aaaa


<img src="https://imgbbb.com/images/2020/02/07/novaescolaex.png">


## Listando

Para ver todos os Clientes cadastrados, acesse a URl **host**/cliente/

� poss�vel tamb�m informar par�metros de pagina��o:

**host**/cliente?limite=**n�mero de resultados por p�gina**&pagina=**n�mero da p�gina, come�ando em 1**


Para recuperar informa��es de um cliente espec�fico, envie uma requsu��o GET para:
**host**/cliente/**ID do Cliente**

## Alterando dados
Para alterar um Cliente, envie uma requisi��o PUT para
**host**/cliente?id=**ID do Cliente**, informando o JSON dos dados alterados, como no exemplo

<img src="https://imgbbb.com/images/2020/02/07/novaescolaputex.png">

## Removendo um Cliente

Para remover um cliente, basta enviar uma requisi��o DEL para o endere�o:
**host**/cliente/**ID do Cliente**

## Integra��o Cont�nua
A cada novo commit no projeto, um novo build � disparado no Travis, como pode ser acompanhado em:

