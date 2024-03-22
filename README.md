# README

## Inhaltsverzeichnis
* [Spring Framework](#spring-framework)
* [Directory / File Struktur](#directory--file-struktur)
* [ServerApplication.java](#serverapplicationjava)
* [Handlers in Spring Boot](#handler)

## Spring Framework

* [Spring.io](https://spring.io/projects/spring-framework)
* [Spring Boot](https://spring.io/projects/spring-boot)
* Spring Boot wird verwendet, um Standalone WebApps und Microservices zu erstellen
  * Lightweight Spring Ableger
* Im Kontext des Projekts wird damit der Server aufgebaut und das Request Handling übernommen

## Directory / File Struktur

*`src/main`
  * `java/at/aau/se2`
    * enthält die ServerApplication Klasse
    * `/handler`
      * enthält die Handler Implementierungen & Konfigurationen
      * Pro Handler ein Directory
        * kann auch eine eigene Logik-Klasse enthalten, die aufwendigere logische Funktionen zusätzlich modularisiert
  * `resources/`
    * enthält die `application.properties` Datei
      * Hier können Port und ähnliche Server Properties definiert werden

## `ServerApplication.java`
* Sorgt dafür, dass die implementierte Logik am Server läuft
* enthält folgenden Code:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public Class ServerApplication{

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
```

## Handler

Handler in Spring Boot setzen sich aus der `HandlerConfig` und der `HandlerImpl` zusammen

### `HandlerConfig`
* Die angesprochene URL des Servers muss auf einen Handler und dessen Implementierung registriert werden
* implementiert `WebSocketConfigurer`
* besteht aus:
  * `void registerWebSocketHandlers()`

### `HandlerImpl`
* stellt die tatsächliche Implementierung des Handlers da
* beinhaltet die Logik, die bei einer an die URL gesendeten Nachricht, ausgeführt werden soll
* implementiert `WebSocketHandler`
* besteht aus:
  * `void afterConnectionEstablished()`
  * `void handleMessage()`
  * `void handleTransportError()`
  * `void afterConnectionClosed()`
  * `boolean supportsPartialMessages()`

## Mapping Konventionen (projektintern)
* `server-url/type/action`
  * `type`
    * User Zug oder Computer Zug
  * `action`
    * Welche Aktion wird vollzogen
    * Später noch Unterteilung in Kartentyp