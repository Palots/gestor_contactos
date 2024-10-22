/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gestorcontactos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luise
 */
public class FormularioContacto extends javax.swing.JFrame {

    private JTextField txtNombre, txtApellido, txtTelefono, txtCorreo, txtDireccion;
    private JRadioButton rbSoltero, rbCasado, rbUnionLibre, rbDivorciado;
    private ButtonGroup bgEstadoCivil;
    private JProgressBar progressBar;
    private JButton btnGuardar, btnCancelar;
    private Contacto contacto;
    private ActionListener onSaveListener;
    
    
    /**
     * Creates new form FormularioContacto
     */
    public FormularioContacto(Contacto contacto, ActionListener onSaveListener) {
        this.contacto = contacto;
        this.onSaveListener = onSaveListener;
        // Configurar el formulario
        setTitle("Formulario de Contacto");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 5, 5));

        // Crear campos de texto y etiquetas
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();

        // Estado civil usando ButtonGroup
        JLabel lblEstadoCivil = new JLabel("Estado Civil:");
        rbSoltero = new JRadioButton("Soltero");
        rbCasado = new JRadioButton("Casado");
        rbUnionLibre = new JRadioButton("Unión Libre");
        rbDivorciado = new JRadioButton("Divorciado");
        bgEstadoCivil = new ButtonGroup();
        bgEstadoCivil.add(rbSoltero);
        bgEstadoCivil.add(rbCasado);
        bgEstadoCivil.add(rbUnionLibre);
        bgEstadoCivil.add(rbDivorciado);

        JPanel panelEstadoCivil = new JPanel();
        panelEstadoCivil.setLayout(new FlowLayout());
        panelEstadoCivil.add(rbSoltero);
        panelEstadoCivil.add(rbCasado);
        panelEstadoCivil.add(rbUnionLibre);
        panelEstadoCivil.add(rbDivorciado);

        // Barra de progreso
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Añadir componentes al formulario
        add(lblNombre); add(txtNombre);
        add(lblApellido); add(txtApellido);
        add(lblTelefono); add(txtTelefono);
        add(lblCorreo); add(txtCorreo);
        add(lblDireccion); add(txtDireccion);
        add(lblEstadoCivil); add(panelEstadoCivil);
        add(progressBar);
        add(btnGuardar); add(btnCancelar);

        // Acción de botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (validarCampos()) {
            guardarContacto();
            if (onSaveListener != null) {
                // Cambia contacto por this para referirte al formulario
                ActionEvent event = new ActionEvent(FormularioContacto.this, ActionEvent.ACTION_PERFORMED, null);
                onSaveListener.actionPerformed(event);
            }
            JOptionPane.showMessageDialog(null, "Contacto guardado exitosamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos correctamente");
        }
    }
});



        // Acción de botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar el formulario
            }
        });

        // Cargar datos si es edición
        if (contacto != null) {
            cargarDatos();
        }
    }
    
    private void guardarContacto() {
        if (contacto == null) {
            contacto = new Contacto();
        }
        contacto.setNombre(txtNombre.getText());
        contacto.setApellido(txtApellido.getText());
        contacto.setTelefono(txtTelefono.getText());
        contacto.setCorreo(txtCorreo.getText());
        contacto.setDireccion(txtDireccion.getText());

        if (rbSoltero.isSelected()) {
            contacto.setEstadoCivil("Soltero");
        } else if (rbCasado.isSelected()) {
            contacto.setEstadoCivil("Casado");
        } else if (rbUnionLibre.isSelected()) {
            contacto.setEstadoCivil("Unión Libre");
        } else if (rbDivorciado.isSelected()) {
            contacto.setEstadoCivil("Divorciado");
        }
    }

    private void cargarDatos() {
        txtNombre.setText(contacto.getNombre());
        txtApellido.setText(contacto.getApellido());
        txtTelefono.setText(contacto.getTelefono());
        txtCorreo.setText(contacto.getCorreo());
        txtDireccion.setText(contacto.getDireccion());

        switch (contacto.getEstadoCivil()) {
            case "Soltero": rbSoltero.setSelected(true); break;
            case "Casado": rbCasado.setSelected(true); break;
            case "Unión Libre": rbUnionLibre.setSelected(true); break;
            case "Divorciado": rbDivorciado.setSelected(true); break;
        }
    }

    private boolean validarCampos() {
        return !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() &&
               !txtTelefono.getText().isEmpty() && !txtCorreo.getText().isEmpty() &&
               !txtDireccion.getText().isEmpty() && bgEstadoCivil.getSelection() != null;
    }
    
    public Contacto getContacto() {
    // Devuelve el contacto que se ha creado o editado
    return contacto; // Asegúrate de que la variable `contacto` contenga el objeto correcto.
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
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        // Aquí se pasa null para el contacto y un ActionListener vacío para pruebas
        FormularioContacto form = new FormularioContacto(null, event -> {
            // No hacer nada; este listener es solo para que el constructor acepte los parámetros
        });
        form.setVisible(true);
    });
}




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
