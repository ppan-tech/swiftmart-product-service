# swiftmart-product-service
# this interacts with sandbox 

# REST API examples using curl:


>>> HTTP GET:
curl --location 'http://localhost:9190/api/swiftmart-products/v1/products/1' 

Response:
{
"id": 1,
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 109.95,
"description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
"imageUrl": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}


>>> HTTP POST:

curl --location 'http://localhost:9190/api/swiftmart-products/v1/products' \
--header 'Content-Type: application/json' \
--data '{
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 88109.95,
"description": "Your perfect pack for everyday",
"imageUrl": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}'

Response:
{
"id": 21,
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 88109.95,
"description": "Your perfect pack for everyday",
"imageUrl": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}


>>> HTTP PUT:
curl --location --request PUT 'http://localhost:9190/api/swiftmart-products/v1/products/1' \
--header 'Content-Type: application/json' \
--data '{
"id": 1,
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 88109.95,
"description": "Your perfect pack for everyday",
"imageUrl": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}'

Response:
{
"id": 1,
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 88109.95,
"description": "Your perfect pack for everyday",
"imageUrl": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}

>>> HTTP DELETE:
curl --location --request DELETE 'http://localhost:9190/api/swiftmart-products/v1/products/21'


>>>HTTP PATCH:

curl --location --request PATCH 'http://localhost:9190/api/swiftmart-products/v1/products/1' \
--header 'Content-Type: application/json-patch+json' \
--data '[{
"op": "replace",
"path": "/price",
"value": 0.99
}]'

Response:
{
"id": 1,
"name": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 0.99,
"description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
"imageUrl": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"category": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
}

