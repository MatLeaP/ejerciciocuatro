/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciocuatro.Task;

/**
 *
 * @author Matias
 */
public class Task {
    
    private int id;
    private String task;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    
    public Task(){       
    }
    
    public void setTask(String task){
        this.task = task;
    }
    
    public String getTask(){
        return this.task;
    }
    
    
}
