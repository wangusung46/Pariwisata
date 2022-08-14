package Koneksi;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
     private static java.sql.Connection koneksi;
     
      public static java.sql.Connection getConnection(){
        if(koneksi == null){
          try{
              String url = "jdbc:mysql://localhost/pulaupari";
              //String url = "jdbc:mysql://localhost/127.0.0.1:3308/pulaupari";
              String user = "root";
              String password = "";
              
              DriverManager.registerDriver(new com.mysql.jdbc.Driver());
              
              koneksi = DriverManager.getConnection(url, user, password);
          }catch(SQLException exception){
              System.out.println("Error Membuat Koneksi");
          }  
        }
        return koneksi;

    }
}
