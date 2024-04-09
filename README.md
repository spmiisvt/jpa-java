# Практическое занятие Spring Data JPA

Перед началом необходимо создать базу данных, которую мы будем запускать в docker контейнере

Для создания базы данных необходимо выполнить скрипт в файле `docker-compose.yml`

```shell
docker compose up -d
```
> Имя пользователя: `studentname`
> 
> Пароль: `studentpassword`
> 
> Порт: `5332`

После запуска контейнера необходимо создать базу данных `studentdb` для этого заходим в терминал и пишем

```shell
docker exec -it postgres bash
```
далее заходим в терминальную утилиту psql под созданным пользователем
```
psql -U studentname
```
и создаем базу данных с именем `studentdb`
```
CREATE DATABASE studentdb;
```
Проверяем что база данных добавилась с помощью команды:
```
\l
```
если всё хорошо, выходим несколько раз выполним команду:
```
Ctrl + d
```
---
* Пробуем запустить приложение и надеемся что нет ошибок
---
В директории `sources` расположены дополнительные файлы.

## Занятие 2
1. (branch lesson-2-1) Исследуем стандартные методы JpaRepository
2. (branch lesson-2-2) Создаем собственные запросы.

> Ссылка на документацию по составлению запроса:
> 
> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

3. (branch lesson-2-3) Управляем запросами в ручную и модифицируем запрос удаления по умолчанию

---
## Занятие 3
Сортировка и пагинация

Перед тем как начать нужно добавить еще одну зависимость для автоматического заполнения базы данных
```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```
> JavaFaker - библиотека для генерации данных (Имена, фамилии, числа и т.д.)
> 
> https://github.com/DiUS/java-faker?tab=readme-ov-file

