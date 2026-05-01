package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import yahoofinance.YahooFinance;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App1 extends Application {

    private final Queue<DataPoint> dataQueue = new LinkedList<>();
    private XYChart.Series<String, Number> series;

    private final ScheduledExecutorService executor =
            Executors.newSingleThreadScheduledExecutor();

    private boolean useFakeData = true; // 👈 переключатель

    @Override
    public void start(Stage stage) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Tracker");

        series = new XYChart.Series<>();
        series.setName("DJI");

        lineChart.getData().add(series);

        stage.setScene(new Scene(lineChart, 900, 600));
        stage.setTitle("Stock Tracker");
        stage.show();

        startFetching();
    }

    private void startFetching() {

        executor.scheduleAtFixedRate(() -> {

            try {
                double price;
                String time = LocalTime.now()
                        .withSecond(0)
                        .withNano(0)
                        .toString();

                // =========================
                // MODE SWITCH
                // =========================
                if (useFakeData) {
                    price = 40000 + (Math.random() * 200 - 100);
                } else {

                    Stock stock = YahooFinance.get("^DJI");

                    if (stock == null ||
                            stock.getQuote() == null ||
                            stock.getQuote().getPrice() == null) {
                        System.out.println("No API data (429 or null)");
                        return;
                    }

                    price = stock.getQuote().getPrice().doubleValue();
                }

                DataPoint point = new DataPoint(time, price);
                dataQueue.add(point);

                if (dataQueue.size() > 20) {
                    dataQueue.poll();
                }

                Platform.runLater(this::updateChart);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    private void updateChart() {
        series.getData().clear();

        for (DataPoint p : dataQueue) {
            series.getData().add(
                    new XYChart.Data<>(p.time, p.value)
            );
        }
    }

    @Override
    public void stop() {
        executor.shutdownNow();
    }

    static class DataPoint {
        String time;
        double value;

        DataPoint(String time, double value) {
            this.time = time;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
