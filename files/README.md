# 🏦 Credit Loan Management System

A practical Java/JavaFX project that implements a **credit loan lifecycle state machine** — including a UML state diagram and two fully functional build configurations (Maven and Gradle).

---

## 📂 Repository Structure

```
/
├── README.md                        ← this file
│
├── credit-uml/                      ← Project 1: UML State Diagram
│   ├── README.md
│   └── diagram.puml
│
├── credit-gradle/                   ← Project 2: JavaFX + Gradle
│   ├── README.md
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   └── src/main/java/org/example/App1.java
│
└── credit-maven/                    ← Project 3: JavaFX + Maven
    ├── README.md
    ├── pom.xml
    └── src/main/java/org/example/App1.java
```

---

## 🎯 Learning Objectives

- Build a **UML State Machine Diagram** using PlantUML
- Configure a **Gradle Kotlin DSL** project with JavaFX
- Configure an **Apache Maven** project with JavaFX
- Implement a **finite state machine** in Java with a live UI

---

## 🔄 Loan Lifecycle

```
  ●  →  [Application Submitted]  →  [Under Review]
                                        │        │
                                  [Verification]  [Rejected] → ◎
                                        │        │
                                    [Approved] ──┘
                                        │
                                   [Disbursed]
                                        │
                                  [Active Loan]
                                   │         │
                                [Closed]  [Overdue]
                                   │         │
                                   └────◎────┘
```

| State                | Color      | Description                          |
|----------------------|------------|--------------------------------------|
| Application Submitted | 🔵 Blue   | Client submitted a loan application  |
| Under Review          | 🟡 Yellow | Initial scoring / data check         |
| Verification          | 🟠 Orange | In-depth verification required       |
| Approved              | 🟢 Green  | Loan approved                        |
| Rejected              | 🔴 Red    | Loan denied                          |
| Disbursed             | 🔵 Blue   | Funds transferred to client          |
| Active Loan           | 🔵 Blue   | Client is making payments            |
| Overdue               | 🔴 Red    | Payment missed                       |
| Closed                | ⚫ Grey   | Loan fully repaid                    |

---

## 🚀 Quick Start

### Project 1 — UML Diagram

```bash
# Online (no install needed)
# → https://www.plantuml.com/plantuml/uml/
# Paste the contents of credit-uml/diagram.puml

# Locally
java -jar plantuml.jar credit-uml/diagram.puml
```

### Project 2 — Gradle

```bash
cd credit-gradle
./gradlew run          # launch the app
./gradlew test         # run tests
./gradlew build        # full build
```

### Project 3 — Maven

```bash
cd credit-maven
mvn javafx:run         # launch the app
mvn test               # run tests
mvn package            # build fat-jar
```

---

## 🛠 Tech Stack

| Technology        | Version | Role                        |
|-------------------|---------|-----------------------------|
| Java              | 17+     | Programming language        |
| JavaFX            | 21      | GUI framework               |
| Gradle Kotlin DSL | 8.x     | Build system (project 2)    |
| Apache Maven      | 3.9+    | Build system (project 3)    |
| JUnit 5           | 5.10.2  | Unit testing                |
| PlantUML          | any     | UML diagram generation      |

---

## ✅ Completed Tasks

- [x] UML state diagram built with PlantUML
- [x] 9 states and all transitions defined
- [x] Viewing instructions for draw.io and PlantUML Online
- [x] Gradle Kotlin DSL project configured with JavaFX 21
- [x] Maven project configured with JavaFX 21
- [x] JavaFX app with live state visualization
- [x] Event log and manual transition controls
