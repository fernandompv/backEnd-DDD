# Api rest following api-first with spring boot

This project it´s a demo project that get values from H2 database using web flux and performing test

# Entities

## Price

| id | brandId | startDate | endDate | priceList | productId | price | priority | currency |
| -- | ------| ----- | ------| ----- | ------| ----- | ------| ----- |

# [Swagger documentation]

Example curl to get prices:
```bash
curl -X 'GET' \
'http://localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2099-12-12T00%3A00' \
-H 'accept: application/json'
```

# Postman collection for End-To-End test:
```json
{
  "info": {
    "_postman_id": "394fa8fe-0a50-46a2-be24-cacafffc53b3",
    "name": "Inditex demo",
    "schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
    "_exporter_id": "27646387"
  },
  "item": [
    {
      "name": "Get price by date #1",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-14T10:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-14T10:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price by date #2",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-14T16:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-14T16:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price by date #3",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-14T21:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-14T21:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price by date #4",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-15T10:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-15T10:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price by date #5",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-16T21:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-16T21:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price empty",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2023-06-14T10:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2023-06-14T10:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price bad request",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=camiseta&brandId=1&priceStartDate=2020-06-14T10:00",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "camiseta"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-14T10:00"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get price bad format date",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:9999/api/price?productId=35455&brandId=1&priceStartDate=2020-06-14T10:00.00Z",
          "host": [
            "localhost"
          ],
          "port": "9999",
          "path": [
            "api",
            "price"
          ],
          "query": [
            {
              "key": "productId",
              "value": "35455"
            },
            {
              "key": "brandId",
              "value": "1"
            },
            {
              "key": "priceStartDate",
              "value": "2020-06-14T10:00.00Z"
            }
          ]
        }
      },
      "response": []
    }
  ]
}
```

[Swagger documentation]: <http://localhost:9999/swagger-doc/webjars/swagger-ui/index.html>