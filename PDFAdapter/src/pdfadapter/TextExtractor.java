/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

/**
 *
 * @author user
 */
public class TextExtractor extends PDFTextStripper {
    private ArrayList<TextPosition> chars;
    private ArrayList<PDFBlock> blocks;
    private float previous;
    private HashMap<Float,Integer> freqMap;
    private float bodyFontSize;
    private float minFontSize;
    private float minWidth;
    private float maxFontSize;
    private TextPosition tempPrev;
    public TextExtractor() throws IOException{
        super();
        chars=new ArrayList<TextPosition>();
        blocks=new ArrayList<PDFBlock>();
        freqMap=new HashMap();
    }
    public TextExtractor(String encode) throws IOException{
        super(encode);
        chars=new ArrayList<TextPosition>();
        blocks=new ArrayList<PDFBlock>();
        freqMap=new HashMap();
    }

    @Override
    protected void processTextPosition(TextPosition text){
        //super.processTextPosition(text);
        if(tempPrev!=null){
            if(tempPrev.getY()==text.getY()){
                if(minWidth==0){
                    minWidth=text.getX()-tempPrev.getX();
                }
                if(text.getX()-tempPrev.getX()<minWidth){
                    minWidth=text.getX()-tempPrev.getX();
                }
            }
        }
        chars.add(text);
    }

    public void generateBlocks(){
        String temp="";
        int count=0;
        TextPosition prev=null;
        for(TextPosition text : chars){
            if(isValid(text)){
                if(previous==text.getXScale()){
                    if(prev!=null){
                        if(text.getX()-prev.getX()>prev.getWidth()){
                         temp+=" ";
                        }
                    }
                    temp+=text.getCharacter();
                    count++;
                }else{
                    temp+=" ";
                    PDFBlock block=new PDFBlock(previous, count, temp);
                    this.blocks.add(block);
                    temp="";
                    this.updateFreqMap(previous, count);
                    temp=text.getCharacter();
                    previous=text.getXScale();
                    count=1;
                }
            }else{
                if(!temp.equals("")){
                    temp+=" ";
                    PDFBlock block=new PDFBlock(previous, count, temp);
                    this.blocks.add(block);
                    temp="";
                    this.updateFreqMap(previous, count);
                    count=0;
                }
            }
            prev=text;
        }
        updateFontInfo();
        chars.clear();
    }

    private boolean isValid(TextPosition text){
        String str=text.getCharacter();
        char[] characters=str.toCharArray();
        for(int i=0;i<characters.length;i++){
            Character c=characters[i];
            c.getNumericValue(c);
            if((c>127)){                
                return false;
            }
        }
        return true;
    }

    private void updateFreqMap(float size,int increment){
        if(this.freqMap.containsKey(size)){
            int val=this.freqMap.get(size);
            this.freqMap.put(size, val+increment);
        }else{
            this.freqMap.put(size, increment);
        }
    }

    private void updateFontInfo(){
        Set<Float> sizes=freqMap.keySet();
        Iterator itr=sizes.iterator();
        int maxFreq=0;
        int count=0;
        while(itr.hasNext()){
            Float size=(Float) itr.next();
            if(maxFontSize<size){
                maxFontSize=size;
            }
            if(minFontSize>size){
                minFontSize=size;
            }
            count=this.freqMap.get(size);
            if(count>maxFreq){
                maxFreq=count;
                bodyFontSize=size;
            }
        }
    }

    public Document getPDFDocument(){
        //System.out.println(bodyFontSize);
        TitleCleaner cleaner=new TitleCleaner();
        String body="";
        String temp="";
        ArrayList<Title> title=new ArrayList<Title>();
        for(PDFBlock block: blocks){
            if(bodyFontSize<block.getFontSize()){
                String ttl=block.getBody();
                ttl=cleaner.clean(ttl);
                if(ttl.length()!=0)
                title.add(new Title(ttl));
            }else if(bodyFontSize==block.getFontSize()){
                body=body+" "+block.getBody()+"\n";
            }else{
                
            }
        }
        Document doc=new Document(body, title);
        System.out.println(body);
        return doc;
    }
}
