package com.example.di_3;

import javafx.animation.Animation;
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

public class HelloApplication extends Application {
    Rectangle pala1 = new Rectangle(15, 80);
    Rectangle pala2 = new Rectangle(15, 80);
    Circle pelota = new Circle(10, 10, 10);

    private boolean abajoDer, abajoIzq, arribaDer, arribaIzq;

    private int moviemientoVelocidadY = -5;
    private int moviemientoVelocidadX = -5;


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

                }}, 1000, 700) {{
            setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.W) {
                    arribaIzq = true;
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    abajoIzq = true;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    arribaDer = true;
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    abajoDer = true;
                }
            });
            setOnKeyReleased(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.W) {
                    arribaIzq = false;
                }
                if (keyEvent.getCode() == KeyCode.S ) {
                    abajoIzq = false;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    arribaDer = false;
                }
                if (keyEvent.getCode() == KeyCode.DOWN ) {
                    abajoDer = false;
                }
            });
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                pala1.setY(arribaIzq ? pala1.getY() : pala1.getY() + 10);
                pala1.setY(abajoIzq ? pala1.getY() : pala1.getY() - 10);
                pala2.setY(arribaDer ? pala2.getY() : pala2.getY() + 10);
                pala2.setY(abajoDer ? pala2.getY() : pala2.getY() - 10);
                System.out.println(pelota.getLayoutY());
                if (pelota.getLayoutY() + moviemientoVelocidadY == 0 || pelota.getLayoutY() + moviemientoVelocidadY == getHeight()-10) {
                    moviemientoVelocidadY*=-1;
                }
                //pelota.setLayoutX(pelota.getLayoutX() + moviemientoVelocidadX);*/
                pelota.setLayoutY(pelota.getLayoutY() + moviemientoVelocidadY);
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