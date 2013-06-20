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
public class PDFAdapter {
    private File[] files;
    public PDFAdapter(File file){
        files=new File[1];
        files[0]=file;
    }
    public PDFAdapter(File[] files){
        this.files=files;
    }
    public void preProcess() throws IOException, FileNotFoundException, TransformerConfigurationException, SAXException{
        for(int i=0;i<files.length;i++){
            PDDocument document=PDDocument.load(files[i]);
            
            TextExtractor stripper = new TextExtractor();
            stripper.setStartPage( 1 );
            String s=stripper.getText(document);
            stripper.generateBlocks();
            Document doc=stripper.getPDFDocument();
            XMLWriter writer=new XMLWriter();
            writer.writeXML(doc);
            document.close();
        }
    }
}
