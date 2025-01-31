@echo off
PATH=%JAVA_HOME%;%PATH%
title Запуск приложения

echo Собираю приложение...
call mvn clean install

echo Запускаю приложение...
:: Определяем путь к JAR-файлу
for /R target %%f in (*.war) do set WAR_FILE=%%f

:: Запускаем собранный JAR-файл
call javaw -jar "%WAR_FILE%"

:: Пауза для просмотра результатов
pause