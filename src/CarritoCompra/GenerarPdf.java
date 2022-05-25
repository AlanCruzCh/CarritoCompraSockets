package CarritoCompraFinal;

import com.itextpdf.kernel.geom.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerarPdf {
    
    private static String fechaFormateada, horaFormateada;
    private static ListaArticulos productosTicket;
    public static final String DIRECCION_PDF = "C:\\Users\\Alan\\Documents\\noveno_semestre"
            + "\\Redes 2\\practica1\\CarritoCompra\\src\\Productos_en_servidor\\ticket.pdf";

    public GenerarPdf(int numeroOrden) {

        GenerarPdf.productosTicket = ServidorCarritoCompra.listaArticulosEnCarrito;
        
        try {
            PdfWriter pdfw = new PdfWriter(DIRECCION_PDF);
            PdfDocument pdfd = new PdfDocument(pdfw);
            Document doc = new Document(pdfd, PageSize.A5);
            Paragraph parrafo = new Paragraph("Carrito de Alan S.A de C.V").
                    setTextAlignment(TextAlignment.CENTER);
            Paragraph parrafo2 = new Paragraph("Orden numero " + numeroOrden).
                    setTextAlignment(TextAlignment.CENTER);
            Paragraph parrafoFecha = new Paragraph(fechaFormateada + "\t\t"
                    + horaFormateada).setTextAlignment(TextAlignment.CENTER);

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
            float pagoTotal = 0;
            StringTokenizer stkID = new StringTokenizer(ID);
            StringTokenizer stkProoducto = new StringTokenizer(Producto);
            StringTokenizer stkColor = new StringTokenizer(Color);
            StringTokenizer stkCantidad = new StringTokenizer(Cantidad);
            StringTokenizer stkPrecio = new StringTokenizer(Precio);
            StringTokenizer stkPago = new StringTokenizer(Pago);

            tabla.addCell(
                    new Cell().add(new Paragraph(stkID.nextToken())));
            tabla.addCell(
                    new Cell().add(new Paragraph(stkProoducto.
                            nextToken())));
            tabla.addCell(
                    new Cell().add(new Paragraph(stkColor.nextToken())));
            tabla.addCell(
                    new Cell().add(new Paragraph(stkCantidad.
                            nextToken())));
            tabla.addCell(
                    new Cell().add(new Paragraph(stkPrecio.nextToken())));
            tabla.addCell(
                    new Cell().add(new Paragraph(stkPago.nextToken())));
            for (int i = 0; i < productosTicket.ListaDeProductos.size(); i++) {
                ID = Integer.toString(productosTicket.ListaDeProductos.
                        get(i).id_Producto);
                Producto = productosTicket.ListaDeProductos.get(i).nombre_Producto;
                Color = productosTicket.ListaDeProductos.get(i).color_Producto;
                Cantidad = Integer.toString(productosTicket.ListaDeProductos.get(i).
                        stock_Producto);
                Precio = String.valueOf(productosTicket.ListaDeProductos.get(i).precio_Producto);
                Pago = String.valueOf(productosTicket.ListaDeProductos.get(i).
                        stock_Producto * productosTicket.ListaDeProductos.get(i).precio_Producto);
                stkID = new StringTokenizer(ID);
                stkProoducto = new StringTokenizer(Producto);
                stkColor = new StringTokenizer(Color);
                stkCantidad = new StringTokenizer(Cantidad);
                stkPrecio = new StringTokenizer(Precio);
                stkPago = new StringTokenizer(Pago);
                tabla.addCell(new Cell().add(new Paragraph(stkID.nextToken())));
                tabla.addCell(new Cell().add(new Paragraph(stkProoducto.
                        nextToken())));
                tabla.addCell(new Cell().add(new Paragraph(stkColor.
                        nextToken())));
                tabla.addCell(new Cell().add(new Paragraph(stkCantidad.
                        nextToken())));
                tabla.addCell(new Cell().add(new Paragraph(stkPrecio.
                        nextToken())));
                tabla.addCell(new Cell().add(new Paragraph(stkPago.
                        nextToken())));
            }
            String tituloPago = "Total a pagar";
            StringTokenizer stktp = new StringTokenizer(tituloPago);

            tabla.addCell(
                    new Cell(1, 5).add(new Paragraph(stktp.nextToken())));
            String aPagarTotal = String.valueOf(pagoTotal);
            StringTokenizer stkPagoTotal = new StringTokenizer(aPagarTotal);

            tabla.addCell(
                    new Cell().add(new Paragraph(stkPagoTotal.
                            nextToken())));

            doc.add(tabla);
            doc.close();
            pdfd.close();
            pdfw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.getStackTrace();
        }

    }

}
