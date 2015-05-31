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

    /** The original PDF that will be parsed. */
    public static final String PREFACE = "src/readme.pdf";
    /** The resulting text file. */
    public static final String RESULT = "src/export.txt";
    
    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdf(String pdf, String txt) throws IOException {
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
     * Main method.
     * @param    args    no arguments needed
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new pdf2texte().parsePdf(PREFACE, RESULT);
    }
}