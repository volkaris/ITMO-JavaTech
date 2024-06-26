## Переход на микросервисную архитектуру

### Микросервисы

1. **Микросервис доступа к котикам**
2. **Микросервис доступа к владельцам**
3. **Микросервис с внешними интерфейсами**

### Перенос эндпоинтов и авторизации

Все созданные ранее эндпоинты и авторизация переезжают на третий микросервис.

### Взаимодействие между микросервисами

- Общение между микросервисами происходит посредством **RabbitMQ/Kafka** (на выбор студента).
- Недопустимо передавать через RabbitMQ/Kafka JPA сущности.
- Рекомендуется создать отдельные оберточные классы для передачи данных между микросервисами.

### Базы данных

- Сервисы доступа к котикам и доступа к владельцам могут быть подключены к одной БД или иметь разные БД.
- В случае использования разных БД, запросы должны быть раздельными (один запрос на каждую БД).

### Рекомендации

- Выделить модуль с JPA сущностями в отдельный подключаемый модуль.
