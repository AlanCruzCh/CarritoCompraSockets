package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodosSocket {
    
    public static Socket Cliente;
    public static ServerSocket Servidor;
    
    public static void llamarIndicacioServidor(String DireccionIP, int PuertoServidor){
        int bandera = 1;
        
        try {
            Cliente = new Socket(DireccionIP, PuertoServidor);
            PrintWriter solicitud = new PrintWriter(new OutputStreamWriter(
                Cliente.getOutputStream()));
            solicitud.println(bandera);
            solicitud.flush();
            solicitud.close();
            Cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(MetodosSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
