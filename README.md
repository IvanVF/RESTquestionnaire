# RESTquestionnaire
Перед первым запуском необходимо настроить БД: в файле application.properies поменять username, password для аккаунта PostgreSQL. Так же необходимо создать БД Questionnaire.

Старт приложения настроен на порт 5000, при необходиости смена осуществляется в строке server.port (файл application.properies)
При первом запуске liquibase настроит таблицы и заполнит пробными значениями.

Посмотреть все опросы можно отправив Get запрос на адрес http://localhost:5000/interview . По умолчанию ответ будет отсортирован сначала по  алфавиту, после чего дополнительно по убыванию даты старта опроса. Сортировку можно поменять на преимущественную по дате добавив параметр sortType=Date (полный запрос: http://localhost:5000/interview?sortType=Date )
Есть следующие фильтры: название опроса (interviewName), дата начала опроса (startDate, формат дд-мм-гггг), дата окончания опроса (endDate, формат дд-мм-гггг), активность (true, false). Полный запрос со всеми фильтрами может выглядить так: http://localhost:5000/interview?startDate=15-05-2021&active=true&interviewName=C Interview 3&sortType=Date .

Для получение одного опроса по id, необходимо отправить Get запрос на адрес http://localhost:5000/interview/idОпроса (например http://localhost:5000/interview/859259b2-34d1-4bbe-850b-93d6bad87731 для опросов по-умолчанию)

Для создания нового опроса необходимо отправить Post запрос на адрес http://localhost:5000/interview с описанием тела опроса в формате JSON: 
{
    "interviewName" : "Новый опрос",
    "startDate" : "19-05-2021",
    "endDate": "19-05-2022",
    "active" : true
}
Все поля являются обязательными. Если не указать "active", будет установлен false. В случае ошибки ErrorHandler выведет удобочитаемое описание.

Для изменения опроса необходимо отправить Put запрос на адрес http://localhost:5000/interview/idОпроса (например http://localhost:5000/interview/859259b2-34d1-4bbe-850b-93d6bad87731 для опросов по-умолчанию)
В теле запроса необходимо указать поля, которые необходимо изменить. Будут обработаны только указанные поля, например:
{
    "interviewName" : "Смена названия опроса"
}

Для удаления опроса по id необходимо отправить Delete запрос на адрес http://localhost:5000/interview/idОпроса (например http://localhost:5000/interview/22dbad43-308d-4322-ab5b-b31d9b073b54 для опросов по-умолчанию)

Для добавления вопросов в опросы необходимо отправить Post запрос на адрес http://localhost:5000/question/idОпроса (например http://localhost:5000/question/6ed19dcf-a0e2-4980-9102-9d1abfdd6840 для опросов по-умолчанию). В теле запроса необходимо указать список questionRequestList, в который входит текст вопроса (question) и номер вопроса (qorder), например:
{ "questionRequestList" : 
    [
        {"question" : "question 1", "qorder" : 1},
        {"question" : "question 2", "qorder" : 2}
    ]
}

Изменить вопрос по id вопроса: Put запрос на адрес http://localhost:5000/question/idВопроса . Пример:
http://localhost:5000/question/4dc77fb3-44e2-47fa-948f-9310d9d8f6bd
{"question" : "Смена вопроса", "qorder" : 15}

Удалить вопрос по id вопроса: Delete запрос на адрес http://localhost:5000/question/idВопроса .

Показать все вопросы: Get запрос на адрес http://localhost:5000/question .

Дополнительно: в приложении используются аннотации валидации и DTO-подход. Обработана часть ошибок через ErrorHandler. Вывод ошибки приведён к виду: httpCode, сообщение, описание.





