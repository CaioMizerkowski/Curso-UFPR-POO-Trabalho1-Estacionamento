package WarpSQL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectRecords {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Users/marte/Desktop/POO/Tarefa 1/db/estacionamento.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ResultSet selectMarca(){
        String sql = "SELECT id, marca FROM marca";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet selectModelo(){
        String sql = "SELECT id, modelo, idMarca FROM modelo";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet selectModelo(int idMarca) {
        String sql = "SELECT id, modelo FROM modelo WHERE idMarca = "+idMarca;
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet selectCarro(){
        String sql = "SELECT id, placa, dtEntrada, dtSaida, idModelo FROM carro";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet selectCarro(int idModelo) {
        String sql = "SELECT id, placa, dtEntrada, dtSaida FROM carro WHERE idModelo = "+idModelo;
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SelectRecords app = new SelectRecords();
        app.selectModelo();
    }


}