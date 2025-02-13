# Test Task - задание для практикантов
Репозиторий содержит выполненные задания для практикантов.

[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=Проект+на+стадии+разработки)](https://git.io/typing-svg)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Содержание
- [Технологии](#Технологии)
- [Быстрый старт](#Использование)


## Технологии
+ [Java SE 23](https://jdk.java.net/23/)
+ [Spring Framework](https://spring.io/)
+ [PostgreSQL](https://www.postgresql.org/)
+ [Hibernate](https://hibernate.org/)
+ [Tomcat](https://tomcat.apache.org/)
+ [Git](https://git-scm.com/)
+ [Docker](https://www.docker.com/)


## Использование
В каждой ветке *(t1, t2, t3 и т.д.)* внутри директории проекта лежит файл: "start_script(windows).bat"*.
Его запуск автоматически собирает maven-проект и запускает war-файл.

![img.png](img/start_script.png)

Если при запуске проекта с помощью скрипта произошла какая-либо ошибка, то
предлагается произвести сборку и запуск проекта самостоятельно:
+ В терминале прейдите в директорию проекта в которой хранится файл "pow.xml".
+ Введите команду для сборки проекта:
``` 
mvn clean install
```
+ Дождитесь процесса сборки проекта и сообщения:
+ > [INFO] BUILD SUCCESS
+ Перейдите в директорию "target" с помощью команды:
```
cd target
```
+ Введите команду с названием собранного war-файла, которая запустит приложение:
```
java -jar 'Название приложения'.war
```
## Тестирование
Ветки t1 - t5 содержат в корневой директории файл Test REST API MS-Task.postman_collection-\*Номер ветки\*.json.

Данный файл содержит описание коллекцию с HTTP-методами, которую вы можете импортировать
в свой Postman для тестирования API.
![](\img\postman-import.gif)
