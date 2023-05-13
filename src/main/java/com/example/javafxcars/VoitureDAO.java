package com.example.javafxcars;

import com.example.javafxcars.Voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static com.example.javafxcars.DBconnector.connectDB;

public class VoitureDAO {

    private static Connection conn = connectDB();

    public VoitureDAO()
    {
        conn = connectDB() ;
        System.out.println("Connected to Voiture ..");
    }

    public void addVoiture(Voiture voiture)
    {
        try {
            String sql = "INSERT INTO `voiture`(`MatriculeV`, `DateVisite`, `DateAssu`, `Marque`, `Modele`, `PrixParJour`, `image`, `etat`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, voiture.getMatriculeV());
            preparedStatement.setString(2,voiture.getDateVisite());
            preparedStatement.setString(3, voiture.getDateAssu());
            preparedStatement.setString(4, voiture.getMarque());
            preparedStatement.setString(5, voiture.getModele());
            preparedStatement.setInt(6, voiture.getPrixParjr());
            preparedStatement.setString(7, voiture.getImageV());
            preparedStatement.setString(8, voiture.getEtat());

            preparedStatement.executeUpdate();

            System.out.println("Car Inserted successfully ...");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static ResultSet getCars()
    {
        try {
            String sql = "SELECT *"+
                    "FROM voiture  " ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return  preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Voiture getVoiture(String mat)
    {
        Voiture voiture = null;
        try {
            String sql = "SELECT *"+
                    "FROM voiture WHERE MatriculeV=mat";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs  =    preparedStatement.executeQuery();
            while (rs.next()) {

                voiture = new Voiture(rs.getString("MatriculeV"), rs.getDate("dateVisite"), rs.getDate("dateAssu"), rs.getString("Marque"), rs.getString("Modele"), rs.getInt("PrixParjr"), rs.getString("imageV"), rs.getString("etat"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return  voiture ;
    }

}
