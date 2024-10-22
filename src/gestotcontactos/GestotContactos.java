/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestotcontactos;

import com.gestorcontactos.VentanaPrincipal;

/**
 *
 * @author luise
 */
public class GestotContactos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecutar la aplicación Swing en el hilo de eventos
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear e iniciar la ventana principal
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            }
        });
    }
    
}
