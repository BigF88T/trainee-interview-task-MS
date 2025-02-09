@echo off
PATH=%JAVA_HOME%;%PATH%
title Запуск приложения

echo Собираю приложение...
docker-compose up

:: Пауза для просмотра результатов
pause