/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ejerciciocuatro.Interfaces;

import com.mycompany.ejerciciocuatro.Task.Task;

/**
 *
 * @author Matias
 */
public interface DaoTask {
    
    public void registrar(Task task);
    public void modificar(Task task);
    public void eliminar(Task task);
    public void buscar(Task task);
}
