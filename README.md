# Kalkulator BMI z funkcją logowania i zapisem rekordów do bazy danych

To jest aplikacja kalkulatora BMI (Body Mass Index) napisana w oparciu o Spring Boot i bazę danych PostgreSQL. Aplikacja umożliwia użytkownikom obliczanie BMI, logowanie do konta oraz zapisywanie rekordów BMI w bazie danych.

## Wymagania wstępne
- Java JDK (wersja 17 lub nowsza)
- IntelliJ IDEA (lub dowolne preferowane środowisko programistyczne Java)
- Serwer bazy danych PostgreSQL

## Uruchamianie aplikacji
1. Uruchom aplikację Spring Boot z IntelliJ IDEA lub użyj następującej komendy w terminalu:
   ```
   ./mvnw spring-boot:run
   ```
2. Otwórz przeglądarkę internetową i przejdź do aplikacji pod adresem `http://localhost:8080`.

## Użycie
1. Zaloguj się na istniejące konto użytkownika, używając loginu "user" i hasła "password".
2. Po zalogowaniu zostaniesz przekierowany na stronę kalkulatora BMI.
3. Wprowadź swój wzrost i wagę w odpowiednich polach tekstowych, a następnie kliknij przycisk "Oblicz BMI".
4. Obliczona wartość BMI zostanie wyświetlona, a rekord BMI zostanie zapisany w bazie danych PostgreSQL.
5. Możesz zobaczyć swoje rekordy BMI, klikając na link "Pokaż rekordy" w pasku nawigacyjnym.

## Schemat bazy danych
Aplikacja wykorzystuje bazę danych PostgreSQL do przechowywania informacji o użytkownikach oraz rekordów BMI. Schemat bazy danych składa się z dwóch tabel:

1. Tabela `users`: Przechowuje informacje o kontach użytkowników.
   - Kolumny:
     - `user_id`: Klucz główny, automatycznie generowany unikalny identyfikator dla każdego użytkownika.
     - `username`: Nazwa użytkownika.
     - `password`: Hasło użytkownika.

2. Tabela `bmi_records`: Przechowuje rekordy BMI dla poszczególnych użytkowników.
   - Kolumny:
     - `record_id`: Klucz główny, automatycznie generowany unikalny identyfikator dla każdego rekordu BMI.
     - `user_id`: Klucz obcy odnoszący się do kolumny `user_id` w tabeli `users`.
     - `height`: Wzrost użytkownika.
     - `weight`: Waga użytkownika.
     - `bmi`: Obliczona wartość BMI.
     - `bmi_category`: Kategoria BMI.
     - `record_date`: Data i czas utworzenia rekordu BMI.

## Wykorzystane technologie
- Spring Boot - framework Java do budowania aplikacji webowych
- Thymeleaf - oparty na Javie silnik szablonów do renderowania stron po stronie serwera
- PostgreSQL - otwartoźródłowy system zarządzania bazą danych relacyjnych
- Hibernate - framework mapowania obiektowo-relacyjnego do dostępu do bazy danych
- Spring Security - framework do uwierzytelniania i autoryzacji w celu zabezpieczenia aplikacji
- HTML/CSS - znaczniki i stylowanie front-endu
- Maven - narzędzie do zarządzania budową i zależnościami
