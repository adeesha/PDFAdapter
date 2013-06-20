/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class TitleCleaner {
    public static final String PRECEDIN_NON_CHARACTERS="[^\\w]+";

    public TitleCleaner(){
        
    }
    public String clean(String str){
        String cleaned=str;
        Pattern p=Pattern.compile("^(\\d+|\\W+)+");
        Pattern p1=Pattern.compile("(\\d+|\\W+)+$");
        Pattern p2=Pattern.compile("\\s[\\d\\W]+\\s");
        Matcher m=p.matcher(cleaned);
        cleaned=m.replaceAll("");
        Matcher m1=p1.matcher(cleaned);
        cleaned=m1.replaceAll("");
        Matcher m2=p2.matcher(cleaned);
        cleaned=m2.replaceAll(" ");
        return cleaned;
    }
}
