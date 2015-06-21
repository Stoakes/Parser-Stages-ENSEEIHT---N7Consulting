/**
 * Classe pour passer d'un pdf à un fichier texte.
 * @author Antoine B.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/** utilisation de la bibliotheque itext : http://itextpdf.com/ **/
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class pdf2texte {

    
    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    private void parsePdf(String pdf, String txt) throws IOException {

    	PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        out.flush();
        out.close();
        reader.close();
    }

    /**
     * constructeur. Lance l'algorithme de parsin de pdf.
     * @param    args    no arguments needed
     * @throws IOException
     */
    public pdf2texte(String path) throws IOException {
       parsePdf(path, path+".txt");
    }
}