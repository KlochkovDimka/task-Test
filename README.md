# Тестовое задание от JavaCode
## Задание:
~~~
    Добрый день, уважаемый соискатель, данное задание нацелено на выявление вашего
реального уровня в разработке на java, поэтому отнеситесь к нему, как к работе на
проекте. Выполняйте его честно и проявите себя по максимуму, удачи!
Напишите приложение, которое по REST принимает запрос вида.

POST api/v1/wallet

{
    valletId: UUID,
    operationType: DEPOSIT or WITHDRAW,
    amount: 1000
}

После выполнять логику по изменению счета в базе данных.
Также есть возможность получить баланс кошелька.

GET api/v1/wallets/{WALLET_UUID}

Стек:
    - java 8-17
    - Spring 3
    - Postgresql
    
    Должны быть написаны миграции для базы данных с помощью liquibase.
    Обратите особое внимание проблемам при работе в конкурентной среде (1000 RPS по
одному кошельку). Ни один запрос не должен быть не обработан (50Х error).
    Предусмотрите соблюдение формата ответа для заведомо неверных запросов, когда
кошелька не существует, не валидный json, или недостаточно средств.
    Приложение должно запускаться в докер контейнере, база данных тоже, вся система
должна подниматься с помощью docker-compose.
    Предусмотрите возможность настраивать различные параметры как на стороне
приложения так и базы данных без пересборки контейнеров.
    Эндпоинты должны быть покрыты тестами.
    
    Решенное задание залить на гитхаб, предоставить ссылку.
    Все возникающие вопросы по заданию решать самостоятельно, по своему
усмотрению.
~~~


Команды для запуска:
<br>
* Команда чтобы собрать проект в jar:     ___mvn clean install___
* Команда для запуска контейнеров:        ___docker-compose up___
