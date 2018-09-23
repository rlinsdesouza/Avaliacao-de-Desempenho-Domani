package uteis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import sandbox.WrapToTest;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Prato;
 
/**
 * @author Bruno Lowagie (iText Software)
 * Adpatado por Rafael
 */
//@WrapToTest
public class CreateEtiquetasPDF {
    public static final String DEST = "results/tables/small_table.pdf";
 
    public void createPdf(String dest, List<Prato> pratos) throws IOException, DocumentException {
    	File file = new File(DEST);
        file.getParentFile().mkdirs();
        
//        Rectangle small = new Rectangle(290,100);
        Font smallfont = new Font(FontFamily.HELVETICA, 10);

        Document document = new Document(PageSize.A4,10,10,10,10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table2 = new PdfPTable(3);
        
        table2.setTotalWidth(new float[]{180,180,180});
        table2.setLockedWidth(true);
        PdfContentByte cb = writer.getDirectContent();
        
        for (Prato prato : pratos) {
            PdfPTable table = new PdfPTable(1);
                   	
        	// first row
            PdfPCell cell = new PdfPCell(new Phrase(prato.getNome()));
//            cell.setFixedHeight(30);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setColspan(2);
            table.addCell(cell);
            // second row
            if (prato.getInsumos()!=null) {
            	cell = new PdfPCell(new Phrase(prato.getInsumos().toString(), smallfont));
            }else {
            	cell = new PdfPCell(new Phrase("Sem insumos cadastrados", smallfont));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            // third row
            cell = new PdfPCell(new Phrase("Glutem: "+ prato.isGlutem() +" / Lactose: "+prato.isLactose(), smallfont));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            PdfPCell celltable2 = new PdfPCell(table);
            celltable2.setBorderWidth(3);
            celltable2.setPadding(1);
            
            table2.addCell(celltable2);
		}
        	document.add(table2);
        
           document.close();
    }
}