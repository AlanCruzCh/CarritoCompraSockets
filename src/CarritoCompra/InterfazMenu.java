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
        agregarEtiquetasDefauls();
    }
    
    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaMenu = new JFrame("Ventana Menu");
        ventanaMenu.setSize(1000, 700);
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
    private void agregarEtiquetasDefauls(){
        ImageIcon producto = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\notfound.png");
        JLabel imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        JLabel numeroProducto = new JLabel("Numero ID: NO ");
        numeroProducto.setForeground(Color.WHITE);
        numeroProducto.setHorizontalAlignment(JLabel.CENTER);
        numeroProducto.setBackground(Color.BLACK);
        numeroProducto.setOpaque(true);
        numeroProducto.setBounds(240, 40, 150, 40);
        numeroProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        JLabel nombreProducto = new JLabel("Nombre: no encontrado");
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        JLabel coloresProducto = new JLabel("Colores: NO ENCONTRADO");
        coloresProducto.setForeground(Color.WHITE);
        coloresProducto.setBackground(Color.BLACK);
        coloresProducto.setOpaque(true);
        coloresProducto.setHorizontalAlignment(JLabel.CENTER);
        coloresProducto.setBounds(240, 110, 460, 40);
        coloresProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        JLabel precioProducto = new JLabel("Precio: NO");
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(270, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        JLabel cantidadProducto = new JLabel("Cantidad: NO");
        cantidadProducto.setForeground(Color.WHITE);
        cantidadProducto.setBackground(Color.BLACK);
        cantidadProducto.setHorizontalAlignment(JLabel.CENTER);
        cantidadProducto.setOpaque(true);
        cantidadProducto.setBounds(450, 180, 150, 40);
        cantidadProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        JLabel descipcionProducto = new JLabel("Descripcion: NO ENCONTRADA");
        descipcionProducto.setForeground(Color.WHITE);
        descipcionProducto.setHorizontalAlignment(JLabel.CENTER);
        descipcionProducto.setBackground(Color.BLACK);
        descipcionProducto.setOpaque(true);
        descipcionProducto.setBounds(20, 250, 680, 40);
        descipcionProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        plantillaMenu.add(numeroProducto);
        plantillaMenu.add(nombreProducto);
        plantillaMenu.add(coloresProducto);
        plantillaMenu.add(precioProducto);
        plantillaMenu.add(cantidadProducto);
        plantillaMenu.add(descipcionProducto);
        plantillaMenu.add(imagenProducto);
        
        colocarBontones(numeroProducto, nombreProducto, coloresProducto, precioProducto);
    }
    
    public void colocarBontones (JLabel numeroProducto, JLabel nombreProducto, JLabel
            coloresProducto, JLabel precioProducto){
        
        JButton botonComprar = new JButton("Agregar al carrito");
        botonComprar.setBounds(180, 320, 350, 50);
        botonComprar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        botonComprar.setEnabled(false);
        
        plantillaMenu.add(botonComprar);
        
    }
    
}
