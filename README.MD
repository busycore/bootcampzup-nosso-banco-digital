<h1 align="center">Nosso Banco Digital - Bootcamp ZUP</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000" />
</p>

> This project was created to develop a digital bank API, caring about the architecture and code efficiency regarding the code maintenance and evolution.

## ⚙ Requirements

```sh
java >=1.8
spring-boot >=2.4.0
maven >= 3.6.3
postgreSQL >= 11.9
```

## 🔨 Install

```sh
git clone this repository
run maven and after it build it in your favorite IDE, or
mvnw clean package
be happy :)
```

## 💻 Usage

```sh
POST http://localhost:8080/account-proposals/
body:
{
  "name": "Cebolaceo",
	"surname":"Cebola",
	"birth_date": "2002-10-10",
	"cpf":"12517993025",
	"email":"cebolinha@cebolinha.com"
}
header-location :http://localhost:8080/account-proposals/{Account_Proposal_ID}/address/

___

POST http://localhost:8080/account-proposals/{Account_Proposal_ID}/address/
body:
{
  "cep": "02142-100",
	"neighborhood": "Bairro do Limoeiro",
	"complement":"Atrás do campinho",
	"street":"Rua da Monica",
	"city":"Limeira",
	"state":"SP"
}
header-location :http://localhost:8080/account-proposals/{Account_Proposal_ID}/cpf-upload/

___

POST http://localhost:8080/account-proposals/{Account_Proposal_ID}/cpf-upload/

Upload multipart : "file"

header-location :http://localhost:8080/account-proposals/{Account_Proposal_ID}/review/

___

GET http://localhost:8080/account-proposals/{Account_Proposal_ID}/review/
response :
{
  "id": 129,
  "address": {
    "id": 130,
    "cep": "02142-100",
    "complement": "Atrás do campinho",
    "street": "Rua da Monica",
    "neighborhood": "Bairro do Limoeiro",
    "city": "Limeira",
    "state": "SP"
  },
  "customer": {
    "id": 128,
    "name": "Cebolaceo",
    "surname": "Cebola",
    "birth_date": "2002-10-10",
    "cpf": "12517993025",
    "email": "cebolinha@cebolinha.com"
  },
  "cpfimagePictureURL": "C:\\Source\\BootcampZup\\levi.jpg",
  "status": "WAITING_CONFIRMATION"
}
header-location :http://localhost:8080/account-proposals/{Account_Proposal_ID}/commit/
___

POST http://localhost:8080/account-proposals/129/commit/
___

POST http://localhost:8080/account-proposals/129/deny/

___
```

## Author

👤 **Matheus Vargem**

- Github: [@busycore](https://github.com/busycore)
- LinkedIn: [@matheusvargem](https://linkedin.com/in/matheusvargem)
