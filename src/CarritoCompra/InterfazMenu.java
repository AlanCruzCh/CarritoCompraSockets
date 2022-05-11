package CarritoCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class InterfazMenu extends JFrame{
    
    JFrame ventanaMenu;
    JPanel plantillaMenu;
    int iteradorLista;
    ImageIcon producto, atras, adelante;
    ListaProducto productosaMostrar;
    JLabel imagenProducto, numeroProducto, nombreProducto, coloresProducto, precioProducto,
            cantidadProducto, descipcionProducto;
    JButton botonAdelante, botonAgregar, botonAtras, botonComprar, botonConsultar,
            botonModificar;
        
    
    /**
     * El cosntructor que nos generara la siguiente ventana, en este caso es la ventana menu
     * @param activarBotones
     */
    public InterfazMenu (boolean activarBotones){
        //this.productosaMostrar = productosDisponibles;
        this.productosaMostrar = InterfazBienvenida.productos_disponibles;
        crearVentana();
        agregarPanel();
        if(activarBotones == true){
            agregarEtiquetasConInfo(activarBotones);
        }else{
            agregarEtiquetasDefauls(activarBotones);
        }
        
        
    }
    
    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaMenu = new JFrame("Ventana Menu");
        ventanaMenu.setSize(930, 420);
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
        plantillaMenu.setBackground(Color.decode("#800018"));
        plantillaMenu.setLayout(null);
        ventanaMenu.add(plantillaMenu);
    }
    
    /**
     * metodo que nos ayudara a agregarle los componentes a nuestra plantilla asi como los
     * eventos de los botonoes 
     */
    private void agregarEtiquetasDefauls(boolean activarBotones){
        producto = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\notfound.png");
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        numeroProducto = new JLabel("Numero ID: NO ");
        numeroProducto.setForeground(Color.WHITE);
        numeroProducto.setHorizontalAlignment(JLabel.CENTER);
        numeroProducto.setBackground(Color.BLACK);
        numeroProducto.setOpaque(true);
        numeroProducto.setBounds(240, 40, 150, 40);
        numeroProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: no encontrado");
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        coloresProducto = new JLabel("Colores: NO ENCONTRADO");
        coloresProducto.setForeground(Color.WHITE);
        coloresProducto.setBackground(Color.BLACK);
        coloresProducto.setOpaque(true);
        coloresProducto.setHorizontalAlignment(JLabel.CENTER);
        coloresProducto.setBounds(240, 110, 460, 40);
        coloresProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: NO");
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(270, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: NO");
        cantidadProducto.setForeground(Color.WHITE);
        cantidadProducto.setBackground(Color.BLACK);
        cantidadProducto.setHorizontalAlignment(JLabel.CENTER);
        cantidadProducto.setOpaque(true);
        cantidadProducto.setBounds(450, 180, 150, 40);
        cantidadProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        descipcionProducto = new JLabel("Descripcion: NO ENCONTRADA");
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
        
        colocarBontones(activarBotones);
    }
    
    private void agregarEtiquetasConInfo(boolean  activarBotones){
        iteradorLista = 0;
        
        producto = new ImageIcon(productosaMostrar.listaProductos.get(iteradorLista).
                ruta_Imagen_Producto);
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        numeroProducto = new JLabel("ID: " + productosaMostrar.listaProductos.get
                (iteradorLista).id_Producto);
        numeroProducto.setForeground(Color.WHITE);
        numeroProducto.setHorizontalAlignment(JLabel.CENTER);
        numeroProducto.setBackground(Color.BLACK);
        numeroProducto.setOpaque(true);
        numeroProducto.setBounds(240, 40, 150, 40);
        numeroProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + productosaMostrar.listaProductos.get
                (iteradorLista).nombre_Producto);
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        coloresProducto = new JLabel("Colores: " + Arrays.toString(productosaMostrar.
                listaProductos.get(iteradorLista).colores_Producto));
        coloresProducto.setForeground(Color.WHITE);
        coloresProducto.setBackground(Color.BLACK);
        coloresProducto.setOpaque(true);
        coloresProducto.setHorizontalAlignment(JLabel.CENTER);
        coloresProducto.setBounds(240, 110, 460, 40);
        coloresProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: " + productosaMostrar.listaProductos.get
                (iteradorLista).precio_Producto);
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(270, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: " + productosaMostrar.listaProductos.get
                (iteradorLista).stock_Producto);
        cantidadProducto.setForeground(Color.WHITE);
        cantidadProducto.setBackground(Color.BLACK);
        cantidadProducto.setHorizontalAlignment(JLabel.CENTER);
        cantidadProducto.setOpaque(true);
        cantidadProducto.setBounds(450, 180, 150, 40);
        cantidadProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        descipcionProducto = new JLabel("Descripcion: " + productosaMostrar.
                listaProductos.get(iteradorLista).descripcion_Producto);
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
        
        colocarBontones(activarBotones);
    }
    
    private void actualizarEtiquetas(){
        
        producto = new ImageIcon(productosaMostrar.listaProductos.get(iteradorLista).
                ruta_Imagen_Producto);
        imagenProducto.setIcon(new ImageIcon(producto.getImage().getScaledInstance(200,200,
                Image.SCALE_SMOOTH)));
        numeroProducto.setText("ID: " + productosaMostrar.listaProductos.get
                (iteradorLista).id_Producto);
        
        nombreProducto.setText("Nombre: " + productosaMostrar.listaProductos.get
                (iteradorLista).nombre_Producto);
        
        coloresProducto.setText("Colores: " + Arrays.toString(productosaMostrar.
                listaProductos.get(iteradorLista).colores_Producto));
        
        precioProducto.setText("Precio: " + productosaMostrar.listaProductos.get
                (iteradorLista).precio_Producto);
        
        cantidadProducto.setText("Cantidad: " + productosaMostrar.listaProductos.get
                (iteradorLista).stock_Producto);
        
        descipcionProducto.setText("Descripcion: " + productosaMostrar.
                listaProductos.get(iteradorLista).descripcion_Producto);
        
        
    }
    
    public void colocarBontones (boolean activarBotonesInterfaz){
        
        botonAgregar = new JButton("Agregar al carrito");
        botonAgregar.setBounds(180, 320, 350, 50);
        botonAgregar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 20));
        
        atras = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\atras.png");
        botonAtras = new JButton(new ImageIcon(atras.getImage()
                .getScaledInstance(50,50, Image.SCALE_SMOOTH)));
        botonAtras.setBounds(80, 320, 80, 50);
        
        adelante = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\adelante.png");
        botonAdelante = new JButton(new ImageIcon(adelante.getImage()
                .getScaledInstance(50,50, Image.SCALE_SMOOTH)));
        botonAdelante.setBounds(550, 320, 80, 50);
        
        botonComprar = new JButton("Comprar carrito");
        botonComprar.setBounds(730, 50, 150, 40);
        botonComprar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        botonConsultar = new JButton("Consultar carrito");
        botonConsultar.setBounds(730, 140, 150, 40);
        botonConsultar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        botonModificar = new JButton("Modificar carrito");
        botonModificar.setBounds(730, 220, 150, 40);
        botonModificar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        botonAdelante.setEnabled(activarBotonesInterfaz);
        botonAgregar.setEnabled(activarBotonesInterfaz);
        botonAtras.setEnabled(activarBotonesInterfaz);
        botonComprar.setEnabled(activarBotonesInterfaz);
        botonConsultar.setEnabled(activarBotonesInterfaz);
        botonModificar.setEnabled(activarBotonesInterfaz);
        
        /**
         * agregando eventos a los botones
         */
        
        ActionListener avanzarAdelante;
        avanzarAdelante = (ActionEvent e) -> {
            iteradorLista = iteradorLista + 1;
            if (iteradorLista < (productosaMostrar.listaProductos.size() - 1)) {
                actualizarEtiquetas();
                botonAtras.setEnabled(activarBotonesInterfaz);
            }
            else{
                actualizarEtiquetas();
                botonAdelante.setEnabled(false);
            }
        };
        
        botonAdelante.addActionListener(avanzarAdelante);
        
        ActionListener avanzarAtras;
        avanzarAtras = (ActionEvent e) -> {
            iteradorLista = iteradorLista - 1;
            if (iteradorLista >=  1) {
                actualizarEtiquetas();
                botonAdelante.setEnabled(activarBotonesInterfaz);
            }
            else{
                actualizarEtiquetas();
                botonAtras.setEnabled(false);
            }
        };
        
        botonAtras.addActionListener(avanzarAtras);
        
        ActionListener avanzarAgregarCarrito;
        avanzarAgregarCarrito = (ActionEvent e) -> {
            InterfazAgregarAlCarrito ventanAgregarCarrito = new InterfazAgregarAlCarrito
                (iteradorLista, activarBotonesInterfaz);
            ventanaMenu.dispose();
        };
        
        botonAgregar.addActionListener(avanzarAgregarCarrito);
        
        plantillaMenu.add(botonAtras);
        plantillaMenu.add(botonAgregar);
        plantillaMenu.add(botonAdelante);
        plantillaMenu.add(botonComprar);
        plantillaMenu.add(botonConsultar);
        plantillaMenu.add(botonModificar);
              
    }
    
}
