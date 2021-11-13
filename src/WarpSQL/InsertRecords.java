package WarpSQL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertRecords {

    private static Connection connect() {
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

    public void InsertInto(String table, String columns, ArrayList<String> valores){
        String sql = String.join("\n"
        ,"INSERT INTO "+table+" ("+columns+") VALUES("
        ,String.join("),(",valores)
        ,")");

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void InsertInto(String table, String columns, String valor){
        String sql = String.join("\n"
        ,"INSERT INTO "+table+" ("+columns+") VALUES("
        ,valor
        ,")");
        sql = sql.replace("\n", " ");
        System.out.println(sql);

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void main(String[] args) {
        ArrayList<String> valores = new ArrayList<String>();
        valores.add("1,'llll'");
        valores.add("2,'kkkk'");
        valores.add("3,'zzzz'");
        valores.add("4,'dddd'");
        InsertInto("Marca","id,marca",valores);
    }
}
