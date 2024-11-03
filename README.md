<h1 align="center" style="font-weight: bold;">Cadastro 💻</h1>

<p align="center">
 <a href="#tech">Technologias</a> •
 <a href="#started">Começando</a> •
  <a href="#routes">API Endpoints</a>
</p>

<p align="center">
    <b>Uma API para gerenciar usuários.</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

- Java
- Spring Boot
- Flyway
- Postgresql
- Docker compose


<h2 id="started">🚀 Começando</h2>

Para rodar localmente, faça o clone desse repositório no seu workspace, suba os containers do docker compose

<h3>Pre requisitos</h3>


- [Maven](https://maven.apache.org/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/engine/install/)

<h3>Clonando o repositório</h3>


```bash
git clone https://github.com/TallesGuima/crud
```

<h3>Começando</h3>

Para iniciar, na pasta que você deu ```git clone```, use:

```bash
cd crud/Cadastro
```

Após isso, use este comando, ele irá limpar o projeto e em seguida construir o JAR:
```bash
mvn clean package
```
Depois navegue até o target
```bash
cd target
```
Agora vamos rodar o ".jar" que o ```mvn clear package``` gerou
```bash
java -jar Cadastro-0.0.1-SNAPSHOT.jar
```

<h2 id="routes">📍 API Endpoints</h2>

Para acessar os Endpoints de usuário, use a url ```localhost:8090/cadastro/user```
​

| rota               | descrição
|----------------------|-----------------------------------------------------
| <kbd>GET </kbd>     | Retorna os dados de todos os usuários [ver detalhes](#get-users)
| <kbd>GET /findbyid/{id}</kbd>    | Retorna os dados de um usuário em específico [ver detalhes](#get-user-id)
| <kbd>POST </kbd>     | Cria um novo usuário [ver detalhes](#post-user)
| <kbd>PUT /{id} </kbd>     | Modifica um usuário existente [ver detalhes](#put-user)
| <kbd>DELETE /{id} </kbd>     | Deleta um usuário existente [ver detalhes](#delete-user)


<h3 id="get-users">GET </h3>

**RESPOSTA**

```json
[
  {
  "id": "user_id",
  "name": "user_name",
  "login": "user@gmail.com",
  "password": "0123456789",
  "active": true
  }
]
```

<h3 id="get-user-id">GET /findbyid/{user_id} </h3>

**RESPOSTA**

```json
{
  "id": "user_id",
  "name": "user_name",
  "login": "user@gmail.com",
  "password": "0123456789",
  "active": true
}
```

<h3 id="get-users">GET /findbyname/{user_name} </h3>

**RESPOSTA**

```json
{
  "id": "user_id",
  "name": "user_name",
  "login": "user@gmail.com",
  "password": "0123456789",
  "active": true
}
```

<h3 id="post-user">POST</h3>

**REQUER**

```json
{
  "name": "test2",
  "login": "test2@gmail.com",
  "password": "0123456789",
  "active": true
}
```

**RESPOSTA**

```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "test2",
  "login": "test2@gmail.com",
  "password": "0123456789",
  "active": true
}
```
<h3 id="put-user">PUT /{user_id}</h3>

**REQUER**

```json
{
  "name": "user_name2",
  "login": "user2@gmail.com",
  "password": "new_password",
  "active": true
}
```

**RESPOSTA**

```json
{
  "id": "user_id",
  "name": "user_name2",
  "login": "user2@gmail.com",
  "password": "new_password",
  "active": true
}
```

<h3 id="get-users">DELETE /{user_id} </h3>

**RESPOSTA** 

```json
```

Para mais detalhes, ao ativar a aplicação, entre na url: ```http://localhost:8090/cadastro/swagger-ui/index.html``` 
