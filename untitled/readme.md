## Задача

**Условия:**

Разработайте счетчик обращений, который подсчитывает количество обращений, полученных за последние 5 минуты.

Ваша система должна принимать timestamp параметр ( с точностью до секунд ), и вы можете предположить, что вызовы выполняются в системе в хронологическом порядке (т.е. временная метка монотонно увеличивается). Несколько обращений могут поступить примерно одновременно.

> **Реализуйте класс ActionCounter:**

`ActionCounter()` - Инициализирует объект системы счетчика обращений.
`void call(int timestamp)` - Записывает обращение, произошедшее в timestamp ( в секундах ). Одновременно может произойти несколько попаданий timestamp.
`int getActions(int timestamp)` - Возвращает количество обращений за последние 5 минут timestamp (т. е. за последние 300 секунд).
> 

```java
class ActionCounter {

    public ActionCounter() {
    }

    public void call(int timestamp) {
    }

    public int getActions(int timestamp) {
    }
}
```

### Ограничения:

- 1 <= timestamp <= $2 * 10^9$
- Все вызовы в систему делаются в хронологическом порядке (т.е. временная метка монотонно увеличивается).

### Пример:

**Ввод/Выход:**

```java
call(1) -> void
call(2) -> void
call(2) -> void
getActions(4) -> 3
call(300) -> void
getActions(300) -> 4
getActions(301) -> 3
```

**Объяснение:**

`call(1);`       // фиксируем обращение в момент времени = 1.
`call(2);`       // фиксируем обращение в момент времени = 2.
`call(2);`       // фиксируем обращение в момент времени = 2.
`getActions(4);`   // запрашиваем количество обращений за последние 5 мин в момент времени = 4, возвращаем 3.
`call(300);`     // фиксируем обращение в момент времени = 300.
`getActions(300);` // запрашиваем количество обращений за последние 5 мин в момент времени = 300, возвращаем 4.
`getActions(301);` // запрашиваем количество обращений за последние 5 мин в момент времени = 301, возвращаем 3.
