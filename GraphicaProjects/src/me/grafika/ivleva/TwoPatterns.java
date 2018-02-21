package me.grafika.ivleva;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Math.*;

public class TwoPatterns extends Application {
    private static final double RADIUS = 30;
    private static final double PAUSE_MS = 1000;
    private static final double ROTATION_MS = 400;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group(); // корневой контейнер
        primaryStage.setTitle("Two Patterns"); // задаем заголовок окна
        primaryStage.setScene(new Scene(root, 500, 500, Color.BLACK));
        primaryStage.setResizable(false); // запрещаем изменять размер окна

        Group hexagons = new Group(); // сюда сохраним шестиугольники
        ArrayList<SequentialTransition> transitions = new ArrayList<>(); // а сюда - анимацию

        // считаем, сколько шестиугольников по вертикали и горизонтали нам хватит
        int hexagons_in_half_raw = (int) ceil(500 / RADIUS / 2) + 2;
        int hexagon_in_half_column = (int) ceil(500 / sqrt(3) / RADIUS / 2) + 2;

        // строим их
        for (int x = -hexagons_in_half_raw; x <= hexagons_in_half_raw; x++) {
            for (int y = -hexagon_in_half_column; y <= hexagon_in_half_column; y++) {
                Polygon hexagon = new Polygon(); // создаем полигон
                hexagon.setFill(Color.valueOf("#f4fbf7")); // задаем заливку
                for (int i = 0; i < 6; i++) {
                    // задаем 6 равно удаленных точек на окружности
                    hexagon.getPoints().add(RADIUS * sin(i * PI / 3));
                    hexagon.getPoints().add(RADIUS * cos(i * PI / 3));
                }
                hexagon.setTranslateX(getX(x, y)); // перемещаем центр в нужную точку
                hexagon.setTranslateY(getY(x, y));
                hexagons.getChildren().add(hexagon); // добавляем в группу

                // создаем анимацию "поворот 0-30 градусов"
                RotateTransition first = new RotateTransition();
                first.setNode(hexagon);
                first.setDuration(Duration.millis(ROTATION_MS));
                first.setFromAngle(0);
                first.setToAngle(30);

                // создаем анимацию "поворот 30-60 градусов"
                RotateTransition second = new RotateTransition();
                second.setNode(hexagon);
                second.setDuration(Duration.millis(ROTATION_MS));
                second.setFromAngle(30);
                second.setToAngle(60);

                // создаем последовательную анимацию, добавляем в него повороты и паузы между ними
                SequentialTransition st = new SequentialTransition();
                st.getChildren().addAll(
                        new PauseTransition(Duration.millis(PAUSE_MS)),
                        first,
                        new PauseTransition(Duration.millis(PAUSE_MS)),
                        second
                );
                st.setCycleCount(Animation.INDEFINITE); // зацикливаем анимацию

                transitions.add(st); // сохраняем анимацию для одного шестиугольника
            }
        }

        // максимальное расстояние от центра видимого шестиугольника до центра сцены
        // (по отношению к нему перематываем анимацию)
        double max_distance = sqrt(pow(getX(0, hexagon_in_half_column) - 250, 2)
                + pow(getY(0, hexagon_in_half_column) - 250, 2));

        for (SequentialTransition st : transitions) {
            RotateTransition rt = (RotateTransition) st.getChildren().get(1); // получаем анимацию-поворот
            Polygon hexagon = (Polygon) rt.getNode(); // получаем шестиугольник

            double cur_distance = sqrt(pow(hexagon.getTranslateX() - 250, 2) + pow(hexagon.getTranslateY() - 250, 2));
            double ratio = cur_distance / max_distance; // вычисляем % выполнения анимации

            // перематываем анимацию так, чтобы шестиугольники начали вращаться в порядке убывания расстояния до центра сцены
            st.jumpTo(Duration.millis(PAUSE_MS - PAUSE_MS * ratio));
        }

        root.getChildren().add(hexagons);
        primaryStage.show();
        transitions.forEach(Transition::play); // запускаем
    }

    // Заметим, что можно ввести аффинное преобразование, переводящее декартовую систему координат в точки центров
    // окружностей, так, чтобы (0,0) переходил в (250,250), (1,1) в (250+sqrt(3)*R,250+3R) и т.д.

    // Соответсвующее преобразование:
    // x_center = 250 + x*R + y*2R
    // y_center = 250 + x*sqrt(3) * R

    private double getX(double x, double y) {
        return 250 + x * RADIUS + y * 2 * RADIUS;
    }

    private double getY(double x, double y) {
        return 250 + x * sqrt(3) * RADIUS;
    }
}
