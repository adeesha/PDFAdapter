/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

/**
 *
 * @author user
 */
public class PDFBlock {
    private float fontSize;
    private int charCount;
    private String body;

    public PDFBlock(float fontSize, int charCount, String body){
        this.fontSize=fontSize;
        this.charCount=charCount;
        this.body=body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCharCount() {
        return charCount;
    }

    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void increaseFreequency(int amount){
        this.charCount+=amount;
    }
    
}
