package CarritoCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class InterfazConsultaCarrito {
    
    JFrame ventanaConsulta;
    JPanel plantillaConsulta;
    JLabel cantidadProducto, imagenProducto, nombreProducto, idProducto, colorProducto,
            precioProducto, apagarProducto;
    ImageIcon producto, atras, adelante;
    JButton botonModificar, botonEliminar, botonAtras, botonAdelante;
    JTextField cantidadProductoDeseado;
    JComboBox<String> colorProductoDeseado;    
    CarritoCompra productosEnElCarrito;
    int iteradorListaCarrito,posicionImagenID, puertoServidor;
    String DireccionIpServidor;
    Socket Cliente;
    
    public  InterfazConsultaCarrito(boolean ActivarBotones){
        this.productosEnElCarrito = InterfazBienvenida.productos_carrito;
        this.DireccionIpServidor = InterfazBienvenida.direccion_IP;
        this.puertoServidor = InterfazBienvenida.PuertoServidor;
        this.Cliente = InterfazBienvenida.cliente;
        
        crearVentana();
        agregarPanel();
        agregarEtiquetasConInfo();
        colocarBontonesConsulta(ActivarBotones);
               
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
        ventanaConsulta.setVisible(true);
        ventanaConsulta.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventanaConsulta.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                int opcion = JOptionPane.showConfirmDialog(ventanaConsulta, "??Seguro que desea "
                        + "salir del sisteme?", "Confirmacion de cierre", JOptionPane.
                        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(opcion == JOptionPane.YES_OPTION){
                    try {
                        Cliente = new Socket(DireccionIpServidor, puertoServidor);
                        int bandera = -1;
                        PrintWriter solicitud = new PrintWriter(new OutputStreamWriter(
                            Cliente.getOutputStream()));
                        solicitud.println(bandera);
                        solicitud.flush();
                        solicitud.close();
                        Cliente.close();
                    } catch (IOException ex) {
                        Logger.getLogger(InterfazConsultaCarrito.class.getName()).log(Level.
                                SEVERE, null, ex);
                    }
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
                
        producto = new ImageIcon(productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).imagen_producto);
        
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 200);
        
        
        idProducto = new JLabel("ID: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).id_ProductoCarrito);
        idProducto.setForeground(Color.WHITE);
        idProducto.setHorizontalAlignment(JLabel.CENTER);
        idProducto.setBackground(Color.BLACK);
        idProducto.setOpaque(true);
        idProducto.setBounds(240, 40, 150, 40);
        idProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).nombre_Producto);
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        colorProducto = new JLabel("Colores: " +  productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).color_Producto);
        colorProducto.setForeground(Color.WHITE);
        colorProducto.setBackground(Color.BLACK);
        colorProducto.setOpaque(true);
        colorProducto.setHorizontalAlignment(JLabel.CENTER);
        colorProducto.setBounds(240, 110, 150, 40);
        colorProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).precio_Producto);
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(240, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: " + productosEnElCarrito.carrito_de_Productos.
                get(iteradorListaCarrito).cantidad_ProductoCarrito);
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
        
        plantillaConsulta.add(imagenProducto);
        plantillaConsulta.add(idProducto);
        plantillaConsulta.add(nombreProducto);
        plantillaConsulta.add(colorProducto);
        plantillaConsulta.add(precioProducto);
        plantillaConsulta.add(cantidadProducto);
        plantillaConsulta.add(apagarProducto);
    }
    
    private void actualizarEtiquetas(){
        
        producto = new ImageIcon(productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).imagen_producto);
        
        imagenProducto.setIcon(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        
        
        idProducto.setText("ID: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).id_ProductoCarrito);
        
        nombreProducto.setText("Nombre: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).nombre_Producto);
        
        precioProducto.setText("Precio: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).precio_Producto);
        
        cantidadProducto.setText("Cantidad: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).cantidad_ProductoCarrito);
        
        colorProducto.setText("Color: " + productosEnElCarrito.carrito_de_Productos.get
                (iteradorListaCarrito).color_Producto);
        
    }
    
    
    
    private void colocarBontonesConsulta (boolean activarBotonesInterfaz){
        
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
        
        botonAdelante.setEnabled(activarBotonesInterfaz);
        botonAtras.setEnabled(activarBotonesInterfaz);
        botonModificar.setEnabled(activarBotonesInterfaz);
        botonEliminar.setEnabled(activarBotonesInterfaz);
        
        /**
         * agregando eventos a los botones
         */
        ActionListener avanzarAdelante;
        avanzarAdelante = (ActionEvent e) -> {
            iteradorListaCarrito = iteradorListaCarrito + 1;
            if (iteradorListaCarrito < (productosEnElCarrito.carrito_de_Productos.size() - 1)) {
                actualizarEtiquetas();
                botonAtras.setEnabled(activarBotonesInterfaz);
                colorProductoDeseado.setEnabled(false);
                cantidadProductoDeseado.setEnabled(false);
            }
            else{
                actualizarEtiquetas();
                botonAtras.setEnabled(activarBotonesInterfaz);
                botonAdelante.setEnabled(false);
                colorProductoDeseado.setEnabled(false);
                cantidadProductoDeseado.setEnabled(false);
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
        
        plantillaConsulta.add(botonAtras);
        plantillaConsulta.add(botonEliminar);
        plantillaConsulta.add(botonAdelante);
        plantillaConsulta.add(botonModificar);
                 
    }
}
