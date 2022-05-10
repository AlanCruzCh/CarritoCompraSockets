package CarritoCompra;

import java.awt.*;
import javax.swing.*;

public class InterfazMenu {
    
    JFrame ventanaMenu;
    JPanel plantillaMenu;
    /**
     * El cosntructor que nos generara la siguiente ventana, en este caso es la ventana menu
     * @param Direcccion_IP
     * @param PuertoServidor
     * @param productosDisponibles
     */
    public InterfazMenu (String Direcccion_IP, int PuertoServidor, ListaProducto
            productosDisponibles){
        crearVentana();
        agregarPanel();
        agregarComponentesdeFecto();
    }
    
    public InterfazMenu(){
        crearVentana();
        agregarPanel();
        agregarComponentesdeFecto();
        agregarComponentesdeFecto();
    }
    
    
    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaMenu = new JFrame("Ventana Menu");
        ventanaMenu.setSize(800, 500);
        ventanaMenu.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaMenu.setIconImage(mercado);
        ventanaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaMenu.setVisible(true);
        
    }
    
    /**
     * metodo que le agrega una JPanel a nuestra ventana para poder agregar los elementos que 
     * necesitamos sobre ella 
     */
    private void agregarPanel(){
        plantillaMenu = new JPanel();
        plantillaMenu.setBackground(Color.DARK_GRAY);
        plantillaMenu.setLayout(null);
        ventanaMenu.add(plantillaMenu);
    }
    
    /**
     * metodo que nos ayudara a agregarle los componentes a nuestra plantilla asi como los eventos
     * de los botonoes 
     */
    private void agregarComponentesdeFecto(){
        ImageIcon producto = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\notfound.png");
        JLabel imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        JLabel nombreProducto = new JLabel("Nombre no encontrado");
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(240, 20, 450, 50);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        plantillaMenu.add(nombreProducto);
        plantillaMenu.add(imagenProducto);
    }
    
    public static void main(String[] args) {
        InterfazMenu ventana = new InterfazMenu();
    }
}
