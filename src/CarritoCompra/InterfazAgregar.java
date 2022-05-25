package CarritoCompraFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfazAgregar {
    public static ListaArticulos articuloComprar, productoAAgregar;
    private static JFrame ventanaCarrito;
    private static JPanel plantillaCarrito;
    private static JLabel idProducto, imagenProducto, nombreProducto, precioProducto, 
            colorProducto, apagarProducto, cantidadProducto;
    private static ImageIcon producto;
    private static JTextField cantidadProductoDeseado;
    private static JComboBox<String> colorProductoDeseado;
    private static JButton botonConfirmar, botonRegresar, botonCalcular;
    int indiceArticulo;
    boolean ActivadorEntradasSalidas;
    ProductoGeneral productoSelecionado;
    
    public InterfazAgregar(int indiceProducto, boolean Activador){
        
        this.indiceArticulo = indiceProducto;
        InterfazAgregar.productoAAgregar = InterfazInicio.productosAMostrar;
        InterfazAgregar.articuloComprar = InterfazInicio.productosAComprar;
        this.ActivadorEntradasSalidas = Activador;
        
        crearVentana();
        agregarPanel();
        colocarEtiquetas();
        colocarBotones(ActivadorEntradasSalidas);
        eventoBotones(ActivadorEntradasSalidas);
        colocarEntradasDatos();
        colocarEntradasSalidas();
    }
    
    private void crearVentana(){
        ventanaCarrito = new JFrame("Agregar Producto");
        ventanaCarrito.setSize(930, 420);
        ventanaCarrito.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaCarrito.setIconImage(mercado);
        ventanaCarrito.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCarrito.setVisible(true);
    }
       
    private void agregarPanel(){
        plantillaCarrito = new JPanel();
        plantillaCarrito.setBackground(Color.decode("#000080"));
        plantillaCarrito.setLayout(null);
        ventanaCarrito.add(plantillaCarrito);
    }

    private void colocarEtiquetas(){
        producto = new ImageIcon(productoAAgregar.ListaDeProductos.get(indiceArticulo).
                ruta_Imagen_Producto);
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 260);
        
        idProducto = new JLabel("ID: " + productoAAgregar.ListaDeProductos.get
                (indiceArticulo).id_Producto);
        idProducto.setForeground(Color.WHITE);
        idProducto.setHorizontalAlignment(JLabel.CENTER);
        idProducto.setBackground(Color.BLACK);
        idProducto.setOpaque(true);
        idProducto.setBounds(240, 40, 150, 40);
        idProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + productoAAgregar.ListaDeProductos.get
                (indiceArticulo).nombre_Producto);
        nombreProducto.setForeground(Color.WHITE);
        nombreProducto.setBackground(Color.BLACK);
        nombreProducto.setHorizontalAlignment(JLabel.CENTER);
        nombreProducto.setOpaque(true);
        nombreProducto.setBounds(400, 40, 300, 40);
        nombreProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        colorProducto = new JLabel("Colores: ");
        colorProducto.setForeground(Color.WHITE);
        colorProducto.setBackground(Color.BLACK);
        colorProducto.setOpaque(true);
        colorProducto.setHorizontalAlignment(JLabel.CENTER);
        colorProducto.setBounds(240, 110, 150, 40);
        colorProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        precioProducto = new JLabel("Precio: " + productoAAgregar.ListaDeProductos.get
                (indiceArticulo).precio_Producto);
        precioProducto.setForeground(Color.WHITE);
        precioProducto.setBackground(Color.BLACK);
        precioProducto.setHorizontalAlignment(JLabel.CENTER);
        precioProducto.setOpaque(true);
        precioProducto.setBounds(240, 180, 150, 40);
        precioProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        cantidadProducto = new JLabel("Cantidad: ");
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
    
    private void colocarEntradasSalidas(){
        plantillaCarrito.add(idProducto);
        plantillaCarrito.add(nombreProducto);
        plantillaCarrito.add(colorProducto);
        plantillaCarrito.add(precioProducto);
        plantillaCarrito.add(cantidadProducto);
        plantillaCarrito.add(apagarProducto);
        plantillaCarrito.add(imagenProducto);
        plantillaCarrito.add(cantidadProductoDeseado);
        plantillaCarrito.add(colorProductoDeseado);
        plantillaCarrito.add(botonConfirmar);
        plantillaCarrito.add(botonCalcular);
        plantillaCarrito.add(botonRegresar);
        
    }
    
    private void colocarBotones(boolean activarBotones){
        botonConfirmar = new JButton("Agregar Producto");
        botonConfirmar.setBounds(730, 50, 150, 40);
        botonConfirmar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        botonConfirmar.setEnabled(activarBotones);
        
        botonCalcular = new JButton("Calcular Costo");
        botonCalcular.setBounds(730, 140, 150, 40);
        botonCalcular.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        botonCalcular.setEnabled(activarBotones);
        
        botonRegresar = new JButton("Regresar Menu");
        botonRegresar.setBounds(730, 220, 150, 40);
        botonRegresar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        botonRegresar.setEnabled(activarBotones);
        
    }
    
    private void colocarEntradasDatos(){
        cantidadProductoDeseado = new JTextField(productoAAgregar.ListaDeProductos.get
                (indiceArticulo).stock_Producto);
        cantidadProductoDeseado.setHorizontalAlignment(JTextField.CENTER);
        cantidadProductoDeseado.setBounds(570, 180, 130, 40);
        cantidadProductoDeseado.setFont(new Font("Bell MT", Font.ITALIC, 15));
        
        colorProductoDeseado = new JComboBox<>(productoAAgregar.ListaDeProductos.get
                (indiceArticulo).colores_Producto);
        colorProductoDeseado.setBounds(420, 110, 280, 40);
        colorProductoDeseado.setFont(new Font("Bell MT", Font.PLAIN, 15));
    }
    
    private void eventoBotones(boolean activarBotones){
        ActionListener calcularCosto;
        calcularCosto = (ActionEvent e) -> {
            String confirmarVacio = cantidadProductoDeseado.getText();
            
            if (confirmarVacio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ha indicado la cantidad del producto\n"
                        + "que desea", "No agrego una cantidad", 
                        JOptionPane.ERROR_MESSAGE);    
            }
            else{
                int confimarProducto = articuloComprar.posicionArticuloPorID
                        (productoAAgregar.ListaDeProductos.get(indiceArticulo).id_Producto);
                if (confimarProducto == -1){
                    // Producto Nuevo
                    int cantidadDeseada = Integer.parseInt(cantidadProductoDeseado.getText());
                    if(cantidadDeseada <= productoAAgregar.ListaDeProductos.get
                        (indiceArticulo).stock_Producto){
                        float cuenta = cantidadDeseada * productoAAgregar.ListaDeProductos.get
                        (indiceArticulo).precio_Producto;
                        apagarProducto.setText("A pagar: " + cuenta);
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "La cantidad de productos que desea\n"
                            + "supera la cantidad del producto\n disponible", "Acaba de "
                            + "arrebasar el stock disponible", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    //el producto ya exixiste
                    int cantidadSuma = articuloComprar.ListaDeProductos.get
                            (confimarProducto).stock_Producto + (Integer.parseInt
                            (cantidadProductoDeseado.getText()));
                    if(cantidadSuma <= productoAAgregar.ListaDeProductos.get
                        (indiceArticulo).stock_Producto){
                        int cantidadDeseada = Integer.parseInt(cantidadProductoDeseado.
                                getText());
                        float cuenta = cantidadDeseada * productoAAgregar.ListaDeProductos.get
                        (indiceArticulo).precio_Producto;
                        apagarProducto.setText("A pagar: " + cuenta);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "La cantidad de productos que desea"
                                + "\nsupera la cantidad del producto\n disponible", "Acaba de "
                                + "arrebasar el stock disponible", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }   
        };
        botonCalcular.addActionListener(calcularCosto);
        
        ActionListener RegresarAlMenu;
        RegresarAlMenu = (ActionEvent e) -> {
            InterfazMenu ventanaMenu = new InterfazMenu(activarBotones);
            ventanaCarrito.dispose();
        };
        botonRegresar.addActionListener(RegresarAlMenu);
        
        ActionListener agregarProductoAlCarrito;
        agregarProductoAlCarrito = (ActionEvent e) -> {
            String confirmarVacio = cantidadProductoDeseado.getText();
            if (confirmarVacio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ha indicado la cantidad del producto\n"
                        + "que desea", "No agrego una cantidad", 
                        JOptionPane.ERROR_MESSAGE);    
            }
            else{
                   
                productoSelecionado = new ProductoGeneral(productoAAgregar.ListaDeProductos.get
                        (indiceArticulo).id_Producto,
                        productoAAgregar.ListaDeProductos.get(indiceArticulo).nombre_Producto, 
                        String.valueOf(colorProductoDeseado.getSelectedItem()),
                        productoAAgregar.ListaDeProductos.get(indiceArticulo).
                        ruta_Imagen_Producto,
                        productoAAgregar.ListaDeProductos.get(indiceArticulo).
                        descripcion_Producto,
                        productoAAgregar.ListaDeProductos.get(indiceArticulo).precio_Producto,
                        Integer.parseInt(confirmarVacio));
                articuloComprar.agregarProductosLista(productoSelecionado);
                
                JOptionPane.showMessageDialog(null, "Producto añadido al carrito de forma"
                        + " exitosa", "Operacion EXITOSA", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                InterfazMenu ventanaMenu = new InterfazMenu(activarBotones);
                ventanaCarrito.dispose();
            }
        };
        botonConfirmar.addActionListener(agregarProductoAlCarrito);
    }
    
}
