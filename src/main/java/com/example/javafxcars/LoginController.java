package com.example.javafxcars;

import com.example.javafxcars.DBconnector;
import com.example.javafxcars.getData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginController {

    @FXML
    private AnchorPane leftform;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane mainform;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane rightform;

    @FXML
    private TextField username;
    private Connection connect ;
    private PreparedStatement preparedStatement ;
    private ResultSet resultSet ;
    public  void loginAdmin()
    {
        getData.username = username.getText() ;
        String sql = " SELECT * FROM users WHERE username=? and password=? " ;
        connect = DBconnector.connectDB() ;
        String user = null ;
        String pass = null ;
        try
        {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,username.getText());
            preparedStatement.setString(2,password.getText());
            resultSet = preparedStatement.executeQuery() ;
            Alert alert ;
            if (username.getText().isEmpty() || password.getText().isEmpty() )
            {
                alert = new Alert(Alert.AlertType.ERROR) ;
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields ");
                alert.showAndWait();
            }
            else
            {
                if (resultSet.next() && !Objects.equals(username.getText(), "root") && !Objects.equals(password.getText(), "root"))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION) ;
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully logged in ");
                    user = username.getText();
                    pass = password.getText() ;
                    alert.showAndWait();
                    // hide login screen
                    loginbtn.getScene().getWindow().hide();




                        // link home screen
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Home");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();

                }
                else
                {
                    alert = new Alert(Alert.AlertType.ERROR) ;
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password ");
                    alert.showAndWait();
                }
            }

        }
        catch (
            Exception e
        )
        {
            System.out.println(e.getMessage());
        }
    }

}
