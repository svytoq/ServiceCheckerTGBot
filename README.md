# ServiceCheckerTGBot
Проект в рамках обучения в школе бекенда Java от Тинькофф

В ходе заданий вы напишете 2 web-сервиса для отслеживания обновлений контента по ссылкам. В сервисе поддерживаются:

Вопросы со StackOverflow
Репозитории GitHub
Управление подписками (ссылками) происходит через чат с ботом в Telegram. При новых изменениях в чат отправляется уведомление.

Сервисы будут общаться между собой как напрямую (по HTTP), так и асинхронно (очередь сообщений). Для хранения данных будет использоваться СУБД PostgreSQL.

Примитивная схема выглядит следующим образом:


![image](https://user-images.githubusercontent.com/55537912/227169941-314807b5-274a-468b-8b9d-26f44f55c75f.png)
