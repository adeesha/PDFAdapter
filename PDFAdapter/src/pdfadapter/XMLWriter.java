/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author user
 */
public class XMLWriter {
    public XMLWriter(){

    }

    public void writeXML(Document doc) throws FileNotFoundException, TransformerConfigurationException, SAXException{
        PrintWriter out=new PrintWriter(new File("test.xml"));
        StreamResult streamResult = new StreamResult(out);
        SAXTransformerFactory tf= (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler hd=tf.newTransformerHandler();
        Transformer serializer = hd.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
        serializer.setOutputProperty(OutputKeys.INDENT,"yes");
        hd.setResult(streamResult);
        hd.startDocument();
        AttributesImpl atts = new AttributesImpl();
        hd.startElement(null, null, "DocumentT", atts);
        ArrayList<Title> titles=doc.getTitles();
        for(Title t : titles){
            atts.clear();
            atts.addAttribute(null, null, "Scale", "CDATA", ""+t.getScale());
            hd.startElement(null, null, "Title", atts);
            char[] c=t.getTitleText().toCharArray();
            hd.characters(c, 0, c.length);
            hd.endElement(null, null, "Title");
        }
        hd.startElement(null, null, "Body", null);
        char[] body=doc.getBody().toCharArray();
        hd.characters(body, 0, body.length);
        hd.endElement(null, null, "Body");
        hd.endElement(null, null, "Document");
        out.close();
    }
}
