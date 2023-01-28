package com.example.di_3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Colocacion elementos responsive
        Rectangle pala1 = new Rectangle(15, 80);
        pala1.setFill(Color.rgb(255, 255, 255));
        Rectangle pala2 = new Rectangle(15,80);;
        pala2.setFill(Color.rgb(255, 255, 255));
        Circle pelota = new Circle(8,8,8);
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
                        pala2.setLayoutX(t1.intValue()-25);
                        pelota.setLayoutX(t1.floatValue()/2.f-pelota.getRadius()/2);
                    });
                    heightProperty().addListener((observableValue, number, t1) -> {
                        pala1.setLayoutY(t1.floatValue()/2.f-pala1.getHeight()/2);
                        pala2.setLayoutY(t1.floatValue()/2.f-pala2.getHeight()/2);
                        pelota.setLayoutY(t1.floatValue()/2.f-pelota.getRadius()/2);
                    });

                }}, 320, 240);
        stage.setTitle("PongFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}