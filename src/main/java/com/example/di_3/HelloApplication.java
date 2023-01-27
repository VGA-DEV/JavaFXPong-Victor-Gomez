package com.example.di_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(
                new Pane(
                        new Rectangle(20,100) {{
                            setLayoutX(10);
                            setLayoutY(10);
                            setFill(Color.rgb(0, 0, 0));
                        }},
                        new Rectangle(20,100) {{
                            setLayoutX(10);
                            setLayoutY(10);
                            setFill(Color.rgb(0, 0, 0));
                        }}
                ) {{
                    requestFocus();
                }}

                , 320, 240);
        stage.setTitle("PongFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}