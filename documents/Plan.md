### План автоматизации тестирования
>*_Валидные данные:_*
>* _номер карты - 16 цифр;_
>* _срок действия карты (месяц/год) - 01.24 (текущая дата), 02.24, 01.25;_
>* _владелец (имя фамилия на латинском);_
>* _cvv код - 3 цифры._
#### Список автоматизируемых сценариев:

**1) Оплата по одобренной карте, обычная покупка, введение валидных данных:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидные данные одобренной карты;
- нажать кнопку "Продолжить".

*Ожидаемый результат:* В БД появляются данные карты (номер карты, владелец, срок действия карты) с указанием статуса, что покупка совершена успешно. Пользователь видит сообщение "Покупка совершена успешно". Данные карты не сохраняются.

**2) Оплата по отклоненной карте, обычная покупка, введение валидных данных:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидные данные отклоненной карты;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: В БД появляются данные карты (номер карты, владелец, срок действия карты) с указанием статуса, что покупка отклонена. Пользователь видит сообщение "Операция отклонена. Проверьте данные карты".

**3) Оплата по неизвестной карте, обычная покупка, введение валидных данных:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидные данные неизвестной карты;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Операция отклонена. Проверьте данные карты".

**4) Оплата по карте c невалидным номером карты (ввод 15 цифр), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести невалидный номер карты (ввести 15 цифр);
- ввести валидные данные в поле "Владелец";
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**5) Оплата по карте c невалидным номером карты (ввод символов на кириллице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести невалидный номер карты (ввести буквы на кириллице);
- ввести валидные данные в поле "Владелец";
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**6) Оплата по карте c невалидным номером карты (ввод символов на латинице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести невалидный номер карты (ввести буквы на латинице);
- ввести валидные данные в поле "Владелец";
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**7) Оплата по карте c невалидным месяцем (ввод 13-ого месяца), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести невалидный месяц (ввести 13 месяц - 13);
- ввести валидный год (24);
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**8) Оплата по карте c невалидным годом (меньше текущего), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести валидный месяц (12);
- ввести невалидный год (23);
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**9) Оплата по карте c невалидным месяцем (ввод символов на кириллице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести невалидный месяц (ввести символы на кириллице);
- ввести валидный год;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**10) Оплата по карте c невалидным годом (ввод символов на кириллице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести валидный месяц;
- ввести невалидный год (ввести символы на кириллице);
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**11) Оплата по карте c невалидным месяцем (ввод символов на латинице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести невалидный месяц (ввести символы на латинице);
- ввести валидный год;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**12) Оплата по карте c невалидным годом (ввод символов на латинице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- ввести валидные данные в поле "Владелец";
- ввести валидный месяц;
- ввести невалидный год (ввести символы на латинице);
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**13) Оплата по карте c невалидным полем владелец (ввод цифр), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- в поле "Владелец" ввести невалидный данные (ввести цифры);
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**14) Оплата по карте c невалидным полем владелец (ввод спецсимволов), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- в поле "Владелец" ввести невалидный данные (ввести спецсимволы);
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**15) Оплата по карте c невалидным полем владелец (ввод данных на кириллице), обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- в поле "Владелец" ввести невалидный данные (ввести данные на кириллице);
- ввести валидный срок действия карты;
- ввести валидный номер CVV;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**16) Оплата по карте c невалидным полем CVV (ввод 2-ух цифр), обычная покупка**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- в поле "Владелец" ввести валидные данные;
- ввести валидный срок действия карты;
- в поле "CVV" ввести невалидный данные (ввести 2 цифры);
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**17) Оплата по карте c невалидным полем CVV (ввод букв), обычная покупка**
- на странице сервиса нажать кнопку "Купить";
- ввести валидный номер карты;
- в поле "Владелец" ввести валидные данные;
- ввести валидный срок действия карты;
- в поле "CVV" ввести невалидный данные (ввести буквы);
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверный формат". Запрос в БД не уходит.

**18) Оплата по карте c пустым номером карты, обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- оставить пустым поле "Номер карты", остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Поле обязательно для заполнения". Запрос в БД не уходит.

**19) Оплата по карте c пустым полем "Месяц", обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- оставить пустым поле "Месяц", остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Поле обязательно для заполнения". Запрос в БД не уходит.

**20) Оплата по карте c пустым полем "Год", обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- оставить пустым поле "Год", остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Поле обязательно для заполнения". Запрос в БД не уходит.

**21) Оплата по карте c пустым полем "Владелец", обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- оставить пустым поле "Владелец", остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Поле обязательно для заполнения". Запрос в БД не уходит.

**22) Оплата по карте c пустым полем CVV, обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- оставить пустым поле "CVV", остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Поле обязательно для заполнения". Запрос в БД не уходит.

**23) Оплата по карте c нулевым номером карты, обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести в поле "Номер карты" нули, остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**24) Оплата по карте c нулевым полем "Срок действия карты", обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести в поле "Срок действия карты" нули, остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**25) Оплата по карте c нулевым полем "Владелец", обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести в поле "Владелец" нули, остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**26) Оплата по карте c нулевым полем CVV, обычная покупка:**
- на странице сервиса нажать кнопку "Купить";
- ввести в поле "CVV" нули, остальные поля заполнить валидными данными;
- нажать кнопку "Продолжить".

*Ожидаемый результат*: Покупка не совершается. Пользователь видит сообщение "Неверные данные". Запрос в БД не уходит.

**При оплате тура в кредит провести тесты, представленные выше.**

### Перечень используемых инструментов:

* IntelliJ IDEA - интегрированная среда разработки (IDE) для создания, тестирования и анализа ПО.
* JUnit5 - инфраструктура модульного тестирования для Java, оснащенная множеством функций, включая вложенные тесты, параметризованные тесты, новые API расширения или поддержку Java.
* Gradle - система автоматический сборки, которую используют для упрощения работы с Java.
* Selenide - для автоматизированного тестирования веб-приложений на основе Selenium WebDriver, дающий такие преимущества, как изящный API, поддержка Ajax для стабильных тестов, мощные селекторы, простая конфигурация.
* Allure - для генерации отчетов.
* Github - это онлайн-платформа для хранения, управления и совместной работы над проектами с открытым исходным кодом.

### Перечень и описание возможных рисков при автоматизаци:

* Риск появления проблем с настройкой приложения.
* Обновление функций приложения, что приведет к новому поиску селекторов.
* Отсутствие в коде разработчика приложения тестовых меток. Вследствие чего уменьшается скорость тестирования и увеличивается стоимость проекта.
* Риск появления проблем с правильной идентификацией полей ввода.
* Сбои в инфраструктуре сайта. Не рабочий сайт нельзя протестировать.

### Интервальная оценка с учетом риска в часах:

На дипломный проект выделено 72 часа.

### План сдачи работ:

* ожидается, что автотесты буду готовы через 2-3 недели;
* ожидается, что результатом их прогона будет успешное прохождение тестов.


