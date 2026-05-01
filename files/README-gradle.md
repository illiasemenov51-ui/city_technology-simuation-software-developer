# ⚙️ Credit Loan System — Gradle + JavaFX

A JavaFX application that visualises the credit loan lifecycle state machine, built with **Gradle Kotlin DSL**.

---

## 📁 Project Structure

```
credit-gradle/
├── build.gradle.kts                       ← build config (Gradle Kotlin DSL)
├── settings.gradle.kts                    ← project name
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
| Gradle      | 8.x (Wrapper) |
| JavaFX      | 21            |

---

## 🚀 Running the App

### Via Gradle

```bash
# Build
./gradlew build

# Run JavaFX application
./gradlew run

# Run tests
./gradlew test
```

### In IntelliJ IDEA

1. `File → Open` → select the `credit-gradle` folder
2. IDEA will automatically pick up `build.gradle.kts`
3. Click **Run** on the `App1` class

---

## 📦 Dependencies

| Dependency                 | Version  | Purpose              |
|----------------------------|----------|----------------------|
| `org.openjfx.javafxplugin` | 0.0.13   | JavaFX Gradle plugin |
| `javafx.controls`          | 21       | JavaFX UI components |
| `javafx.fxml`              | 21       | FXML loader          |
| `YahooFinanceAPI`          | 3.17.0   | Financial data       |
| `junit-jupiter`            | 5.10.2   | JUnit 5 testing      |

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
