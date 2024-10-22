/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gestorcontactos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luise
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEditar, btnEliminar, btnBuscar;
    private JTextField txtBuscar;
    

    /**
     * Creates new form VentanaPrincipal
     */
     public VentanaPrincipal() {
        // Configurar la ventana principal
        setTitle("Gestor de Contactos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuNuevo = new JMenuItem("Nuevo");
        JMenuItem menuGuardar = new JMenuItem("Guardar");
        JMenuItem menuSalir = new JMenuItem("Salir");

        menuArchivo.add(menuNuevo);
        menuArchivo.add(menuGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuSalir);
        menuBar.add(menuArchivo);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem menuAcercaDe = new JMenuItem("Acerca de");
        menuAyuda.add(menuAcercaDe);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        // Panel superior para búsqueda
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");

        panelSuperior.add(new JLabel("Buscar Contacto: "));
        panelSuperior.add(txtBuscar);
        panelSuperior.add(btnBuscar);

        // Crear la tabla para mostrar los contactos
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Apellido", "Teléfono", "Correo"}, 0);
        tablaContactos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaContactos);

        // Panel inferior para botones de control
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        panelInferior.add(btnAgregar);
        panelInferior.add(btnEditar);
        panelInferior.add(btnEliminar);

        // Añadir componentes a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones para los botones de la barra de menú
        menuSalir.addActionListener(e -> System.exit(0));

        // Acciones para los botones principales
        btnAgregar.addActionListener(e -> {
            FormularioContacto form = new FormularioContacto(null, event -> {
                // Cambia la forma en que obtienes el nuevo contacto
                Contacto nuevoContacto = ((FormularioContacto) event.getSource()).getContacto();
                agregarContactoATabla(nuevoContacto);
            });
            form.setVisible(true);
        });


        btnEditar.addActionListener(e -> {
            int selectedRow = tablaContactos.getSelectedRow();
            if (selectedRow != -1) {
                Contacto contactoSeleccionado = obtenerContactoDeFila(selectedRow);
                FormularioContacto form = new FormularioContacto(contactoSeleccionado, event -> {
                    Contacto contactoActualizado = ((FormularioContacto) event.getSource()).getContacto();
                    modeloTabla.setValueAt(contactoActualizado.getNombre(), selectedRow, 0);
                    modeloTabla.setValueAt(contactoActualizado.getApellido(), selectedRow, 1);
                    modeloTabla.setValueAt(contactoActualizado.getTelefono(), selectedRow, 2);
                    modeloTabla.setValueAt(contactoActualizado.getCorreo(), selectedRow, 3);
                });
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un contacto para editar.");
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tablaContactos.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "¿Está seguro de que desea eliminar este contacto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Eliminar el contacto seleccionado del modelo de tabla
                    modeloTabla.removeRow(selectedRow);
                    // Nota: Debes actualizar la lista de contactos que manejes
                    JOptionPane.showMessageDialog(null, "Contacto eliminado correctamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un contacto para eliminar.");
            }
        }
        });


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = txtBuscar.getText().trim().toLowerCase();
                if (!searchText.isEmpty()) {
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        String nombre = modeloTabla.getValueAt(i, 0).toString().toLowerCase();
                        String telefono = modeloTabla.getValueAt(i, 2).toString();
                        if (nombre.contains(searchText) || telefono.contains(searchText)) {
                            // Selecciona la fila encontrada y asegúrate de que esté visible
                            tablaContactos.setRowSelectionInterval(i, i);
                            tablaContactos.scrollRectToVisible(new Rectangle(tablaContactos.getCellRect(i, 0, true)));
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Contacto no encontrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre o teléfono para buscar.");
                }
                }
        });
    }
    private void agregarContactoATabla(Contacto contacto) {
        modeloTabla.addRow(new Object[]{
            contacto.getNombre(), contacto.getApellido(), contacto.getTelefono(), contacto.getCorreo()
        });
    }

    private Contacto obtenerContactoDeFila(int fila) {
        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        String apellido = (String) modeloTabla.getValueAt(fila, 1);
        String telefono = (String) modeloTabla.getValueAt(fila, 2);
        String correo = (String) modeloTabla.getValueAt(fila, 3);

        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setTelefono(telefono);
        contacto.setCorreo(correo);
        return contacto;
    }
     
     


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}