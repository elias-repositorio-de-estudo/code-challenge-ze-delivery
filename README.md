# O que é este projeto?

Um projeto que com base na area de cobertura de uma loja vai ver se atende a determinado lugar ou nao 


# Tecnologias usadas 
- Java 11
- Spring boot
- Flyway
- Postgres com postgis
- Docker

# Buildando a imagem java

Use o comando mvn clean install -DskipTests para buildar a imagem 

# Subindo o docker

Use o comando docker-compose up --build e todos os endpoints vao estar disponiveis com isso ja temos a imagem que nos disponibiliza fazer o deploy! 
