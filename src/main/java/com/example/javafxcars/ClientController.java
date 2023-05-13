package com.example.javafxcars;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {




        @FXML
        private AnchorPane ap_main;

        @FXML
        private Button button_send;

        @FXML
        private ScrollPane sp_main;

        @FXML
        private TextField tf_message;

        @FXML
        private VBox vbox_messages;
        private Client client ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client(new Socket("localhost",5014));
            System.out.println("Connected to the server ... ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the server ");
        }


        vbox_messages.heightProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        sp_main.setVvalue((Double) t1);

                    }
                }
        );

        client.receiveMessageFromServer(vbox_messages);
        button_send.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String messageToSend = tf_message.getText();
                        if (!messageToSend.isEmpty()) {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));
                            Text text = new Text(messageToSend);
                            text.setFill(Color.WHITE);
                            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-background-color: rgb(64, 124, 181);" +
                                    "-fx-background-radius: 20px;");

                            textFlow.setPadding(new Insets(5, 10, 5, 10));

                            hBox.getChildren().add(textFlow);
                            vbox_messages.getChildren().add(hBox);

                            client.sendMessageToServer(messageToSend);
                            tf_message.clear();
                        }

                    }
                }
        );

    }
    public static void addLabel(String messageFromServer, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(messageFromServer);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));

        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(255, 242, 204);" +
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> {
            vBox.getChildren().add(hBox);
        });

    }
}
