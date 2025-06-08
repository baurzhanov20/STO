# СТО Сервис (Service Station Management System)

## Описание проекта
Сервис для управления заявками на ремонт автомобилей в автосервисе. Включает полный цикл от создания заявки до уведомления клиента о завершении работ.

## Функционал
- Создание/просмотр заявок
- Изменение статусов заявок
- История изменений статусов
- Мок-уведомления клиентов
- Фильтрация заявок по клиенту и статусу

## Технологический стек
- Java 21
- Spring Boot 3.4.2
- PostgreSQL
- Kafka (опционально)
- Docker

## Запуск проекта

### Требования
- Установленные Docker и Docker Compose
- Maven (для локальной сборки)

### 1. Сборка и запуск через Docker Compose
```bash
docker-compose up --build
2. Локальная сборка (альтернатива)
bash
mvn clean package
docker-compose up
3. Остановка сервисов
bash
docker-compose down
Доступ к сервисам
Приложение: http://localhost:8080

PostgreSQL: localhost:5432 (логин/пароль: sto/sto123)

Kafka (если используется): localhost:9092

API Endpoints
Основные эндпоинты
Метод	Путь	Описание
POST	/api/requests	Создать новую заявку
GET	/api/requests/client/{name}	Получить заявки по клиенту
GET	/api/requests/status/{status}	Получить заявки по статусу
PATCH	/api/requests/{id}/status	Изменить статус заявки
GET	/api/requests/{id}/history	Получить историю изменений
Примеры статусов
NEW

PROCESSING

IN_PROGRESS

COMPLETED# СТО Сервис (Service Station Management System)

## Описание проекта
Сервис для управления заявками на ремонт автомобилей в автосервисе. Включает полный цикл от создания заявки до уведомления клиента о завершении работ.

## Функционал
- Создание/просмотр заявок
- Изменение статусов заявок
- История изменений статусов
- Мок-уведомления клиентов
- Фильтрация заявок по клиенту и статусу

## Технологический стек
- Java 21
- Spring Boot 3.4.2
- PostgreSQL
- Kafka (опционально)
- Docker

## Запуск проекта

### Требования
- Установленные Docker и Docker Compose
- Maven (для локальной сборки)

### 1. Сборка и запуск через Docker Compose
```bash
docker-compose up --build
2. Локальная сборка (альтернатива)
bash
mvn clean package
docker-compose up
3. Остановка сервисов
bash
docker-compose down
Доступ к сервисам
Приложение: http://localhost:8080

PostgreSQL: localhost:5432 (логин/пароль: sto/sto123)

Kafka (если используется): localhost:9092

API Endpoints
Основные эндпоинты
Метод	Путь	Описание
POST	/api/requests	Создать новую заявку
GET	/api/requests/client/{name}	Получить заявки по клиенту
GET	/api/requests/status/{status}	Получить заявки по статусу
PATCH	/api/requests/{id}/status	Изменить статус заявки
GET	/api/requests/{id}/history	Получить историю изменений
Примеры статусов
NEW

PROCESSING

IN_PROGRESS

COMPLETED
