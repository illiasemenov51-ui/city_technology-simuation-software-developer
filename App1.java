package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Система управления кредитами — JavaFX UI
 * Визуализация жизненного цикла кредитной заявки.
 */
public class App1 extends Application {

    // ── Состояния кредита ──────────────────────────────────────────────────
    private enum LoanState {
        APPLICATION_SUBMITTED("Подача заявки",    "#5B9BD5"),
        UNDER_REVIEW         ("Проверка данных",  "#F4C542"),
        VERIFICATION         ("Доп. проверка",    "#F4A742"),
        APPROVED             ("Одобрено",         "#70AD47"),
        REJECTED             ("Отклонено",        "#FF5252"),
        DISBURSED            ("Выдача кредита",   "#5B9BD5"),
        ACTIVE_LOAN          ("Активный кредит",  "#5B9BD5"),
        OVERDUE              ("Просрочка",        "#FF5252"),
        CLOSED               ("Закрыт",           "#9E9E9E");

        final String label;
        final String color;

        LoanState(String label, String color) {
            this.label = label;
            this.color = color;
        }
    }

    private LoanState currentState = LoanState.APPLICATION_SUBMITTED;
    private Label  stateLabel;
    private Label  statusLabel;
    private VBox   stateBox;
    private TextArea logArea;

    // ── Запуск ────────────────────────────────────────────────────────────
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Система управления кредитами");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F5F5;");

        root.setTop(buildHeader());
        root.setCenter(buildCenter());
        root.setBottom(buildControls());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        log("Система запущена. Текущее состояние: " + currentState.label);
    }

    // ── Шапка ─────────────────────────────────────────────────────────────
    private HBox buildHeader() {
        Text title = new Text("💳 Кредитная система — Управление состоянием");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setFill(Color.WHITE);

        HBox header = new HBox(title);
        header.setPadding(new Insets(14, 20, 14, 20));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1565C0;");
        return header;
    }

    // ── Центральная панель ─────────────────────────────────────────────────
    private HBox buildCenter() {
        // -- Панель текущего состояния --
        stateBox = new VBox(12);
        stateBox.setAlignment(Pos.CENTER);
        stateBox.setPadding(new Insets(30));
        stateBox.setMinWidth(320);
        stateBox.setStyle("-fx-background-color: white; -fx-border-color: #DDDDDD; "
                        + "-fx-border-radius: 8; -fx-background-radius: 8;");

        Label lbl = new Label("Текущее состояние");
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        lbl.setTextFill(Color.GRAY);

        stateLabel = new Label(currentState.label);
        stateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        stateLabel.setTextFill(Color.web(currentState.color));

        statusLabel = new Label("Ожидание действия");
        statusLabel.setFont(Font.font("Arial", 12));
        statusLabel.setTextFill(Color.GRAY);

        stateBox.getChildren().addAll(buildStateIndicator(), lbl, stateLabel, statusLabel);

        // -- Журнал событий --
        VBox logBox = new VBox(8);
        logBox.setPadding(new Insets(16));
        logBox.setStyle("-fx-background-color: white; -fx-border-color: #DDDDDD; "
                      + "-fx-border-radius: 8; -fx-background-radius: 8;");

        Label logLbl = new Label("📋 Журнал событий");
        logLbl.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setFont(Font.font("Monospaced", 11));
        logArea.setPrefRowCount(14);
        logArea.setWrapText(true);
        logArea.setStyle("-fx-control-inner-background: #FAFAFA;");

        logBox.getChildren().addAll(logLbl, logArea);
        VBox.setVgrow(logArea, Priority.ALWAYS);

        HBox center = new HBox(16, stateBox, logBox);
        center.setPadding(new Insets(16));
        HBox.setHgrow(logBox, Priority.ALWAYS);
        return center;
    }

    // ── Кружок-индикатор ──────────────────────────────────────────────────
    private StackPane buildStateIndicator() {
        Circle circle = new Circle(30);
        circle.setFill(Color.web(currentState.color));
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(3);

        Text icon = new Text("●");
        icon.setFill(Color.WHITE);
        icon.setFont(Font.font(22));

        StackPane pane = new StackPane(circle, icon);
        pane.setId("stateCircle");
        return pane;
    }

    // ── Кнопки управления ─────────────────────────────────────────────────
    private VBox buildControls() {
        Label hint = new Label("Управление переходами:");
        hint.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        hint.setPadding(new Insets(0, 0, 6, 0));

        FlowPane buttons = new FlowPane(8, 8);
        buttons.setPadding(new Insets(0, 0, 8, 0));

        buttons.getChildren().addAll(
            makeBtn("▶ Начать проверку",   "#5B9BD5", () -> transition(LoanState.UNDER_REVIEW,   "Заявка передана на проверку")),
            makeBtn("🔍 Доп. проверка",    "#F4A742", () -> transition(LoanState.VERIFICATION,   "Требуется дополнительная верификация")),
            makeBtn("✅ Одобрить",          "#70AD47", () -> transition(LoanState.APPROVED,       "Кредит одобрен скорингом")),
            makeBtn("❌ Отклонить",         "#FF5252", () -> transition(LoanState.REJECTED,       "В кредите отказано")),
            makeBtn("💰 Выдать",            "#5B9BD5", () -> transition(LoanState.DISBURSED,      "Средства перечислены клиенту")),
            makeBtn("📅 Активировать",      "#1565C0", () -> transition(LoanState.ACTIVE_LOAN,   "Кредит активен")),
            makeBtn("⚠ Просрочка",         "#FF5252", () -> transition(LoanState.OVERDUE,        "Зафиксирована просрочка платежа")),
            makeBtn("🔒 Закрыть",           "#9E9E9E", () -> transition(LoanState.CLOSED,         "Кредит закрыт")),
            makeBtn("🔄 Сбросить",          "#607D8B", this::reset)
        );

        VBox controls = new VBox(hint, buttons);
        controls.setPadding(new Insets(12, 16, 16, 16));
        controls.setStyle("-fx-background-color: #EEEEEE; -fx-border-color: #CCCCCC; "
                        + "-fx-border-width: 1 0 0 0;");
        return controls;
    }

    private Button makeBtn(String text, String color, Runnable action) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; "
                   + "-fx-font-size: 12; -fx-padding: 6 14; -fx-background-radius: 4; "
                   + "-fx-cursor: hand;");
        btn.setOnAction(e -> action.run());
        return btn;
    }

    // ── Логика переходов ──────────────────────────────────────────────────
    private void transition(LoanState next, String event) {
        String prev = currentState.label;
        currentState = next;

        stateLabel.setText(next.label);
        stateLabel.setTextFill(Color.web(next.color));
        statusLabel.setText("Последнее событие: " + event);

        // обновить индикатор
        StackPane circle = (StackPane) stateBox.lookup("#stateCircle");
        if (circle != null) {
            ((Circle) circle.getChildren().get(0)).setFill(Color.web(next.color));
        }

        log("[" + prev + "] ──► [" + next.label + "]  ·  " + event);
    }

    private void reset() {
        currentState = LoanState.APPLICATION_SUBMITTED;
        stateLabel.setText(currentState.label);
        stateLabel.setTextFill(Color.web(currentState.color));
        statusLabel.setText("Состояние сброшено");
        logArea.clear();
        log("--- Сброс системы ---");
        log("Текущее состояние: " + currentState.label);
    }

    private void log(String msg) {
        logArea.appendText(msg + "\n");
    }

    // ── Точка входа ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        launch(args);
    }
}
