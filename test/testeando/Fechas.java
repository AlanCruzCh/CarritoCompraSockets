package testeando;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Fechas {
    public static void main(String[] args) {
        LocalDateTime Fecha = LocalDateTime.now();
        System.out.println("" + Fecha);
        DateTimeFormatter formatenadoFecha = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        
        String fechaFormateada = Fecha.format(formatenadoFecha);
        DateFormat formatenadoHora = new SimpleDateFormat("hh.mm aa");
        String horaFormateada = formatenadoHora.format(new Date());
        System.out.println("" + fechaFormateada + "\n" + horaFormateada);
        
        
    }
}
