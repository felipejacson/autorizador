**Autorizador**
----
  Esse projeto tem como objetivo a inserção e a consulta de procedimentos para um sistema de controle de autorizações de procedimentos médicos.
  <br /> 
  
  O sistema possui os seguintes endpoints REST com seus respectivos retornos:
* **Consultando um procedimento**

    `/autorizador/procedimento/{procedimentoId}/idade/{idade}/sexo/{sexo}`

    * **Method:**
  
        `GET`
  
    *  **Parâmetros da URL (Obrigatórios):**
 
       `procedimentoId=[long] / 1234` <br/>
       `idade=[int] / 25`<br/>
       `sexo=[char] / [M | F]`

    * **Success Response:**
      
      **Code:** `200` <br />
      **Content:** `SIM | NAO`
 
    * **Error Response:**<br />
        **Code:** `404 NOT FOUND` (Caso algum parâmetro não seja inserido) <br/>
        **Content:** `{"timestamp": "current timestamp","status": 404,"error": "Not Found","message": "","path": "/autorizador/procedimento//idade/{idade}/sexo/{sexo}"}`

      OU
    
        **Code:** `400 BAD REQUEST` (Caso seja inserido algum parâmetro inválido)<br />
        **Content:** `{"timestamp": "current timestamp","status": 400,"error": "Bad Request","message": "","path": "/autorizador/procedimento/INVALID_PARAM/idade/INVALID_PARAM/sexo/INVALID_PARAM"}`

    * **URL de exemplo:**
    
      `/autorizador/procedimento/1234/idade/10/sexo/M`

* **Inserindo um procedimento**

  `/autorizador/cadastro/procedimento/{procedimentoId}/idade/{idade}/sexo/{sexo}/autoriza/{autoriza}`

    * **Method:**
      
      `POST`
      
    *  **Parâmetros da URL (Obrigatórios):**
     
       `procedimentoId=[long] / 1234` <br/>
       `idade=[int] / 25`<br/>
       `sexo=[char] / [M | F]`<br/>
       `autoriza=[String] / [NAO | SIM]`
    
    * **Success Response:**
      
      **Code:** `200` <br />
      **Content:** `200 OK`
     
    * **Error Response:**
    
        **Code:** `404 NOT FOUND` (Caso algum parâmetro não seja inserido)<br />
        **Content:** `{"timestamp": "current timestamp","status": 404,"error": "Not Found","message": "","path": "/autorizador/cadastro/procedimento//idade/{idade}/sexo/{sexo}"}`
    
      OU
    
        **Code:** `400 BAD REQUEST` (Caso esteja tentando inserir uma autorização em duplicidade)<br />
        **Content:** `Dados já existentes`
        
      OU
      
        **Code:** `400 BAD REQUEST` (Caso seja inserido algum parâmetro inválido)<br />
        **Content:** `{"timestamp": "current timestamp","status": 400,"error": "Bad Request","message": "","path": "/autorizador/cadastro/procedimento/INVALID_PARAM/idade/INVALID_PARAM/sexo/INVALID_PARAM"}`
    
    * **URL de exemplo:**
    
      `/autorizador/cadastro/procedimento/1234/idade/10/sexo/M/autoriza/SIM`

* **Tecnologias Usadas:** <br /><br />
  O projeto está usando as seguintes tecnologias: <br />
  * **Java 8**
  * **Spring Boot**
  * **JUnit**
  * **PostgreSQL**
  * **Maven**
  * **Docker**

* **Como executar o projeto:** <br /><br />
  Para executar o projeto execute os seguintes comandos na pasta principal do projeto:
    <br />
    * **`mvn clean package -P prod`**<br />
    * **`docker-compose -f docker-compose.yml up -d`**

* **Como executar os testes unitários do projeto:** <br /><br />
  Para executar os testes unitários do projeto execute o seguinte comando na pasta principal do projeto:
    <br />
    * **`mvn test`**

* **IMPORTANTE:** <br /><br />
  **Quando o sistema é executado pela primeira vez o banco de dados está sem nenhum registro.
  Para poder realizar a busca de um determinado procedimento, primeiramente, este deve ser inserido.**