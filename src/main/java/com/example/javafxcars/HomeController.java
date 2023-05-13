package com.example.javafxcars;

import com.example.javafxcars.UsersDAO;
import com.example.javafxcars.DBconnector;
import com.example.javafxcars.Users;
import com.example.javafxcars.Voiture;
import com.example.javafxcars.Main;
import com.example.javafxcars.getData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.javafxcars.VoitureDAO.getCars;
public class HomeController implements Initializable {
    @FXML
    private TableColumn<Voiture, Date> DtAssCol;

    @FXML
    private TableColumn<Voiture, Date> DtVisCol;

    @FXML
    private TableColumn<Voiture, String> EatCol;

    @FXML
    private Button InsereBtnCol;

    @FXML
    private TableColumn<Voiture, String> MarqueCol;

    @FXML
    private TableColumn<Voiture, String> ModeleCol;
    private boolean carsListPopulated = false;

    @FXML
    private TableColumn<Voiture, Integer> PrixCol;
    @FXML
    private TableColumn<Voiture, String> matriculeCol;
    @FXML
    private TextField CINtxtfld;

    @FXML
    private DatePicker addassur;

    @FXML
    private AnchorPane addcar;

    @FXML
    private AnchorPane addclient;

    @FXML
    private TextField addetat;

    @FXML
    private TextField addmarque;

    @FXML
    private TextField addmatricule;

    @FXML
    private TextField addmodel;

    @FXML
    private Button addpic;

    @FXML
    private TextField addprice;

    @FXML
    private DatePicker addvisit;

    @FXML
    private TextField agetxtfld;

    @FXML
    private Button carsbtn;

    @FXML
    private Button clientsbtn;

    @FXML
    private AnchorPane home;
    @FXML
    private Text rent_total;
    @FXML
    private Text nbjours;
    @FXML
    private Text prixparjr;
    @FXML
    private Text home_avilablecar;

    @FXML
    private Button home_btn;
    @FXML
    private Button rentBtn;
    @FXML
    private ComboBox<?> rent_carId;

    @FXML
    private Text homeclients;



    @FXML
    private Text homemoney;
    int hometotal = 0 ;

    @FXML
    private Pane innerpane;
    @FXML
    TextArea feedbacktext ;
    @FXML
    private Button feedback ;
    @FXML
    private Label labelpic;

    @FXML
    private AnchorPane location;

    @FXML
    private Button locationbtn;

    @FXML
    private Button logout;

    @FXML
    private Pane mostinnerpane;

    @FXML
    private Label name;

    @FXML
    private TextField nomtxtfld;

    @FXML
    private TextField prenomtxtfld;

    @FXML
    private PasswordField pwdConftxtfld;

    @FXML
    private PasswordField pwdtxtfld;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane sideanchorpane;
    @FXML
    private ComboBox<?> choixMat ;

    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;
    @FXML
    private TextField teltxtfld;

    @FXML
    private TextField txt_search;

    @FXML
    private TextField usernametxtfld;
    @FXML
    private TableView<Voiture> carstableview ;
    @FXML
    private Button chatbtn1;

    public void homeAvailableCars(){

        String sql = "SELECT COUNT(MatriculeV) FROM voiture";


        Connection connect = DBconnector.connectDB();
        int countAC = 0;
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){
                countAC = result.getInt("COUNT(MatriculeV)");
            }

