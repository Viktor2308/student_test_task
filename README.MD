# Тестовое задание на должность Java Developer

Данный сервис имеет возможности:

1. добавить нового студента в список;
2. удалить существующего студента;
3. отредактировать существующего студента;
4. внести изменения названий (поле text) в справочнике успеваемости. 

База данных PostgreSQL, для развртывания используется библиотека миграций Flyway
Исходные значения будут добавлены при старте приложения из файла "V1_2__insert_data.sql":
[{id 2, text "неуд"}, {id 3, text "уд"}, {id 4, text "хор"}, {id 5, text "отл"}]

### Запуск:
git clone https://github.com/Viktor2308/student_test_task.git

gradle build

docker compose -f docker-compose.yml up

Сервис запускается на порту: 8090
Документации Swagger http://localhost:8090/webjars/swagger-ui/index.html

## Стэк технологий:
- Java 17
- Spring Boot webflux 3.1.4
- Spring data r2dbc
- Postgresql
- flyway
- Jakarta validation
- Lombok
- mapstruct
- swagger
- Junit tests



