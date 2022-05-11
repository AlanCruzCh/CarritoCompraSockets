package CarritoCompra;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfazConsultaCarrito {
    
    JFrame ventanaConsulta;
    JPanel plantillaConsulta;
    CarritoCompra productosEnElCarrito;
    
    public  InterfazConsultaCarrito(boolean ActivarBotones){
        this.productosEnElCarrito = InterfazBienvenida.productos_carrito;
        
        crearVentana();
        agregarPanel();
        productosEnElCarrito.mostrarCarritoProductos();
        
    }

    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaConsulta = new JFrame("Ventana Menu");
        ventanaConsulta.setSize(930, 420);
        ventanaConsulta.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaConsulta.setIconImage(mercado);
        ventanaConsulta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaConsulta.setVisible(true);
    }
    
    /**
     * metodo que le agrega una JPanel a nuestra ventana para poder agregar los elementos que 
     * necesitamos sobre ella 
     */
    private void agregarPanel(){
        plantillaConsulta = new JPanel();
        plantillaConsulta.setBackground(Color.decode("#000080"));
        plantillaConsulta.setLayout(null);
        ventanaConsulta.add(plantillaConsulta);
    }

    
    
}
