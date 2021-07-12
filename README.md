# Desafio Spring da Massa

### Post `/users/registerUser`
#### Descrição: Adicionar um novo usuário.
###### - body request:
    {
    "name": "Carlos",
    "type": "SELLER"
    }
    or
    {
    "name": "Carlos",
    "type": "CLIENT"
    }
###### - body response:
    {
    "id": 0,
    "name": "Carlos",
    "type": "SELLER"
    }

    Status Code 200 (tudo OK)
    Status Code 400 (Bad Request)

