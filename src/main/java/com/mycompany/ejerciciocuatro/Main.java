/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciocuatro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matias
 */
public class Main {
    
    Connection conectar = null;
    
    String usuario = "root";
    
    String contrasena = "root";
    
    String db = "task";
    
    String ip = "localhost";
    
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;
    
    //Statement st = null;
    
    //ResultSet rs = null;
    
    public Connection establecerConeccion(){
        
        try{
            
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
            //JOptionPane.showMessageDialog(null, "Se conectó correctamente");
 
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Se producido un error" + e.toString());
        }
        return conectar;
        
        
    }
    
    public static void main(String[] args) throws SQLException {
        
      Main app = new Main();
      app.establecerConeccion();
        
    }

}
