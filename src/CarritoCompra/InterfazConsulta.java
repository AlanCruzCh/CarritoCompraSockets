package CarritoCompraFinal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfazConsulta {
    
    private static JFrame ventanaConsulta;
    private static JPanel plantillaConsulta;
    private static JLabel cantidadProducto, imagenProducto, nombreProducto, idProducto, 
            colorProducto,precioProducto, apagarProducto;
    private static ImageIcon producto, atras, adelante;
    private static JButton botonModificar, botonEliminar, botonAtras, botonAdelante;
    private int iteradorListaCarrito;
    private static ListaArticulos listaProductos;
    
    public  InterfazConsulta(boolean ActivarBotones){
        
        InterfazConsulta.listaProductos = InterfazInicio.productosAComprar;
        
        crearVentana();
        agregarPanel();
        agregarEtiquetasConInfo();
        colocarBontones();
        agregarEventos(ActivarBotones);
        colocarEntradasSalidas();
        ConfigurarVentana();
    }

    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaConsulta = new JFrame("Ventana Menu");
        ventanaConsulta.setSize(730, 420);
        ventanaConsulta.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaConsulta.setIconImage(mercado);       
    }
    
    private void ConfigurarVentana(){
        ventanaConsulta.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventanaConsulta.setVisible(true);
        ventanaConsulta.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                int opcion = JOptionPane.showConfirmDialog(ventanaConsulta, "¿Seguro que desea "
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

    private void agregarEtiquetasConInfo(){
        iteradorListaCarrito = 0;
                
        producto = new ImageIcon(listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).ruta_Imagen_Producto);
        
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        
        idProducto = new JLabel("ID: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).id_Producto);
        idProducto.setForeground(Color.WHITE);
        idProducto.setHorizontalAlignment(JLabel.CENTER);
        idProducto.setBackground(Color.BLACK);
        idProducto.setOpaque(true);
        idProducto.setBounds(240, 40, 150, 40);
        idProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).nombre_Producto);
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        colorProducto = new JLabel("Colores: " +  listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).color_Producto);
        colorProducto.setForeground(Color.WHITE);
        colorProducto.setBackground(Color.BLACK);
        colorProducto.setOpaque(true);
        colorProducto.setHorizontalAlignment(JLabel.CENTER);
        colorProducto.setBounds(240, 110, 460, 40);
        colorProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).precio_Producto);
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(240, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).stock_Producto);
        cantidadProducto.setForeground(Color.WHITE);
        cantidadProducto.setBackground(Color.BLACK);
        cantidadProducto.setHorizontalAlignment(JLabel.CENTER);
        cantidadProducto.setOpaque(true);
        cantidadProducto.setBounds(400, 180, 150, 40);
        cantidadProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        apagarProducto = new JLabel("A pagar: ");
        apagarProducto.setForeground(Color.WHITE);
        apagarProducto.setBackground(Color.BLACK);
        apagarProducto.setHorizontalAlignment(JLabel.CENTER);
        apagarProducto.setOpaque(true);
        apagarProducto.setBounds(240, 250, 460, 40);
        apagarProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
    }
    
    private void actualizarEtiquetas(){
        
        producto = new ImageIcon(listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).ruta_Imagen_Producto);
        
        imagenProducto.setIcon(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        
        
        idProducto.setText("ID: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).id_Producto);
        
        nombreProducto.setText("Nombre: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).nombre_Producto);
        
        precioProducto.setText("Precio: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).precio_Producto);
        
        cantidadProducto.setText("Cantidad: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).stock_Producto);
        
        colorProducto.setText("Color: " + listaProductos.ListaDeProductos.get
                (iteradorListaCarrito).color_Producto);
        
    }
    
    
    
    private void colocarBontones(){
        
        botonModificar = new JButton("Actualizar Producto");
        botonModificar.setBounds(180, 320, 200, 50);
        botonModificar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 20));
        
        botonEliminar = new JButton("Eliminar producto");
        botonEliminar.setBounds(400, 320, 200, 50);
        botonEliminar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 20));
        
        atras = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\atras.png");
        botonAtras = new JButton(new ImageIcon(atras.getImage()
                .getScaledInstance(50,50, Image.SCALE_SMOOTH)));
        botonAtras.setBounds(80, 320, 80, 50);
        
        adelante = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\adelante.png");
        botonAdelante = new JButton(new ImageIcon(adelante.getImage()
                .getScaledInstance(50,50, Image.SCALE_SMOOTH)));
        botonAdelante.setBounds(620, 320, 80, 50);
                
    }
    
    private void agregarEventos(boolean activarBotonesInterfaz){
        ActionListener avanzarAdelante;
        avanzarAdelante = (ActionEvent e) -> {
            iteradorListaCarrito = iteradorListaCarrito + 1;
            if (iteradorListaCarrito < (listaProductos.ListaDeProductos.size() - 1)) {
                actualizarEtiquetas();
                botonAtras.setEnabled(activarBotonesInterfaz);
            }
            else{
                actualizarEtiquetas();
                botonAtras.setEnabled(activarBotonesInterfaz);
                botonAdelante.setEnabled(false);
            }
        };
        
        botonAdelante.addActionListener(avanzarAdelante);
        
        ActionListener avanzarAtras;
        avanzarAtras = (ActionEvent e) -> {
            iteradorListaCarrito = iteradorListaCarrito - 1;
            if (iteradorListaCarrito >= 1){
                actualizarEtiquetas();
                botonAdelante.setEnabled(activarBotonesInterfaz);
                
            }
            else{
                actualizarEtiquetas();
                botonAtras.setEnabled(false);
                botonAdelante.setEnabled(activarBotonesInterfaz);
            }
        };
        
        botonAtras.addActionListener(avanzarAtras);
        
        ActionListener actualizarProductoEnCarrito;
        actualizarProductoEnCarrito = (ActionEvent e) -> {
            
        };
        
        botonModificar.addActionListener(actualizarProductoEnCarrito);
        
    }
    
    private void colocarEntradasSalidas(){
        plantillaConsulta.add(imagenProducto);
        plantillaConsulta.add(idProducto);
        plantillaConsulta.add(nombreProducto);
        plantillaConsulta.add(colorProducto);
        plantillaConsulta.add(precioProducto);
        plantillaConsulta.add(cantidadProducto);
        plantillaConsulta.add(apagarProducto);
        plantillaConsulta.add(botonAtras);
        plantillaConsulta.add(botonEliminar);
        plantillaConsulta.add(botonAdelante);
        plantillaConsulta.add(botonModificar);
    }
}