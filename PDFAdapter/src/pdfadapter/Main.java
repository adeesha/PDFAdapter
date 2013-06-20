/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, TransformerConfigurationException, SAXException {
        // TODO code application logic here
                                File f=new File("S1.pdf");
           PDFAdapter adapter=new PDFAdapter(f);
           adapter.preProcess();
    }

}
