# СТО Сервис (Service Station Management)

## Описание
Серверное приложение для управления заявками автосервиса с поддержкой:
- Полного жизненного цикла заявок
- Истории изменений статусов
- Уведомлений клиентов (мок-реализация)
- Обработки событий через Kafka

## Технологии
- **Java 21** (Temurin)
- **Spring Boot 3.4.2**
- **PostgreSQL 14**
- **Kafka 7.5.0**
- **Docker**

## Конфигурация окружения

### Параметры БД
- Порт: `5433` (маппинг на 5432 в контейнере)
- База: `mydatabase`
- Пользователь: `myuser`
- Пароль: `1234`

### Kafka
- Порт: `9092`
- Zookeeper порт: `2181`

### Приложение
- Порт: `8081`
- Автоперезапуск при ошибках

## Запуск проекта

1. Соберите JAR-файл:
```bash
mvn clean package

Запустите сервисы:

bash
docker-compose up --build
Для остановки:

bash
docker-compose down
Доступ к сервисам
Сервис	URL	Учетные данные
Приложение	http://localhost:8081	-
PostgreSQL	jdbc:postgresql://localhost:5433/mydatabase	myuser/1234
Kafka	localhost:9092	-
API Endpoints
Основные методы
POST /api/requests - Создать заявку

GET /api/requests/{id} - Получить заявку

PATCH /api/requests/{id}/status - Изменить статус

GET /api/requests/client/{name} - Поиск по клиенту

GET /api/requests/status/{status} - Фильтр по статусу

Пример тела запроса
json
{
    "clientName": "Biba",
    "clientPhone": "12345",
    "carDetails": "MERS",
    "status": "NEW"
}
Логирование уведомлений
Все исходящие уведомления выводятся в лог:

Управление сервисами
Проверить работу Kafka
bash
docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092
Подключиться к PostgreSQL
bash
docker-compose exec postgres psql -U myuser -d mydatabase
Просмотр логов приложения
bash
docker-compose logs -f sto-app
Особенности конфигурации
Автоматическое создание/обновление таблиц (ddl-auto=update)

Показа SQL-запросов в логах

Используется Alpine-образ JDK для уменьшения размера

Технические заметки
При первом запуске может потребоваться несколько секунд для инициализации Kafka

Для полного сброса данных выполните:

bash
docker-compose down -v
Для Запусти контейнер
bash
docker-compose up
