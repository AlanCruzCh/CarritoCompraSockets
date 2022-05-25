package CarritoCompraFinal;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class InterfazMenu {
    
    public static ListaArticulos ProductosDisponibles;
    private static int iteradorProductos;
    private static JFrame ventanaMenu;
    private static JPanel plantillaMenu;
    private static ImageIcon producto, atras, adelante;
    private static JLabel imagenProducto, numeroProducto, nombreProducto, coloresProducto,
            precioProducto,cantidadProducto, descipcionProducto;
    private static JButton botonAdelante, botonAgregar, botonAtras, botonComprar, botonConsultar;
    boolean ActivadorDeEntradasSalidas;
    
    
    public InterfazMenu(boolean Activador) {
        this.ActivadorDeEntradasSalidas = Activador;
        ProductosDisponibles = InterfazInicio.productosAMostrar; 
        crearVentana();
        agregarPanel();
        
        if (ActivadorDeEntradasSalidas == true) {
            agregarEtiquetasConInfo();
        }else{
            agregarEtiquetasDefauls();
        }
        colocarBontones();
        activarBotones(ActivadorDeEntradasSalidas);
        eventoBotones(ActivadorDeEntradasSalidas);
        colocarEntradasSalidas();
        ConfigurarVentana();
    }
    
    private void crearVentana(){
        ventanaMenu = new JFrame("Ventana Menu");
        ventanaMenu.setSize(930, 420);
        ventanaMenu.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaMenu.setIconImage(mercado);
    }

    private void ConfigurarVentana(){
        ventanaMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventanaMenu.setVisible(true);
        ventanaMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                int opcion = JOptionPane.showConfirmDialog(ventanaMenu, "¿Seguro que desea "
                        + "salir del sisteme?", "Confirmacion de cierre", JOptionPane.
                        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(opcion == JOptionPane.YES_OPTION){
                    
                    MetodosSockets.mandarIndicacioServidor(InterfazInicio.DireccionIP,
                            InterfazInicio.PuertoServidor, -1);
                    System.exit(0);
                }
            }
        });
    }
    
    private void agregarPanel(){
        plantillaMenu = new JPanel();
        plantillaMenu.setBackground(Color.decode("#800018"));
        plantillaMenu.setLayout(null);
        ventanaMenu.add(plantillaMenu);
        
    }
    
    private void agregarEtiquetasDefauls(){
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
        
    }
    
    private void agregarEtiquetasConInfo(){
        iteradorProductos = 0;
        
        producto = new ImageIcon(ProductosDisponibles.ListaDeProductos.get(iteradorProductos).
                ruta_Imagen_Producto);
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        numeroProducto = new JLabel("ID: " + ProductosDisponibles.ListaDeProductos.
                get(iteradorProductos).id_Producto);
        numeroProducto.setForeground(Color.WHITE);
        numeroProducto.setHorizontalAlignment(JLabel.CENTER);
        numeroProducto.setBackground(Color.BLACK);
        numeroProducto.setOpaque(true);
        numeroProducto.setBounds(240, 40, 150, 40);
        numeroProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + ProductosDisponibles.ListaDeProductos.
                get(iteradorProductos).nombre_Producto);
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        coloresProducto = new JLabel("Colores: " + Arrays.toString(ProductosDisponibles.
                ListaDeProductos.get(iteradorProductos).colores_Producto));
        coloresProducto.setForeground(Color.WHITE);
        coloresProducto.setBackground(Color.BLACK);
        coloresProducto.setOpaque(true);
        coloresProducto.setHorizontalAlignment(JLabel.CENTER);
        coloresProducto.setBounds(240, 110, 460, 40);
        coloresProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: " + ProductosDisponibles.ListaDeProductos.
                get(iteradorProductos).precio_Producto);
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(270, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: " + ProductosDisponibles.ListaDeProductos.
                get(iteradorProductos).stock_Producto);
        cantidadProducto.setForeground(Color.WHITE);
        cantidadProducto.setBackground(Color.BLACK);
        cantidadProducto.setHorizontalAlignment(JLabel.CENTER);
        cantidadProducto.setOpaque(true);
        cantidadProducto.setBounds(450, 180, 150, 40);
        cantidadProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        descipcionProducto = new JLabel("Descripcion: " + ProductosDisponibles.ListaDeProductos.
                get(iteradorProductos).descripcion_Producto);
        descipcionProducto.setForeground(Color.WHITE);
        descipcionProducto.setHorizontalAlignment(JLabel.CENTER);
        descipcionProducto.setBackground(Color.BLACK);
        descipcionProducto.setOpaque(true);
        descipcionProducto.setBounds(20, 250, 680, 40);
        descipcionProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
    
    }
    
    private void colocarBontones (){
        
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
        
    }
    
    private void colocarEntradasSalidas(){
        
        plantillaMenu.add(numeroProducto);
        plantillaMenu.add(nombreProducto);
        plantillaMenu.add(coloresProducto);
        plantillaMenu.add(precioProducto);
        plantillaMenu.add(cantidadProducto);
        plantillaMenu.add(descipcionProducto);
        plantillaMenu.add(imagenProducto);
        plantillaMenu.add(botonAtras);
        plantillaMenu.add(botonAgregar);
        plantillaMenu.add(botonAdelante);
        plantillaMenu.add(botonComprar);
        plantillaMenu.add(botonConsultar); 
        
    }
    
    private void activarBotones(boolean activarBotonesInterfaz){
        botonAdelante.setEnabled(activarBotonesInterfaz);
        botonAgregar.setEnabled(activarBotonesInterfaz);
        botonAtras.setEnabled(activarBotonesInterfaz);
        botonComprar.setEnabled(activarBotonesInterfaz);
        botonConsultar.setEnabled(activarBotonesInterfaz);

    }
    
    private void eventoBotones(boolean activarBotonesInterfaz){
        ActionListener avanzarAdelante;
        avanzarAdelante = (ActionEvent e) -> {
            iteradorProductos = iteradorProductos + 1;
            System.out.println("" + iteradorProductos);
            System.out.println("" + ProductosDisponibles.ListaDeProductos.size());
            if (iteradorProductos < (ProductosDisponibles.ListaDeProductos.size() - 1)) {
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
            
            System.out.println("" + ProductosDisponibles.ListaDeProductos.size());
            iteradorProductos = iteradorProductos - 1;
            if (iteradorProductos >=  1) {
                actualizarEtiquetas();
                botonAdelante.setEnabled(activarBotonesInterfaz);
                
            }
            else{
                actualizarEtiquetas();
                botonAtras.setEnabled(false);
                
            }
        };
        botonAtras.addActionListener(avanzarAtras);
        
        ActionListener AgregarCarrito;
        AgregarCarrito = (ActionEvent e) -> {
            InterfazAgregar ventanAgregarCarrito = new InterfazAgregar
                (iteradorProductos, ActivadorDeEntradasSalidas);
            ventanaMenu.dispose();
        };
        botonAgregar.addActionListener(AgregarCarrito);
        
        ActionListener ConsultarCarrito;
        ConsultarCarrito = (ActionEvent e) -> {
            InterfazConsulta ventanAgregarCarrito = new InterfazConsulta
                (ActivadorDeEntradasSalidas);
            ventanaMenu.dispose();
        };
        botonConsultar.addActionListener(ConsultarCarrito);
        
        ActionListener ComprarCarrito;
        ComprarCarrito = (ActionEvent e) -> {
            MetodosSockets.SerializarListaProductos("C:\\Users\\Alan\\Documents\\noveno_semestre"
                    + "\\Redes 2\\practica1\\CarritoCompra\\src\\Productos_en_cliente\\"
                    + "lista_productos_a_comprar.txt", InterfazInicio.productosAComprar);
            
            boolean validar = MetodosSockets.mandarIndicacioServidor(InterfazInicio.DireccionIP,
                    InterfazInicio.PuertoServidor, 2);
            if (validar == true){
                MetodosSockets.SubirArchivo(InterfazInicio.DireccionIP, 
                    InterfazInicio.PuertoServidor,
                    "C:\\Users\\Alan\\Documents\\noveno_semestre"
                    + "\\Redes 2\\practica1\\CarritoCompra\\src\\Productos_en_cliente\\"
                    + "lista_productos_a_comprar.txt");
            }
            
            
        };
        botonComprar.addActionListener(ComprarCarrito);
        
        
    }
    
    private void actualizarEtiquetas(){
        producto = new ImageIcon(ProductosDisponibles.ListaDeProductos.get(iteradorProductos).
                ruta_Imagen_Producto);
        imagenProducto.setIcon(new ImageIcon(producto.getImage().getScaledInstance(200,200,
                Image.SCALE_SMOOTH)));
        numeroProducto.setText("ID: " + ProductosDisponibles.ListaDeProductos.get
                (iteradorProductos).id_Producto);
        nombreProducto.setText("Nombre: " + ProductosDisponibles.ListaDeProductos.get
                (iteradorProductos).nombre_Producto);
        
        coloresProducto.setText("Colores: " + Arrays.toString(ProductosDisponibles.
                ListaDeProductos.get(iteradorProductos).colores_Producto));
        
        precioProducto.setText("Precio: " + ProductosDisponibles.ListaDeProductos.get
                (iteradorProductos).precio_Producto);
        
        cantidadProducto.setText("Cantidad: " + ProductosDisponibles.ListaDeProductos.get
                (iteradorProductos).stock_Producto);
        
        descipcionProducto.setText("Descripcion: " + ProductosDisponibles.ListaDeProductos.get
                (iteradorProductos).descripcion_Producto);
    }
}
