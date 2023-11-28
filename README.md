# Grocery project

This is a learning project that allows you to create your shopping lists, copy lists, add/update/delete products, cross
off products already purchased.

Project needs JDK17

### Link to figma with UI mockup

https://www.figma.com/file/G4TTqTUgl1VqsGzfCt3dgC/List-family-app?type=design&node-id=20%3A2&mode=design&t=LtfEgRf47SljevtU-1

### Building the service:

```mvnw package```

### Creating docker image:

```docker build -t grocerylist-service:0.0.1 .```

### (Optional) Creating database docker image

```docker build -f Dockerfile.DB -t grocery-list-service-db:0.0.1 .```

### Running the service locally

Make sure to provide actual DB connection parameters:

```docker run -p 8443:8443 -e DB_URL=jdbc:postgresql://localhost:5432/postgres -e DB_USERNAME=postgres -e  DB_PASSWORD=db-password -t customer-service:0.0.1```

### Service assembly via docker-compose.yml:

```docker compose build```

### Running the service together with postgresql db container via docker-compose.yml:

```docker compose up --force-recreate```

### Go to the browser  (default user: Dud, password : 111)

https://localhost:8443/login
