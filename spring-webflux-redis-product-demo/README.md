## Product service

See [Spring WebFlux Redis Integration](https://www.vinsguru.com/spring-webflux-redis/)


Product service is responsible for providing product information based on the given id. 

Data source for the product service is a PostgreSQL DB.

Application will expose 2 APIs.

- ***GET***
  API to provide the product info for the given product id

- ***PATCH***
  API to update some product information.
  
### Run

```sh
docker compose up -d
```

### Test

- Create product

```sh
curl -s -X POST localhost:8080/api/products -H 'Content-Type: application/json' -d'
{
   "id":2222222,
   "description":"Product2",
   "price":1297,
   "quantityAvailable":69
}'; echo
```

- Get product by id

```sh
curl -s -X GET localhost:8080/api/products/2222222 -H 'Content-Type: application/json'| jq '.'
```

- Update product by id

```sh
curl -s -X PUT localhost:8080/api/products/2222222 -H 'Content-Type: application/json'  -d'
{
   "id":2222222,
   "description":"Product2",
   "price":1297,
   "quantityAvailable":123
}'; echo
```

Then get product

```sh
curl -s -X GET localhost:8080/api/products/2222222 -H 'Content-Type: application/json'| jq '.'
```

 



