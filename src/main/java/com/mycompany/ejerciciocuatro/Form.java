/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ejerciciocuatro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matias
 */
public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Form
     */
    public Form() {
        initComponents();
        setLocationRelativeTo(null);
    }

    
      public void agregar(String nombreTarea, String descripcionTarea){
        //sentencia sql
        String sql = "INSERT INTO tareas (nombreTarea, descripcionTarea) VALUES (?, ?)";
        
        //instacia de la clase main
        Main con = new Main();
        
        //conexion a base de datos
        Connection conexion = con.establecerConeccion();
        
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            
            //valor de parametro de la sentencia
            preparedStatement.setString(1, nombreTarea);
            preparedStatement.setString(2, descripcionTarea);
            
            //ejecutar la sentencia sql y obtenemos las filas 
            int filasAfectadas = preparedStatement.executeUpdate();
            
            //comprobacion  si se agrego el registro
            if(filasAfectadas > 0){
                JOptionPane.showMessageDialog(null,"Tarea agregada.");
            }else{
                JOptionPane.showMessageDialog(null,"No se ha podido agregar la tarea.");
            }            
        }catch(SQLException e){
        e.printStackTrace();
        }
    }
      
       public void nuevo(){
        nombreTarea.setText("");
        nombreTarea.requestFocus();
        descripcionTarea.setText("");
        descripcionTarea.requestFocus();
    }
       
       public void mostrar(){
        //sentencia sql 
        String sql = "SELECT * FROM tareas";
        
        //creacion de instancia de clase main para conectar con base de datos
        Main con = new Main();
        
        Connection conexion = con.establecerConeccion();
        
        System.out.println(sql);
        
        DefaultTableModel model =  new DefaultTableModel();
        
        try{
           //declaracion para la consulta
           Statement st = conexion.createStatement();
           
           ResultSet rs = st.executeQuery(sql);
           
           //informaciond de la consulta
           
           ResultSetMetaData metaData = rs.getMetaData();
            
           int numColumnas = metaData.getColumnCount();
           
           for(int column = 1; column <= numColumnas; column ++){
               model.addColumn(metaData.getColumnName(column));
           }
           
           //agrega filas
           while(rs.next()){
               Object[] rowData= new Object[numColumnas] ;
               for(int i = 0 ; i < numColumnas; i++){
                   rowData[i]= rs.getObject(i +1);
               }
               model.addRow(rowData);
           }
           
           //asignamos el modelo de la tabla al componente table
           tablaTareas.setModel(model);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
       
       public int obtenerIdSeleccionado() {
        //Obetener la fila seleccionada en la tablas
        int filaSeleccionada = tablaTareas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla");
            return -1; //Retornamos un valor negativo para indicar que no se ha seleccionado nada
        }

        int id = (int) tablaTareas.getValueAt(filaSeleccionada, 0);
        return id;
    }
       
        public void modificar() {

        //Obtener el nuevo nombnre de la carrera desde un campo de texto
        String nuevaTarea = nombreTarea.getText();
        String nuevaDescripcion = descripcionTarea.getText();
        
        
        //Verificar si se proporcionó un nuevo nombre
        if (nuevaTarea.isEmpty() || nuevaDescripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar nuevos datos para poder actualizar la tarea");
        } else {
            Main con = new Main();
            Connection conexion = con.establecerConeccion();

            if (conexion != null) {
                try {
                    //Obtener el Id de la carrera seleccionada en la tabla
                    int idSeleccionado = obtenerIdSeleccionado();

                    if (idSeleccionado != -1) {
                        String sql = "UPDATE tareas SET nombreTarea = ?, descripcionTarea = ? WHERE id = ?";


                        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

                        preparedStatement.setString(1, nuevaTarea);
                        preparedStatement.setString(2, nuevaDescripcion);

                        preparedStatement.setInt(3, idSeleccionado);

                        //Ejecutar la actualización
                        int filasAfectadas = preparedStatement.executeUpdate();

                        if (filasAfectadas > 0) {
                            //La actualización fue exitosa
                            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
                        } else {
                            //Caso contrario que pase todo mal
                            JOptionPane.showMessageDialog(null, "No se pudo modificar la tarea");
                        }
                        //Cerrar la sentencia preparada
                        preparedStatement.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar la tarea");
                    }
                    //Cerrar la conexion
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al modificar la tarea");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion a la base de datos");
            }
        }
    }
        
        public void eliminar() {

        int filaSeleccionada = tablaTareas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            int idEliminar = (int) tablaTareas.getValueAt(filaSeleccionada, 0);
            String sql = "DELETE FROM tareas WHERE id = " + idEliminar;

            try {
                Main con = new Main();

                Connection conexion = con.establecerConeccion();
                Statement st = conexion.createStatement();

                int filasAfectadas = st.executeUpdate(sql);

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "La tarea fué eliminada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la tarea");
                }
                st.close();
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreTarea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        idTarea = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionTarea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTareas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("TO-DO LIST");

        jLabel2.setText("Ingrese el nombre de su tarea");

        jLabel3.setText("Ingrese la descripcion de la tarea");

        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnActualizar.setText("MODIFICAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel4.setText("Id");

        idTarea.setEditable(false);

        descripcionTarea.setColumns(20);
        descripcionTarea.setRows(5);
        jScrollPane2.setViewportView(descripcionTarea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnConsultar)
                        .addGap(31, 31, 31)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar))
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        tablaTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE DE LA TAREA", "DESCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTareasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTareas);
        if (tablaTareas.getColumnModel().getColumnCount() > 0) {
            tablaTareas.getColumnModel().getColumn(0).setMinWidth(50);
            tablaTareas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaTareas.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(299, 299, 299)
                                .addComponent(jLabel1)))
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombretarea= nombreTarea.getText();
        String descripciontarea= descripcionTarea.getText();
        
        agregar(nombretarea, descripciontarea);
        mostrar();
        nuevo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        mostrar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificar();
        mostrar();
        nuevo();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        mostrar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTareasMouseClicked
        int fila = tablaTareas.getSelectedRow();
            if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Tarea no seleccionada");
        } else {
            int id = Integer.parseInt((String) tablaTareas.getValueAt(fila, 0).toString());
            String nom = (String) tablaTareas.getValueAt(fila, 1);
            String des = (String) tablaTareas.getValueAt(fila, 2);
            
            idTarea.setText("" + id);
            nombreTarea.setText(nom);
            descripcionTarea.setText(des);
        }
    }//GEN-LAST:event_tablaTareasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JTextArea descripcionTarea;
    private javax.swing.JTextField idTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombreTarea;
    private javax.swing.JTable tablaTareas;
    // End of variables declaration//GEN-END:variables
}
