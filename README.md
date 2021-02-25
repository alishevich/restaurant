# Voting system

## Technology stack

+ Java 8
+ Spring(Security, MVC, Data JPA, Test), Hibernate
+ HSQLDB
+ slf4j, junit5, Assertj, Hamcrest
+ Jackson
+ Maven

## Task

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

Build a voting system for deciding where to have lunch.

+ 2 types of users: admin and regular users
+ Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
+ Menu changes each day (admins do the updates)
+ Users can vote on which restaurant they want to have lunch at
+ Only one vote counted per user
+ If user votes again the same day:
  + If it is before 11:00 we assume that he changed his mind.
  + If it is after 11:00 then it is too late, vote can't be changed
+ Each restaurant provides a new menu each day.

## Install

Clone project:
```
git clone https://github.com/alishevich/restaurant.git
```
Deploy:
```
$ docker build -t restaurant .
$ docker run -p 8080:8080 restaurant
```
## API
Swagger documentation will be available at http://localhost:8080/restaurant/swagger-ui.html

### Authorization
Use http basic authorization. Built-in profiles:
```
Admin: admin@gmail.com : admin
User1: user1@yandex.ru : password1
User2: user2@yandex.ru : password2
```
### User vote
*Get restaurants with today menus*

`curl -s http://localhost:8080/restaurant/rest/profile/restaurants/with-menus --user user1@yandex.ru:password1`

*Vote/change vote*

`curl -s -X PATCH http://localhost:8080/restaurant/rest/profile/votes/?restaurantId=1 --user user1@yandex.ru:password1`

*Get current user vote*

`curl -s http://localhost:8080/restaurant/rest/profile/votes/with-restaurant --user user1@yandex.ru:password1`

### Admin (users)
*Get all users*

`curl -s http://localhost:8080/restaurant/rest/admin/users --user admin@gmail.com:admin`

*Get user by email*

`curl -s http://localhost:8080/restaurant/rest/admin/users/by?email=user1@yandex.ru --user admin@gmail.com:admin`

*Enable/disable user*

`curl -s -X PATCH http://localhost:8080/restaurant/rest/admin/users/2?enabled=false --user admin@gmail.com:admin`

### Admin (restaurants)

*Get all restaurants*

`curl -s http://localhost:8080/restaurant/rest/admin/restaurants --user admin@gmail.com:admin`

*Update restaurant*

`curl -s -X PUT -d '{"name":"Updated Restaurant", "address":"Updated address", "phone":"+375291111113"}' -H 'Content-Type: application/json' http://localhost:8080/restaurant/rest/admin/restaurants/0 --user admin@gmail.com:admin`

*Get restaurant*

`curl -s http://localhost:8080/restaurant/rest/admin/restaurants/0 --user admin@gmail.com:admin`

*Create restaurant*

`curl -s -X POST -d '{"name":"New Restaurant", "address":"new address", "phone":"+375291111113"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/admin/restaurants --user admin@gmail.com:admin`

### Admin (menus)

*Get menu with dishes*

`curl -s http://localhost:8080/restaurant/rest/admin/menus/2 --user admin@gmail.com:admin`

*Create new menu for restaurant*

`curl -s -X POST -d '{"date":"2021-01-29", "dishes":[{"name":"dish18", "amount":"70"}]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/admin/menus?restaurantId=0 --user admin@gmail.com:admin`

*Delete menu*

`curl -s -X DELETE http://localhost:8080/restaurant/rest/admin/menus/1 --user admin@gmail.com:admin`

*Get menu not found*

`curl -s http://localhost:8080/restaurant/rest/admin/menus/1 --user admin@gmail.com:admin`





