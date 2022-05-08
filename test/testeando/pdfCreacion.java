package testeando;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.*;
import java.util.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pdfCreacion {

    public static void main(String[] args) {
        File fichero = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\"
                + "practica1\\CarritoCompra\\src\\Productos_en_servidor\\ticket.pdf");

        //Obtendremos la fecha y la hora en la que se creo el pdf
        LocalDateTime Fecha = LocalDateTime.now();
        System.out.println("" + Fecha);
        DateTimeFormatter formatenadoFecha = DateTimeFormatter.ofPattern("E, MMM dd yyyy");

        String fechaFormateada = Fecha.format(formatenadoFecha);
        DateFormat formatenadoHora = new SimpleDateFormat("hh.mm aa");
        String horaFormateada = formatenadoHora.format(new Date());

        try {
            PdfWriter pdfw = new PdfWriter(fichero);
            PdfDocument pdfd = new PdfDocument(pdfw);
            Document doc = new Document(pdfd, PageSize.A4);
            Paragraph parrafo = new Paragraph("Carrito de Alan S.A de C.V").setTextAlignment
                    (TextAlignment.CENTER);
            int numeroOrden = 1;
            Paragraph parrafo2 = new Paragraph("Orden numero " + numeroOrden).setTextAlignment
                    (TextAlignment.CENTER);
            Paragraph parrafoFecha = new Paragraph(fechaFormateada + "\t\t" + horaFormateada).
                    setTextAlignment(TextAlignment.CENTER);
            doc.add(parrafo);
            doc.add(parrafo2);
            doc.add(parrafoFecha);
            float[] pointColumnWidths = {50f, 200f, 120f, 100f, 100f, 100f};
            Table tabla = new Table(pointColumnWidths);
            tabla.setTextAlignment(TextAlignment.CENTER);
            tabla.setMarginTop(20f);
            String ID = "Id";
            String Producto = "Producto";
            String Color = "Color";
            String Cantidad = "Cantidad";
            String Precio = "Precio";
            String Pago = "Pago";
            StringTokenizer stkID = new StringTokenizer(ID);
            StringTokenizer stkProoducto = new StringTokenizer(Producto);
            StringTokenizer stkColor = new StringTokenizer(Color);
            StringTokenizer stkCantidad = new StringTokenizer(Cantidad);
            StringTokenizer stkPrecio = new StringTokenizer(Precio);
            StringTokenizer stkPago = new StringTokenizer(Pago);
            tabla.addCell(new Cell().add(new Paragraph(stkID.nextToken())));
            tabla.addCell(new Cell().add(new Paragraph(stkProoducto.
                    nextToken())));
            tabla.addCell(new Cell().add(new Paragraph(stkColor.nextToken())));
            tabla.addCell(new Cell().add(new Paragraph(stkCantidad.
                    nextToken())));
            tabla.addCell(new Cell().add(new Paragraph(stkPrecio.nextToken())));
            tabla.addCell(new Cell().add(new Paragraph(stkPago.nextToken())));

            doc.add(tabla);
            doc.close();
            pdfd.close();
            pdfw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfCreacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfCreacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
