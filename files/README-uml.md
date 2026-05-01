# 📊 UML — Credit Loan State Machine Diagram

## Overview

This project contains a **UML State Machine Diagram** for a bank credit loan system, built with PlantUML. It describes the complete lifecycle of a loan application — from submission through to closure.

---

## 📁 Project Structure

```
credit-uml/
└── diagram.puml    ← PlantUML source file
```

---

## 🔄 States

| State                 | Description                              | Color      |
|-----------------------|------------------------------------------|------------|
| Application Submitted | Client submitted the application         | 🔵 Blue    |
| Under Review          | Initial data check / scoring             | 🟡 Yellow  |
| Verification          | Additional / in-depth verification       | 🟡 Yellow  |
| Approved              | Application approved                     | 🟢 Green   |
| Rejected              | Application denied                       | 🔴 Red     |
| Disbursed             | Funds issued to the client               | 🔵 Blue    |
| Active Loan           | Loan is active; client making payments   | 🔵 Blue    |
| Overdue               | Payment overdue                          | 🔴 Red     |
| Closed                | Loan fully repaid and closed             | ⚫ Grey    |

---

## ➡️ Transitions

```
[*] ─────────────────────► ApplicationSubmitted
ApplicationSubmitted ─────► UnderReview
UnderReview ──────────────► Approved
UnderReview ──────────────► Rejected
UnderReview ──────────────► Verification
Verification ─────────────► Approved
Verification ─────────────► Rejected
Approved ─────────────────► Disbursed
Disbursed ────────────────► ActiveLoan
ActiveLoan ───────────────► Closed
ActiveLoan ───────────────► Overdue
Overdue ──────────────────► Closed
Closed / Rejected ────────► [*]
```

---

## 🛠 How to View the Diagram

### Option 1 — PlantUML Online (recommended)

1. Go to [https://www.plantuml.com/plantuml/uml/](https://www.plantuml.com/plantuml/uml/)
2. Paste the contents of `diagram.puml`
3. Click **Submit**

### Option 2 — VS Code

Install the **PlantUML** extension by jebbs:
```
ext install jebbs.plantuml
```
Open `diagram.puml` and press `Alt + D` to preview.

### Option 3 — Locally via Java

```bash
# Download PlantUML jar
wget https://github.com/plantuml/plantuml/releases/latest/download/plantuml.jar

# Generate PNG
java -jar plantuml.jar diagram.puml
```

---

## 🎨 Colour Scheme

| Element               | Hex       |
|-----------------------|-----------|
| Core process states   | `#DDEEFF` |
| Approved              | `#DDFFDD` |
| Rejected / Overdue    | `#FFDDDD` |
| Verification checks   | `#FFFFCC` |
| Closed / Final        | `#EEEEEE` |

---

## 📌 UML Notation

| Symbol              | Meaning              |
|---------------------|----------------------|
| `●` filled circle   | Initial state        |
| `◎` double circle   | Final state          |
| Rectangle           | State                |
| Arrow `→`           | Transition           |
| Arrow label         | Event / condition    |
