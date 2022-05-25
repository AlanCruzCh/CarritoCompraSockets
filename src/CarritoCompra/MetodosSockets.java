package CarritoCompraFinal;

import java.net.*;
import java.io.*;
import java.util.logging.*;

public class MetodosSockets {

    private static Socket Cliente;
    private static ServerSocket Servidor;
    private static int mensaje_respuesta;

    public static void levantarServidor(int PuertoLevantarServidor) {
        try {
            Servidor = new ServerSocket(PuertoLevantarServidor);
            Servidor.setReuseAddress(true);
            System.out.println("\nEsperando conexion con el cliente...");

        } catch (IOException ex) {
            Logger.getLogger(MetodosSockets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static boolean mandarIndicacioServidor(String DireccionIP, int PuertoServidor, 
            int posicion) {
        int bandera = posicion;
        boolean DescargaCompleta = true;
        try {
            Cliente = new Socket(DireccionIP, PuertoServidor);
            PrintWriter solicitud = new PrintWriter(new OutputStreamWriter(
                    Cliente.getOutputStream()));
            solicitud.println(bandera);
            solicitud.flush();
            solicitud.close();
            Cliente.close();
        } catch (IOException ex) {
            DescargaCompleta = false;
            ex.getMessage();
        }
        return DescargaCompleta;
    }

    public static int recibirIndicacionesCliente() {
        try {
            Cliente = Servidor.accept();
            System.out.println("El cliente se conecto desde: " + Cliente.getInetAddress()
                    + " desde el puerto: " + Cliente.getPort());
            BufferedReader br_accion = new BufferedReader(new InputStreamReader(Cliente.
                    getInputStream()));
            mensaje_respuesta = Integer.parseInt(br_accion.readLine());
            br_accion.close();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return mensaje_respuesta;
    }

    public static void SerializarListaProductos(String RutaArchivoSerializar,
            ListaArticulos listaProductos) {
        try {
            FileOutputStream fos = new FileOutputStream(RutaArchivoSerializar);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaProductos);
            oos.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServidorCarritoCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public static ListaArticulos DeserializarProductos(String RutaArchivoDeserializar){
        ListaArticulos listaProductosDeserializado = null;
        try {
            FileInputStream fis = new FileInputStream(RutaArchivoDeserializar);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaProductosDeserializado = (ListaArticulos) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(MetodosSockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listaProductosDeserializado;
    }

    public static void MandarArchivos(String RutaArchivoSerializado) {
        try {
            Cliente = Servidor.accept();
            File archivo_al_cliente = new File(RutaArchivoSerializado);
            String ruta_archivo = archivo_al_cliente.getAbsolutePath();
            String nombre_archivo = archivo_al_cliente.getName();
            long tamaño_archivo = archivo_al_cliente.length();

            /**
             * Definimos dos flujos orientados a bytes, uno para leer el archivo
             * y otro para mandarlo por el socket
             */
            DataOutputStream dos_envio = new DataOutputStream(Cliente.
                    getOutputStream());
            DataInputStream dis_envio = new DataInputStream(new FileInputStream(ruta_archivo));

            /**
             * Enviamos los datos generales del archivo por el socket
             */
            dos_envio.writeUTF(nombre_archivo);
            dos_envio.flush();
            dos_envio.writeLong(tamaño_archivo);
            dos_envio.flush();

            /**
             * Leemos los datos contenidos en el archivo en paquetes de 1024
             * bytes y lo enviamos por el socket
             */
            byte[] b_envio = new byte[1024];
            long enviados = 0;
            int n_envio;
            while (enviados < tamaño_archivo) {
                n_envio = dis_envio.read(b_envio);
                dos_envio.write(b_envio, 0, n_envio);
                dos_envio.flush();
                enviados = enviados + n_envio;
            }// cerramos el while

            /**
             * Cerramos los flujos, el socket, terminamos bloques y cerramos
             * flujos
             */
            System.out.print("\nArchivo enviado\n");
            dos_envio.close();
            dis_envio.close();

        } catch (IOException ex) {
            Logger.getLogger(MetodosSockets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void RecibirArchivos(String RutaArchivoDescarga){
        try {
            Cliente = Servidor.accept();
            DataInputStream dis = new DataInputStream(Cliente.getInputStream());

            /**
             * Leemos los datos principales del archivo y creamos un flujo para
             * escribir el archivo de salida
             */
            byte[] b = new byte[1024];
            String nombre = dis.readUTF();
            long tam = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                    RutaArchivoDescarga + nombre));

            /**
             * Preparamos los datos para recibir los paquetes de datos del
             * archivo
             */
            long recibidos = 0;
            int n;

            /**
             * Definimos el ciclo donde estaremos recibiendo los datos mandados
             * por el cliente
             */
            while (recibidos < tam) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                recibidos = recibidos + n;
            }// while

            dos.close();
            dis.close();
            System.out.println("Archivo Recibido");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void DescargarArchivo(String DireccionIP, int PuertoServidor, 
            String RutaArchivo) {
        try {
            Cliente = new Socket(DireccionIP, PuertoServidor);
            DataInputStream dis = new DataInputStream(Cliente.getInputStream());

            /**
             * Leemos los datos principales del archivo y creamos un flujo para
             * escribir el archivo de salida
             */
            byte[] b = new byte[1024];
            String nombre = dis.readUTF();
            long tam = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                    RutaArchivo + nombre));

            /**
             * Preparamos los datos para recibir los paquetes de datos del
             * archivo
             */
            long recibidos = 0;
            int n;

            /**
             * Definimos el ciclo donde estaremos recibiendo los datos mandados
             * por el cliente
             */
            while (recibidos < tam) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                recibidos = recibidos + n;
            }// while

            dos.close();
            dis.close();
            Cliente.close();
            System.out.println("Archivo Descargado");
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public static void SubirArchivo(String DireccionIP, int PuertoServidor, String RutaArchivo){
        try {
            Cliente = new Socket(DireccionIP, PuertoServidor);
            File archivo_al_cliente = new File(RutaArchivo);
            String ruta_archivo = archivo_al_cliente.getAbsolutePath();
            String nombre_archivo = archivo_al_cliente.getName();
            long tamaño_archivo = archivo_al_cliente.length();

            /**
             * Definimos dos flujos orientados a bytes, uno para leer el archivo
             * y otro para mandarlo por el socket
             */
            DataOutputStream dos_envio = new DataOutputStream(Cliente.
                    getOutputStream());
            DataInputStream dis_envio = new DataInputStream(new FileInputStream(ruta_archivo));

            /**
             * Enviamos los datos generales del archivo por el socket
             */
            dos_envio.writeUTF(nombre_archivo);
            dos_envio.flush();
            dos_envio.writeLong(tamaño_archivo);
            dos_envio.flush();

            /**
             * Leemos los datos contenidos en el archivo en paquetes de 1024
             * bytes y lo enviamos por el socket
             */
            byte[] b_envio = new byte[1024];
            long enviados = 0;
            int n_envio;
            while (enviados < tamaño_archivo) {
                n_envio = dis_envio.read(b_envio);
                dos_envio.write(b_envio, 0, n_envio);
                dos_envio.flush();
                enviados = enviados + n_envio;
            }// cerramos el while

            /**
             * Cerramos los flujos, el socket, terminamos bloques y cerramos
             * flujos
             */
            System.out.print("Archivo subido");
            dos_envio.close();
            dis_envio.close();
            Cliente.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
