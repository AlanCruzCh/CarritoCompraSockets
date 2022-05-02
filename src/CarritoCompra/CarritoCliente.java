package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoCliente extends Clases_del_Cliente {

    public static void main(String[] args) {

        try {

            // Declaramos la ruta donde se gurradara el archivo que vamos a descargar del cliente
            File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src");
            String ruta = f.getAbsolutePath();
            String carpeta = "Productos_en_cliente";
            String ruta_descarga = ruta + "\\" + carpeta + "\\";
            System.out.println("ruta: " + ruta_descarga);
            File f2 = new  File(ruta_descarga);
            f2.mkdirs();
            f2.setWritable(true);

            // Declaramos el buffer de entrada ppara guardar los datos del servidor al que nos queremos conectar
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nEscriba la direccion del servidor: ");
            String host = br.readLine();
            System.out.println("\nEscriba el puerto del servidor: ");
            int puerto = Integer.parseInt(br.readLine());

            // Inicializamos el cliente y la conexion con el servidor
            Socket cliente = new Socket(host, puerto);
            System.out.println("\nConexion establecida con el servidor");

            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            String nombre_archivo = dis.readUTF();
            long tam_archivo = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream( ruta_descarga + nombre_archivo));
            long recibidos = 0;
            int n = 0;
            byte[] b = new byte[1024];
            while (recibidos < tam_archivo) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                recibidos = recibidos + n;
            }
            if (recibidos == tam_archivo) {
                System.out.println("Descarga completa al 100%");
            }
            dos.close();
            dis.close();
            
            // Deserializamos la lista de productos que nos mando el servidor  
            FileInputStream fis = new FileInputStream("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\"
                    + "CarritoCompra\\src\\Productos_en_cliente\\lista_productos.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ListaProducto lista_productos_disponibles = (ListaProducto) ois.readObject();
            ois.close();
            fis.close();
            
            // Hacemos el menu con el cual el usuario interactuara
            
                
            
            cliente.close();
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarritoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
