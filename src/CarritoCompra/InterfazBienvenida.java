package CarritoCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class InterfazBienvenida extends JFrame{
    
    private static  String direccion_IP;
    private static  String puerto;
    private static int PuertoServidor;
    private static ListaProducto productos_disponibles;
    
    public static void main(String[] args) {
        
        /**
         * Declaramos la ruta donde se gurradara el archivo que vamos a
         * descargar del cliente
         */
        File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src");
        String ruta = f.getAbsolutePath();
        String carpeta = "Productos_en_cliente";
        String ruta_descarga = ruta + "\\" + carpeta + "\\";
        System.out.println("ruta: " + ruta_descarga);
        File f2 = new File(ruta_descarga);
        f2.mkdirs();
        f2.setWritable(true);

        
        /**
         * Creamos la interfaz de usuario que vera el cliente al entrar a nuestro sisitema
         * con Jframe creamos la ventana que usaremos en la interfaz
         */
        JFrame ventana = new JFrame("Carrito compra Alan");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(570, 600);
        ventana.setLocationRelativeTo(null);
        /**
         * Cambiamos el icono de nuestra interfaz
         */
        Image mercado = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan\\Documents"
                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                + "imagenesInterfaces\\icono.png");
        ImageIcon fondo = new ImageIcon("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2"
                + "\\practica1\\CarritoCompra\\src\\imagenesInterfaces\\icono.png");
        
        /**
         * Jpanel es la plantilla sobre la que trabajarmos esta nos permitira agregar componentes
         * a nuestra ventana 
         */
        JPanel plantilla = new JPanel();
        plantilla.setBackground(Color.DARK_GRAY);
        plantilla.setLayout(null);
        
        /**
         * Componentes que pondremos sobre el JPanel plantilla
         */
        JLabel imagenFondo = new JLabel(new ImageIcon(fondo.getImage().getScaledInstance(500,
                515,Image.SCALE_SMOOTH)));
        imagenFondo.setBounds(25, 25, 500, 515);
        
        JLabel bienvenida = new JLabel("Bienvenido al sistema", SwingConstants.CENTER);
        bienvenida.setForeground(Color.WHITE);
        //bienvenida.setBackground(Color.BLACK);
        //bienvenida.setOpaque(true);
        bienvenida.setBounds(50, 50, 450, 50);
        bienvenida.setFont(new Font("Bell MT", Font.ITALIC, 50));
        
        JLabel direccionIP = new JLabel("Ingrese la IP del servidor", SwingConstants.CENTER);
        direccionIP.setBounds(100, 110, 350, 40);
        //direccionIP.setOpaque(true);
        direccionIP.setForeground(Color.WHITE);
        //direccionIP.setBackground(Color.BLACK);
        direccionIP.setFont(new Font("Bell MT", Font.PLAIN, 25));
        
        JTextField textoIP = new JTextField();
        textoIP.setBounds(175, 150, 200, 40);
        textoIP.setHorizontalAlignment(JTextField.CENTER);
        
        JLabel numeroPuerto = new JLabel("Ingrese el Puerto del servidor", 
                SwingConstants.CENTER);
        numeroPuerto.setBounds(100, 220, 350, 40);
        //direccionIP.setOpaque(true);
        numeroPuerto.setForeground(Color.WHITE);
        //direccionIP.setBackground(Color.BLACK);
        numeroPuerto.setFont(new Font("Bell MT", Font.PLAIN, 25));
        
        JTextField textoPuerto = new JTextField();
        textoPuerto.setBounds(175, 260, 200, 40);
        textoPuerto.setHorizontalAlignment(JTextField.CENTER);
        
        JButton botonIngresar = new JButton("Ingresar");
        botonIngresar.setBounds(290, 390, 100, 30);
        botonIngresar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBounds(160, 390, 100, 30);
        botonLimpiar.setFont(new Font("Bell MT", Font.ROMAN_BASELINE, 15));
        
        
        /**
         * Agregamos eventos a los botones 
         */
        ActionListener ingresarAlSistema;
        ingresarAlSistema = (ActionEvent e) -> {
            direccion_IP = textoIP.getText();
            puerto = textoPuerto.getText();
            if (direccion_IP.isEmpty() || puerto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rebice que todos los campos esten llenos",
                        "Campos vacios",JOptionPane.WARNING_MESSAGE);
            }
            else{
                PuertoServidor = Integer.parseInt(puerto);
                //try {
                    /**
                     * Mandamos una peticion al servidor para que este nos mande la lista de 
                     * productos disponibles que le mandaremos a la interfaz del menu
                     */
                /*    Socket cliente = new Socket(direccion_IP, PuertoServidor);
                    int bandera = 1;
                    PrintWriter solicitud = new PrintWriter(new OutputStreamWriter(
                            cliente.getOutputStream()));
                    solicitud.println(bandera);
                    solicitud.flush();
                    solicitud.close();
                    cliente.close();
                */    
                    /**
                     * Descargamos el archivo que trae la lista de productos que mandaremos a
                     * la interfaz menu
                     */
                /*    cliente = new Socket(direccion_IP, PuertoServidor);
                    DataInputStream dis = new DataInputStream(cliente.getInputStream());
                */
                    /**
                     * Leemos los datos principales del archivo y creamos un flujo para
                     * escribir el archivo de salida
                     */
                /*    byte[] b = new byte[1024];
                    String nombre = dis.readUTF();
                    long tam = dis.readLong();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                            ruta_descarga + nombre));
*/
                    /**
                     * Preparamos los datos para recibir los paquetes de datos del
                     * archivo
                     */
  //                  long recibidos = 0;
    //                int n;

                    /**
                     * Definimos el ciclo donde estaremos recibiendo los datos mandados
                     * por el cliente
                     */
      /*              while (recibidos < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        dos.flush();
                        recibidos = recibidos + n;
                    }// while

                    FileInputStream fis = new FileInputStream("C:\\Users\\Alan\\Documents\\"
                            + "noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                            + "Productos_en_cliente\\lista_productos_disponibles.txt");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    productos_disponibles = (ListaProducto) ois.readObject();
                    ois.close();
                    fis.close();
                    dos.close();
                    dis.close();
                    cliente.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(InterfazBienvenida.class.getName()).log(Level.SEVERE, null,
                            ex);
                    JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor",
                            "Fallo de conexion", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InterfazBienvenida.class.getName()).log(Level.SEVERE, null,
                            ex);
                }
*/              InterfazMenu ventanaMenu = new InterfazMenu(direccion_IP, PuertoServidor,
                        productos_disponibles);
                ventana.dispose();
            }
        };
        
        botonIngresar.addActionListener(ingresarAlSistema);
        
        /**
         * Agregamos los componentes que hayamos creado al Jpanel 
         * Agregamos el JPanel al JFrame y desplegamos la interfaz con el metodo
         * setVisible(true);
         */
        plantilla.add(bienvenida);
        plantilla.add(direccionIP);
        plantilla.add(textoIP);
        plantilla.add(numeroPuerto);
        plantilla.add(textoPuerto);
        plantilla.add(botonIngresar);
        plantilla.add(botonLimpiar);
        plantilla.add(imagenFondo);
        ventana.setIconImage(mercado);
        ventana.add(plantilla);
        ventana.setVisible(true);
    }
}
