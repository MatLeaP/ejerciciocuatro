/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciocuatro.Interfaces;

import com.mycompany.dao.Main;
import com.mycompany.ejerciciocuatro.Task.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Matias
 */
public class DaoImplementsTask implements DaoTask{

    Main main = new Main();
    
    public DaoImplementsTask(){
        
    }
        
    
    
    @Override
    public void registrar(Task task) {
          
        
        try{
            
            Connection conectar = this.main.establecerConeccion();
            PreparedStatement insertar = conectar.prepareStatement("INSERT INTO task (id, nombre) VALUES (?,?)");
            insertar.setInt(1, task.getId());
            insertar.setString(2, task.getTask());
            insertar.executeUpdate();
            
        }catch(Exception e){
            
            System.out.println(e);;
        }
    }

    @Override
    public void modificar(Task task) {
        
        try{
            
            Connection conectar = this.main.establecerConeccion();
            PreparedStatement modificar = conectar.prepareStatement("UPDATE task SET nombre where = ? id = ?");
            modificar.setInt(1, task.getId());
            modificar.setString(2, task.getTask());
            modificar.executeUpdate();
            
        }catch(Exception e){
            
            System.out.println(e);;
        }
    }

    @Override
    public void eliminar(Task task) {
     
        try{
            
            Connection conectar = this.main.establecerConeccion();
            PreparedStatement eliminar = conectar.prepareStatement("DELETE FROM task where id = ?");
            
            eliminar.setInt(1, task.getId());
            
            eliminar.executeUpdate();
            
        }catch(Exception e){
            
            System.out.println(e);;
        }
    }

    @Override
    public void buscar(Task task) {
        
        try{
            Connection conectar = main.establecerConeccion();
            PreparedStatement buscar = conectar.prepareStatement("SELECT * FROM task WHERE id = ?");
            buscar.setInt(1, task.getId());
            
            ResultSet consulta = buscar.executeQuery();
            
            if(consulta.next()){
                task.setId(Integer.parseInt(consulta.getString("id")));
                task.setTask(consulta.getString("nombre"));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
