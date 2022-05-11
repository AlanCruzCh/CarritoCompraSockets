package CarritoCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class InterfazAgregarAlCarrito extends JFrame{
    
    CarritoCompra productosEnCarrito;
    Producto_del_Carrito productoSelecionado;
    ListaProducto productosDisponible;
    JFrame ventanaCarrito;
    JPanel plantillaCarrito;
    JLabel idProducto, imagenProducto, nombreProducto, precioProducto, colorProducto,
            apagarProducto, cantidadProducto;
    ImageIcon producto;
    int iteradorLista;
    JTextField cantidadProductoDeseado;
    JComboBox<String> colorProductoDeseado;
    JButton botonConfirmar, botonRegresar, botonCalcular;
    
    public InterfazAgregarAlCarrito(int iteradorLista, boolean ActivarBotones){
        this.productosEnCarrito = InterfazBienvenida.productos_carrito;
        this.productosDisponible = InterfazBienvenida.productos_disponibles;
        this.iteradorLista =  iteradorLista;
        crearVentana();
        agregarPanel();
        colocarEtiquetas();
        colocarEntradasDatos();
        colocarBotones(ActivarBotones);
   
    }
    
    /**
     * metodo para crear la ventana que saremos de esta interfaz
     */
    private void crearVentana(){
        ventanaCarrito = new JFrame("Ventana Menu");
        ventanaCarrito.setSize(930, 420);
        ventanaCarrito.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaCarrito.setIconImage(mercado);
        ventanaCarrito.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCarrito.setVisible(true);
    }
    
    /**
     * metodo que le agrega una JPanel a nuestra ventana para poder agregar los elementos que 
     * necesitamos sobre ella 
     */
    private void agregarPanel(){
        plantillaCarrito = new JPanel();
        plantillaCarrito.setBackground(Color.decode("#000080"));
        plantillaCarrito.setLayout(null);
        ventanaCarrito.add(plantillaCarrito);
    }

    private void colocarEtiquetas(){
        producto = new ImageIcon(productosDisponible.listaProductos.get(iteradorLista).
                ruta_Imagen_Producto);
        imagenProducto = new JLabel(new ImageIcon(producto.getImage().getScaledInstance
        (200,200, Image.SCALE_SMOOTH)));
        imagenProducto.setBounds(20, 20, 200, 260);
        
        idProducto = new JLabel("ID: " + productosDisponible.listaProductos.get
                (iteradorLista).id_Producto);
        idProducto.setForeground(Color.WHITE);
        idProducto.setHorizontalAlignment(JLabel.CENTER);
        idProducto.setBackground(Color.BLACK);
        idProducto.setOpaque(true);
        idProducto.setBounds(240, 40, 150, 40);
        idProducto.setFont(new Font("Bell MT", Font.ITALIC, 20));
        
        nombreProducto = new JLabel("Nombre: " + productosDisponible.listaProductos.get
                (iteradorLista).nombre_Producto);
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
        
        precioProducto = new JLabel("Precio: " + productosDisponible.listaProductos.get
                (iteradorLista).precio_Producto);
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
        
        plantillaCarrito.add(idProducto);
        plantillaCarrito.add(nombreProducto);
        plantillaCarrito.add(colorProducto);
        plantillaCarrito.add(precioProducto);
        plantillaCarrito.add(cantidadProducto);
        plantillaCarrito.add(apagarProducto);
        plantillaCarrito.add(imagenProducto);
    }
    
    private void colocarEntradasDatos(){
        cantidadProductoDeseado = new JTextField();
        cantidadProductoDeseado.setHorizontalAlignment(JTextField.CENTER);
        cantidadProductoDeseado.setBounds(570, 180, 130, 40);
        cantidadProductoDeseado.setFont(new Font("Bell MT", Font.ITALIC, 15));
        
        colorProductoDeseado = new JComboBox<>(productosDisponible.listaProductos.get
                (iteradorLista).colores_Producto);
        colorProductoDeseado.setBounds(420, 110, 280, 40);
        colorProductoDeseado.setFont(new Font("Bell MT", Font.PLAIN, 15));
        
        plantillaCarrito.add(cantidadProductoDeseado);
        plantillaCarrito.add(colorProductoDeseado);
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
        
        ActionListener calcularCosto;
        calcularCosto = (ActionEvent e) -> {
            String confirmarVacio = cantidadProductoDeseado.getText();
            
            if (confirmarVacio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ha indicado la cantidad del producto\n"
                        + "que desea", "No agrego una cantidad", 
                        JOptionPane.ERROR_MESSAGE);    
            }
            else{
                int confimarProducto = productosEnCarrito.buscar_indice_carrito
                        (productosDisponible.listaProductos.get(iteradorLista).id_Producto);
                if (confimarProducto == -1){
                    // Producto Nuevo
                    int cantidadDeseada = Integer.parseInt(cantidadProductoDeseado.getText());
                    if(cantidadDeseada <= productosDisponible.listaProductos.get
                        (iteradorLista).stock_Producto){
                        float cuenta = cantidadDeseada * productosDisponible.listaProductos.get
                        (iteradorLista).precio_Producto;
                        apagarProducto.setText("A pagar: " + cuenta);
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "La cantidad de productos que desea\n"
                            + "supera la cantidad del producto\n disponible", "Acaba de "
                            + "arrebasar el stock disponible", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    //el producto ya exixiste
                    int cantidadSuma = productosEnCarrito.carrito_de_Productos.get
                            (confimarProducto).cantidad_ProductoCarrito + (Integer.parseInt
                            (cantidadProductoDeseado.getText()));
                    if(cantidadSuma <= productosDisponible.listaProductos.get
                        (iteradorLista).stock_Producto){
                        int cantidadDeseada = Integer.parseInt(cantidadProductoDeseado.
                                getText());
                        float cuenta = cantidadDeseada * productosDisponible.listaProductos.get
                        (iteradorLista).precio_Producto;
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
        
        botonCalcular.addActionListener(calcularCosto);
        
        ActionListener agregarProductoAlCarrito;
        agregarProductoAlCarrito = (ActionEvent e) -> {
            String confirmarVacio = cantidadProductoDeseado.getText();
            if (confirmarVacio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ha indicado la cantidad del producto\n"
                        + "que desea", "No agrego una cantidad", 
                        JOptionPane.ERROR_MESSAGE);    
            }
            else{
                float cuentaApagar = (Integer.parseInt(confirmarVacio)) * productosDisponible.
                        listaProductos.get(iteradorLista).precio_Producto;
                        
                productoSelecionado = new Producto_del_Carrito(productosDisponible.
                        listaProductos.get(iteradorLista).id_Producto, productosDisponible.
                        listaProductos.get(iteradorLista).nombre_Producto, 
                        String.valueOf(colorProductoDeseado.getSelectedItem()), 
                        Integer.parseInt(confirmarVacio), productosDisponible.listaProductos.get
                        (iteradorLista).precio_Producto, cuentaApagar);
                productosEnCarrito.agregarProductoCarrito(productoSelecionado);
                
                JOptionPane.showMessageDialog(null, "Producto añadido al carrito de forma"
                        + " exitosa", "Operacion EXITOSA", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                InterfazMenu ventanaMenu = new InterfazMenu(activarBotones);
                ventanaCarrito.dispose();
            }
            
        };
        
        botonConfirmar.addActionListener(agregarProductoAlCarrito);
        
        
        plantillaCarrito.add(botonConfirmar);
        plantillaCarrito.add(botonCalcular);
        plantillaCarrito.add(botonRegresar);
        
    }
}
