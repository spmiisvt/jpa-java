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