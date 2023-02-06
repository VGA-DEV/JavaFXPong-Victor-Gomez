package com.example.di_3;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    Rectangle pala1 = new Rectangle(15, 80);
    Rectangle pala2 = new Rectangle(15, 80);
    Circle pelota = new Circle(10, 10, 10);

    private boolean abajoDer, abajoIzq, arribaDer, arribaIzq;

    private int move = 5;


    @Override
    public void start(Stage stage) throws IOException {
        //Colocacion elementos responsive
        pala1.setFill(Color.rgb(255, 255, 255));
        pala2.setFill(Color.rgb(255, 255, 255));
        pelota.setFill(Color.rgb(255, 255, 255));
        Scene scene = new Scene(
                new Pane(
                        pala1,
                        pala2,
                        pelota
                ) {{
                    requestFocus();
                    setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
                    widthProperty().addListener((observableValue, number, t1) -> {
                        pala1.setLayoutX(10);
                        pala2.setLayoutX(t1.intValue() - 25);
                        pelota.setLayoutX(t1.floatValue() / 2.f - pelota.getRadius() / 2);
                    });
                    heightProperty().addListener((observableValue, number, t1) -> {
                        pala1.setLayoutY(t1.floatValue() / 2.f - pala1.getHeight() / 2);
                        pala2.setLayoutY(t1.floatValue() / 2.f - pala2.getHeight() / 2);
                        pelota.setLayoutY(t1.floatValue() / 2.f - pelota.getRadius() / 2);
                    });

                }}, 320, 240) {{
            setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.W && pala1.getLayoutY() > 10) {
                    arribaIzq = true;
                }
                if (keyEvent.getCode() == KeyCode.S && pala1.getHeight() < getHeight() - 90) {
                    abajoIzq = true;
                }
                if (keyEvent.getCode() == KeyCode.UP && pala2.getLayoutY() > 10) {
                    arribaDer = true;
                }
                if (keyEvent.getCode() == KeyCode.DOWN && pala2.getLayoutY() < getHeight() - 90) {
                    abajoDer = true;
                }
            });
            setOnKeyReleased(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.W && pala1.getLayoutY() > 10) {
                    arribaIzq = false;
                }
                if (keyEvent.getCode() == KeyCode.S && pala1.getHeight() < getHeight() - 90) {
                    abajoIzq = false;
                }
                if (keyEvent.getCode() == KeyCode.UP && pala2.getLayoutY() > 10) {
                    arribaDer = false;
                }
                if (keyEvent.getCode() == KeyCode.DOWN && pala2.getLayoutY() < getHeight() - 90) {
                    abajoDer = false;
                }
            });
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                pala1.setY(arribaIzq ? pala1.getY() : pala1.getY() + 10);
                pala1.setY(abajoIzq ? pala1.getY() : pala1.getY() - 10);
                pala2.setY(arribaDer ? pala2.getY() : pala2.getY() + 10);
                pala2.setY(abajoDer ? pala2.getY() : pala2.getY() - 10);
                if (pelota.getLayoutX() + move > getHeight() || pelota.getCenterX() - move < 0) {
                    move*=-1;
                }
                pelota.setLayoutX(pelota.getLayoutY() + move);
                pelota.setLayoutY(pelota.getLayoutX() + 5);
            }
            ));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();


        }};

        stage.setTitle("PongFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}