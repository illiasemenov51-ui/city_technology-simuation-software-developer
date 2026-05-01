# ⚙️ Credit Loan System — Maven + JavaFX

A JavaFX application that visualises the credit loan lifecycle state machine, built with **Apache Maven**.

---

## 📁 Project Structure

```
credit-maven/
├── pom.xml                                ← Maven configuration
└── src/
    ├── main/
    │   └── java/org/example/
    │       └── App1.java                  ← main JavaFX class
    └── test/
        └── java/org/example/             ← JUnit 5 tests
```

---

## 🔧 Requirements

| Tool        | Version       |
|-------------|---------------|
| Java (JDK)  | 17 or higher  |
| Maven       | 3.9+          |
| JavaFX      | 21            |

---

## 🚀 Running the App

### Via Maven

```bash
# Compile
mvn compile

# Run JavaFX application
mvn javafx:run

# Run tests
mvn test

# Build fat-jar (includes all dependencies)
mvn package
java -jar target/credit-loan-maven-1.0-SNAPSHOT.jar
```

### In IntelliJ IDEA

1. `File → Open` → select the `credit-maven` folder
2. IDEA will automatically pick up `pom.xml`
3. Click **Run** on the `App1` class

---

## 📦 Dependencies

| Artifact                  | Version  | Purpose              |
|---------------------------|----------|----------------------|
| `javafx-controls`         | 21       | JavaFX UI components |
| `javafx-fxml`             | 21       | FXML loader          |
| `YahooFinanceAPI`         | 3.17.0   | Financial data       |
| `junit-jupiter`           | 5.10.2   | JUnit 5 testing      |

### Maven Plugins

| Plugin                   | Version  | Purpose                             |
|--------------------------|----------|-------------------------------------|
| `maven-compiler-plugin`  | 3.13.0   | Compile Java 17                     |
| `javafx-maven-plugin`    | 0.0.8    | Run JavaFX (`mvn javafx:run`)       |
| `maven-surefire-plugin`  | 3.2.5    | Run JUnit 5 tests                   |
| `maven-shade-plugin`     | 3.5.3    | Build fat-jar                       |

---

## 🖥 App Features

- Displays the **current loan state** with colour-coded indicator
- Buttons for **manually triggering** state transitions
- **Event log** — full history of all transitions
- **Reset** button to restart the lifecycle

---

## 🔁 State Diagram

```
[Application] → [Under Review] → [Approved]  → [Disbursed] → [Active] → [Closed]
                               ↘ [Verification] ↗             ↘ [Overdue] ↗
                               ↘ [Rejected]
```

Full UML diagram: see `credit-uml/`.

---

## ⚖️ Maven vs Gradle

| Criteria          | Maven (`pom.xml`)        | Gradle (`build.gradle.kts`)    |
|-------------------|--------------------------|--------------------------------|
| Format            | XML                      | Kotlin DSL                     |
| Build speed       | Standard                 | Faster (incremental builds)    |
| Readability       | Verbose XML              | Concise Kotlin                 |
| Ecosystem         | Mature, very large       | Growing                        |
| IDE support       | Excellent                | Excellent                      |
| Flexibility       | Limited                  | High                           |
