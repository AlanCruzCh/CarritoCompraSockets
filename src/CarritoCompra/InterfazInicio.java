package CarritoCompraFinal;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class InterfazInicio extends JFrame{
    
    public static  String DireccionIP;
    public static  String puerto;
    public static int PuertoServidor;
    public static ListaArticulos productosAMostrar, productosAComprar = new ListaArticulos();
    
    private static JLabel imagenFondo, bienvenida, direccion_ip, numeroPuerto; 
    private static JTextField textoIP, textoPuerto; 
    private static JButton botonIngresar, botonLimpiar;
    public static JFrame ventanaInicio;
    private static JPanel plantillaInicio;
    private static String ruta_descarga, ruta, carpeta;
    
    
    public InterfazInicio(){
        File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src");
        ruta = f.getAbsolutePath();
        carpeta = "Productos_en_cliente";
        ruta_descarga = ruta + "\\" + carpeta + "\\";
        System.out.println("ruta: " + ruta_descarga);
        File f2 = new File(ruta_descarga);
        f2.mkdirs();
        f2.setWritable(true);
        
        
        CrearVentana();
        CrearPanel();
        CrearEtiquetas();
        CrearInputs();
        CrearBotones();
        EventosBotones();
        ColocarEIP();
        ConfigurarCierreVentana();
    
    }
    
    private void CrearVentana(){
        ventanaInicio = new JFrame();
        ventanaInicio.setSize(560, 600);
        ventanaInicio.setLocationRelativeTo(null);
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ventanaInicio.setIconImage(mercado);
    }
    
    private void CrearPanel(){
        plantillaInicio = new JPanel();
        plantillaInicio.setBackground(Color.decode("#800018"));
        plantillaInicio.setLayout(null);
    }
    
    private void CrearEtiquetas(){
        ImageIcon fondo = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\icono.png");
        
        imagenFondo = new JLabel(new ImageIcon(fondo.getImage().getScaledInstance(500,
                515,Image.SCALE_SMOOTH)));
        imagenFondo.setBounds(25, 25, 500, 515);
        
        bienvenida = new JLabel("Bienvenido al sistema", SwingConstants.CENTER);
        bienvenida.setForeground(Color.WHITE);
        //bienvenida.setBackground(Color.BLACK);
        //bienvenida.setOpaque(true);
        bienvenida.setBounds(50, 50, 450, 50);
        bienvenida.setFont(new Font("Bell MT", Font.ITALIC, 50));
        
        direccion_ip = new JLabel("Ingrese la IP del servidor", SwingConstants.CENTER);
        direccion_ip.setBounds(100, 110, 350, 40);
        //direccionIP.setOpaque(true);
        direccion_ip.setForeground(Color.WHITE);
        //direccionIP.setBackground(Color.BLACK);
        direccion_ip.setFont(new Font("Bell MT", Font.PLAIN, 25));
        
        numeroPuerto = new JLabel("Ingrese el Puerto del servidor", 
                SwingConstants.CENTER);
        numeroPuerto.setBounds(100, 220, 350, 40);
        //direccionIP.setOpaque(true);
        numeroPuerto.setForeground(Color.WHITE);
        //direccionIP.setBackground(Color.BLACK);
        numeroPuerto.setFont(new Font("Bell MT", Font.PLAIN, 25));
    }
    
    private void CrearInputs(){
        textoIP = new JTextField("192.168.100.14");
        textoIP.setBounds(175, 150, 200, 40);
        textoIP.setHorizontalAlignment(JTextField.CENTER);
        
        textoPuerto = new JTextField("3070");
        textoPuerto.setBounds(175, 260, 200, 40);
        textoPuerto.setHorizontalAlignment(JTextField.CENTER);
    }
    
    private void CrearBotones(){
        botonIngresar = new JButton("Ingresar");
        botonIngresar.setBounds(290, 390, 100, 30);
        botonIngresar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBounds(160, 390, 100, 30);
        botonLimpiar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));    
    }
    
    private void EventosBotones(){
        ActionListener ingresarAlSistema;
        ingresarAlSistema = (ActionEvent e) -> {
            DireccionIP = textoIP.getText();
            puerto = textoPuerto.getText();
            if (DireccionIP.isEmpty() || puerto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rebice que todos los campos esten llenos",
                        "Campos vacios",JOptionPane.WARNING_MESSAGE);
            }else{
                PuertoServidor = Integer.parseInt(puerto);
                boolean descarga = MetodosSockets.mandarIndicacioServidor(DireccionIP,
                        PuertoServidor, 1);
                if (descarga == true) {
                    MetodosSockets.DescargarArchivo(DireccionIP, PuertoServidor,
                        ruta_descarga);
                    JOptionPane.showMessageDialog(null, "Conexion exitosa con el servidor",
                            "Conexion Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    productosAMostrar = MetodosSockets.DeserializarProductos("C:\\Users\\Alan\\"
                            + "Documents\\noveno_semestre\\Redes 2\\practica1"
                            + "\\CarritoCompra\\src\\Productos_en_cliente\\"
                            + "lista_productos_disponibles.txt");
                    
                    InterfazMenu ventanaMenu = new InterfazMenu(true);
                    ventanaInicio.dispose();
                }
                else{
                    InterfazMenu ventanaMenu = new InterfazMenu(false);
                    JOptionPane.showMessageDialog(null, "Conexion fallida con el servidor",
                            "Conexion fallida", JOptionPane.ERROR_MESSAGE);
                    ventanaInicio.dispose();
                    
                }
                
                
            
            }
            
        };
        
        botonIngresar.addActionListener(ingresarAlSistema);
        
    }
    
    private void ColocarEIP(){
        plantillaInicio.add(bienvenida);
        plantillaInicio.add(direccion_ip);
        plantillaInicio.add(numeroPuerto);
        plantillaInicio.add(textoIP);
        plantillaInicio.add(textoPuerto);
        plantillaInicio.add(botonIngresar);
        plantillaInicio.add(botonLimpiar);
        plantillaInicio.add(imagenFondo); 
        ventanaInicio.add(plantillaInicio);
    }
    
    private void ConfigurarCierreVentana(){
        ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInicio.setVisible(true);
    }
    
    public static void main(String[] args) {
     InterfazInicio VentanaInicio = new InterfazInicio();
        
        
        
        
    
    }
    
}
