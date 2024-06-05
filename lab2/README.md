# Сервис по учету котиков и их владельцев

## Существующая информация о котиках:

- **Имя**
- **Дата рождения**
- **Порода**
- **Цвет** (один из заранее заданных вариантов)
- **Хозяин**
- **Список котиков, с которыми дружит этот котик** (из представленных в базе)

## Существующая информация о хозяевах:

- **Имя**
- **Дата рождения**
- **Список котиков**

## Архитектура

Сервис должен реализовывать архитектуру **Сontroller-Service-Dao**.

## Хранение данных

Вся информация хранится в БД **PostgreSQL**. Для связи с БД должен использоваться **Hibernate**.

## Сборка проекта

Проект должен собираться с помощью **Maven** или **Gradle** (на выбор студента). Слой доступа к данным и сервисный слой должны являться двумя разными модулями Maven/Gradle. При этом проект должен полностью собираться одной командой.
