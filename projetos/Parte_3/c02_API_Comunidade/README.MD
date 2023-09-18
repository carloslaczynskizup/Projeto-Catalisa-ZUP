
# Apenas uma idea:

Um rascunho de um pseudocódigo para uma API que ajude a rastrear a doação de alimentos para combater
o desperdício de alimentos e apoiar comunidades carentes.

Nem todos os packages foram incrementados. O código encontra-se com muitos erros ainda não solucionados.

Principalmente na parte de Security, dando erro do não autorização pelo Postman. 
Não consegui solucionar.

Quando retirado a parte do Security, a API roda tranquilamente.

# Package model contendo as classes de modelo

```java
package model {
class FoodDonation {
String id
String donorName
String foodType
Date donationDate
Location donationLocation
}

class Location {
String id
String name
String address
}

class User {
String id
String username
String password
List<Role> roles
}

class Role {
String id
String name
}
}
```


# Package dto contendo classes de transferência de dados

```java
package dto {
class FoodDonationDto {
String donorName
String foodType
Date donationDate
LocationDto donationLocation
}

class LocationDto {
String name
String address
}

class UserDto {
String username
String password
}
}
```

# Package repository contendo repositórios de dados

```java
package repository {
interface FoodDonationRepository {
List<FoodDonation> findAll()
FoodDonation findById(String id)
FoodDonation save(FoodDonation foodDonation)
void deleteById(String id)
}

interface LocationRepository {
List<Location> findAll()
Location findById(String id)
Location save(Location location)
void deleteById(String id)
}

interface UserRepository {
User findByUsername(String username)
User save(User user)
}
}
```

# Package service contendo classes de serviço

```java
package service {
class FoodDonationService {
List<FoodDonation> getAllDonations()
FoodDonation getDonationById(String id)
FoodDonation createDonation(FoodDonationDto donationDto)
void deleteDonation(String id)
}

class LocationService {
List<Location> getAllLocations()
Location getLocationById(String id)
Location createLocation(LocationDto locationDto)
void deleteLocation(String id)
}

class UserService {
User registerUser(UserDto userDto)
User authenticateUser(String username, String password)
}
}
```

# Package controller contendo controladores REST

```java
package controller {
class FoodDonationController {
List<FoodDonationDto> getAllDonations()
FoodDonationDto getDonationById(String id)
FoodDonationDto createDonation(FoodDonationDto donationDto)
void deleteDonation(String id)
}

class LocationController {
List<LocationDto> getAllLocations()
LocationDto getLocationById(String id)
LocationDto createLocation(LocationDto locationDto)
void deleteLocation(String id)
}

class UserController {
UserDto registerUser(UserDto userDto)
UserDto authenticateUser(UserDto userDto)
}
}
```

# Configuração de segurança com JWT

```java
security {
jwt {
secret: "chave_secreta"
expiration: 3600 # tempo de expiração em segundos
}
}
```

# Configuração de banco de dados

```java
database {
primary: postgres
secondary: h2
postgres {
url: "jdbc:postgresql://localhost:5432/fooddonation"
username: "postgres"
password: "senha"
}
h2 {
url: "jdbc:h2:mem:fooddonation"
username: "sa"
password: ""
}
}
```

# Configuração do Postman

```java
postman {
collection: "food-donation-api.postman_collection.json"
}
```

# Classe principal do aplicativo Spring Boot

```java
class FoodDonationApplication {
static void main(String[] args) {
SpringApplication.run(FoodDonationApplication.class, args)
}
}
```





