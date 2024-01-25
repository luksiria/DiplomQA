## Дипломный проект

Тема — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Инструкция по запуску

1. Склонировать репозиторий с GitHub и запустить его на локальном компьютере:
   git clone https://github.com/luksiria/DiplomQA.git

2. Открыть папку DiplomQA с помощью программы IntelliJ IDEA.

3. Работа с базой данных MySQl.
   1) Запуск контейнера docker:  
      docker-compose up
   2) Запуск приложения:  
      java '-Dspring.datasource.url=jdbc:mysql://localhost:3306/app' -jar ./artifacts/aqa-shop.jar
   3) Запуск тестов:  
      ./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3306/app'
   4) Сформировать отчет и открыть его в браузере:  
      ./gradlew allureReport
      ./gradlew allureServe
   5) Остановить контейнер:
      docker compose down

4. Работа с базой данных Postgres.
    1) Запуск контейнера docker:  
       docker-compose up
    2) Запуск приложения:  
       java '-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app' -jar ./artifacts/aqa-shop.jar
    3) Запуск тестов:  
       ./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'
   4) Сформировать отчет и открыть его в браузере:  
      ./gradlew allureReport
      ./gradlew allureServe
   5) Остановить контейнер:
      docker compose down
