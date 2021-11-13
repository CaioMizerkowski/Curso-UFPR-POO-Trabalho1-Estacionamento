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
    
    
    public void selectAll(){
        String sql = "SELECT * FROM modelo";  
            
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
                
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("id") +  "\t" +   
                                    rs.getString("Modelo") + "\t" +  
                                    rs.getInt("idMarca"));  
            }
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
        
    public static void main(String[] args) {  
        SelectRecords app = new SelectRecords();  
        app.selectAll();  
    }  
    
}  