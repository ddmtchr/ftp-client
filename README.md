### Инструкция по сборке и запуску тестов:
`git clone https://github.com/ddmtchr/ftp-client.git`

`cd ftp-client/ftp-client`

`mvn clean install`

`cd ../ftp-client-tests`

`mvn clean test`

### Запуск клиента
`java -jar ./ftp-client/target/ftp-client-1.0-SNAPSHOT.jar <login> <host>`

Далее ввести пароль и следовать инструкции по командам. Файл сохраняется на сервер при выходе через exit.

### Комментарии по тестам

- Тесты на подключение к серверу (некорректный адрес, данные пользователя)
- Тесты основной бизнес-логики
- Тесты парсинга JSON'a