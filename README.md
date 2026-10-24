# Lab 1 — Data-Oriented Programming in Java

## Опис проєкту
Модель системи сповіщень, побудована за підходом Data-Oriented Programming:
- `Notification` — sealed interface, що описує ієрархію даних;
- `TextMsg`, `ImageMsg`, `AudioMsg` — незмінні records з валідацією в compact-конструкторах;
- `NotificationProcessor` — обробник, що через switch expression з pattern matching
  (без `default`) формує текстове представлення за допомогою Text Blocks.

Усі класи знаходяться в пакеті `ua.kpi.comsys.dop`.

## Розрахунок варіанта
N = 17 (номер порядкового номера в групі)
V = 17 mod 3 = **2** — система сповіщень.

## Вимоги
- JDK 21+
- JUnit 5 (`lib/junit-platform-console-standalone-1.11.4.jar`, вже в репозиторії)

## Запуск тестів

### IntelliJ IDEA
ПКМ на папці `test` → **Run 'All Tests'**.

### З командного рядка
Linux / macOS:
```bash
javac -d out src/ua/kpi/comsys/dop/*.java
javac -d out -cp out:lib/junit-platform-console-standalone-1.11.4.jar test/ua/kpi/comsys/dop/*.java
java -jar lib/junit-platform-console-standalone-1.11.4.jar -cp out --scan-classpath
```

Windows (замість `:` у classpath використовується `;`):
```bash
javac -d out src/ua/kpi/comsys/dop/*.java
javac -d out -cp "out;lib/junit-platform-console-standalone-1.11.4.jar" test/ua/kpi/comsys/dop/*.java
java -jar lib/junit-platform-console-standalone-1.11.4.jar -cp out --scan-classpath
```