            home_avilablecar.setText(String.valueOf(countAC));

        }catch(Exception e){e.printStackTrace();}

    }  public void homeCustomers(){

        String sql = "SELECT COUNT(CIN) FROM users";


        Connection connect = DBconnector.connectDB();
        int countAC = 0;
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){
                countAC = result.getInt("COUNT(CIN)");
            }

            homeclients.setText(String.valueOf(countAC));

        }catch(Exception e){e.printStackTrace();}

    }

    public void carsListShow() throws SQLException, ParseException {
            ObservableList<Voiture> carsList = FXCollections.observableArrayList() ;
            ResultSet rs = getCars() ;
            while (rs.next())
            {

                Voiture voiture = new Voiture(rs.getString("MatriculeV"), rs.getDate("dateVisite"), rs.getDate("dateAssu"), rs.getString("Marque"), rs.getString("Modele"), rs.getInt("PrixParjr"), rs.getString("imageV"), rs.getString("etat"));
                carsList.add(voiture) ;

            }
        matriculeCol.setCellValueFactory(new PropertyValueFactory<Voiture, String>("matriculeV"));
        MarqueCol.setCellValueFactory(new PropertyValueFactory<Voiture, String>("marque"));
        ModeleCol.setCellValueFactory(new PropertyValueFactory<Voiture, String>("modele"));
        PrixCol.setCellValueFactory(new PropertyValueFactory<Voiture, Integer>("prixParjr"));
        DtVisCol.setCellValueFactory(new PropertyValueFactory<Voiture, Date>("dateVisite"));
        DtAssCol.setCellValueFactory(new PropertyValueFactory<Voiture, Date>("dateAssu"));
        EatCol.setCellValueFactory(new PropertyValueFactory<Voiture, String>("etat"));
        carstableview.getItems().clear();

        carstableview.getItems().addAll(carsList);


    }


    public void UsersId() {

        String sql = "SELECT * FROM users";

        Connection connect = DBconnector.connectDB();

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("CIN"));
            }

            rent_carId.setItems(listData);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void rentCarCarId() {

        String sql = "SELECT * FROM voiture WHERE etat = 'disponible'";

        Connection connect = DBconnector.connectDB();

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("MatriculeV"));
            }

            choixMat.setItems(listData);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUserName()
    {
        name.setText(getData.username);
    }





    public Boolean checkUser() throws SQLException {
        boolean check = false;
        try {
            Connection mycnx = DBconnector.connectDB() ;
            PreparedStatement prep = mycnx.prepareStatement("SELECT * FROM users WHERE CIN =? ");
            //prep.clearParameters()
            prep.setString(1, CINtxtfld.getText());
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String CIN = rs.getString("CIN");
                if (CIN.equals(CINtxtfld.getText())) // on compare les deux chaines de caracteres //
                {
                    check = true;
                    return check;
                } else {
                    check = false;
                    return check;
                }
            }

        } catch (SQLException ex) {
            System.out.println("1"+ex);
        }


        return check;
    }

    public void switchForm(ActionEvent event) throws SQLException, ParseException {

        if (event.getSource() == home_btn) {
            home.setVisible(true);
            addcar.setVisible(false);
            addclient.setVisible(false);
            location.setVisible(false);
            

        } else if (event.getSource() == carsbtn) {
            home.setVisible(false);
            addcar.setVisible(true);
            addclient.setVisible(false);
            location.setVisible(false);
            carsListShow();


        } else if (event.getSource() == clientsbtn) {
            home.setVisible(false);
            addcar.setVisible(false);
            addclient.setVisible(true);
            location.setVisible(false);


        }
        else if (event.getSource() == locationbtn)
        {
            home.setVisible(false);
            addcar.setVisible(false);
            addclient.setVisible(false);
            location.setVisible(true);
            rentCarCarId();
            UsersId();
        }

    }
    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (((Optional<?>) option).get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
                Stage stage = new Stage() ;
                Scene scene = new Scene(fxmlLoader.load(), 888  , 666);
                stage.setTitle("Rent Cars");
                stage.setScene(scene);
                stage.show();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private int countDate;
    public void rentDate(){

        if(rent_carId.getSelectionModel().getSelectedItem() == null || choixMat.getSelectionModel().getSelectedItem() == null)
                {

            rent_dateRented.setValue(null);
            rent_dateReturn.setValue(null);

        }else{

            if(rent_dateReturn.getValue().isAfter(rent_dateRented.getValue())) {

                countDate = rent_dateReturn.getValue().compareTo(rent_dateRented.getValue());
                System.out.println(countDate);
            }
        }
    }

    private int totalP;
    int price = 0;
    public void rentDisplayTotal(){
        rentDate();

        String sql = "SELECT PrixParjr FROM voiture WHERE MatriculeV = '"
                +choixMat.getSelectionModel().getSelectedItem()+"'";

        Connection connect = DBconnector.connectDB();

        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            if(result.next()){
                price = result.getInt("PrixParjr");
            }

            System.out.println(price);
            totalP = price * countDate;


            nbjours.setText(String.valueOf(countDate));
            prixparjr.setText("$"+ price);
            rent_total.setText("$" + totalP);
            hometotal+=totalP ;
            homemoney.setText("$"+hometotal);
            insertFacture();


        }catch(Exception e){e.printStackTrace();}

    }
    public  void insertFacture() throws SQLException {
        String qr="INSERT INTO `location`(`MatriculeV`, `CIN`, `Prix_Total`, `datedeb`, `datefin`) VALUES (?,?,?,?,?)";
        Connection mycnx = DBconnector.connectDB() ;
        PreparedStatement  prep= mycnx.prepareStatement(qr);
        prep.setString(1, (String) choixMat.getSelectionModel().getSelectedItem());
        prep.setString(2, (String) rent_carId.getSelectionModel().getSelectedItem());
        prep.setInt(3,totalP);
        prep.setString(4, String.valueOf(rent_dateRented.getValue()));
        prep.setString(5,  String.valueOf(rent_dateReturn.getValue())) ;
        if (
                choixMat.getSelectionModel().getSelectedItem() != null &&
    rent_carId.getSelectionModel().getSelectedItem() != null)
        {
            prep.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Réservation réussie");
            alert.showAndWait();
        }





    }
    public void chatOpen(ActionEvent event)
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("client.fxml"));

        Scene scene =  null ;
        try {
            scene = new Scene(fxmlLoader.load(), 478, 396);
            System.out.println("errrrrrror ");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();
    }
    public void SendFeedback (ActionEvent event) throws SQLException {
        String qr="INSERT INTO `feedback`('CIN_client`, `description`) VALUES (?,?)";
        String cin = null;
        Connection mycnx = DBconnector.connectDB() ;
        PreparedStatement  prep= mycnx.prepareStatement(qr);
        String sql = "SELECT CIN FROM users  WHERE username = ? " ;
        PreparedStatement prepare = mycnx.prepareStatement(sql);
        prepare.setString(1,getData.username);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            cin =    result.getString("CIN");
        }
        prep.setString(1, cin);
        prep.setString(2, feedbacktext.getText());

        if (
               feedbacktext.getText()!=null
        )
        {
            prep.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Feedback submitted");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        homeAvailableCars();
        displayUserName();
        rentDisplayTotal();
        homeCustomers();

        if (!carsListPopulated) {
            try {
                carsListShow();
                carsListPopulated = true;
            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
