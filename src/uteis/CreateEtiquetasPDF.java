package uteis;

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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
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


 
    public void createPdf(String dest, List<Prato> pratos) throws IOException, DocumentException {
//    	File file = new File(dest);
//      file.getParentFile().mkdirs();
 
        Document document = new Document(PageSize.A4,10,10,10,10);
        
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        Font smallfont = new Font(FontFamily.HELVETICA, 12);
        Font pratofont = new Font(FontFamily.HELVETICA, 20);
        Font glutemfont = new Font(FontFamily.HELVETICA, 12);
        
        
        document.open();
        
        
        int qntcolunas = 2;
        PdfPTable table2 = new PdfPTable(qntcolunas);
        table2.setTotalWidth(new float[]{260,260});
        table2.setLockedWidth(true);

        
        for (Prato prato : pratos) {
            PdfPTable table = new PdfPTable(1);
                   	
        	// first row
            PdfPCell cell = new PdfPCell(new Phrase(prato.getNome(),pratofont));
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
            cell = new PdfPCell(new Phrase("Possui Glúten? "+(prato.isGlutem() == true ? "SIM" : "NÃO")+" / Possui Lactose? "+(prato.isLactose() == true ? "SIM" : "NÃO"), glutemfont));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            PdfPCell celltable2 = new PdfPCell(table);
            celltable2.setBorderWidth(3);
            celltable2.setPadding(1);
            
            table2.addCell(celltable2);
		}
        
        if (pratos.size()%qntcolunas != 0) {
        	for (int i = 0; i < qntcolunas-(pratos.size()%qntcolunas); i++) {
        		PdfPCell cellcomplete = new PdfPCell(new Phrase(""));
        		table2.addCell(cellcomplete);
			}
        }
        
        document.add(table2);
        document.add(new Paragraph("Fim do relatÃ³rio"));
        document.close();
    }
}