/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

/**
 *
 * @author user
 */
import java.util.ArrayList;

public class Document {
        String body;
        ArrayList<Title>  titles;


        public Document(String body, ArrayList<Title> titles) {
                // TODO Auto-generated constructor stub
                this.body = body;
                this.titles = titles;
        }


        public String getBody() {
                return body;
        }
        public void setBody(String body) {
                this.body = body;
        }
        public ArrayList<Title> getTitles() {
                return titles;
        }
        public void setTitles(ArrayList<Title> titles) {
                this.titles = titles;
        }


}

