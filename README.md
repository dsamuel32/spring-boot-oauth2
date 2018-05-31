### Spring-Boot-Oauth2 - Spring Boot/Spring Security
Exemplo de aplicação REST com autenticação OAUTH2.

- Tecnologias Utilizadas
- Spring Boot
- Spring Security
- Spring Security OAuth
- Spring MVC
- Spring Data JPA
- Liquibase
- ModelMapper
- Swagger(Pendente)
- Mysql

### Instruções

- Criar um schema chamado acessos;

- E executar o comando 

### Build/Start do sistema

```
mv clean install spring-boot:run
```
### Chamando as APIs

- Efetue uma requisição para o endpont público:
```sh
curl http://localhost:8080/autenticacao/publico/home
```
Resposta
```sh
{
  "Pagina": "Página sem segurança!!!!"
}
```

- Efetue uma requisição para o endpont protegido:

```sh
curl http://localhost:8080/autenticacao/v1/usuario
```
Resposta 
```sh
{
  "error": "unauthorized",
  "error_description": "An Authentication object was not found in the SecurityContext"
}
```

- Endpoint de autentição
```sh
curl -X POST -vu clientapp:123456 http://localhost:8080/autenticacao/oauth/token -H "Accept: application/json" -d "password=123&username=teste&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"

```

Resposta
```sh
{
  "access_token": "ff16372e-38a7-4e29-88c2-1fb92897f558",
  "token_type": "bearer",
  "refresh_token": "f554d386-0b0a-461b-bdb2-292831cecd57",
  "expires_in": 43199,
  "scope": "read write"
}
```

- Use o access_token para se autenticar
```sh
curl http://localhost:8080/autenticacao/v1/usuario -H "Authorization: Bearer ff16372e-38a7-4e29-88c2-1fb92897f558"
```
Resposta 
```sh
{
    "nome": "teste",
    "userName": "teste",
    "enabled": true,
    "permissoes": [
        {
            "id": 1,
            "descricao": "ADM"
        }
    ]
}
```



