/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package x3d.fields;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Formatter;
/**
 *
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SFImage")
public class SFImage 
        extends X3DField{

    private Integer width;
    private Integer height;
    private Integer components;

    private ArrayList<Integer> pixels;

    public Integer getComponents() {
        return components;
    }

    public void setComponents(Integer components)
        throws IllegalArgumentException{
        if (components < 0 || components > 4){
            throw new IllegalArgumentException("Number of components of an SFImage must be between 0 and 4");
        }
        this.components = components;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height)
        throws IllegalArgumentException{
        if (height < 0){
            throw new IllegalArgumentException("Height of SFImage cannot be smaller zero!");
        }
        this.height = height;
    }

    public ArrayList<Integer> getPixels() {
        return pixels;
    }

    public void setPixels(ArrayList<Integer> pixels)
        throws IllegalArgumentException{
        if (pixels.size() != (this.width * this.height)){
            throw new IllegalArgumentException("Number of pixels (" + pixels.size() + ") deviates from number of pixels calculated (" + (width*height) +")!" );
        }
        this.pixels = pixels;
    }

    public void addPixel (Integer pixel)
        throws IllegalArgumentException {
        if (pixels.size() == (this.width * this.height)){
            throw new IllegalArgumentException ("Number of pixels already at capacity!" );
        }
        else {
            this.pixels.add(pixel);
        }
    }

    public void clearPixels (){
        this.pixels.clear();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) 
        throws IllegalArgumentException{
        if (width < 0){
            throw new IllegalArgumentException("Width of SFImage cannot be smaller zero!");
        }
        this.width = width;
    }

    public SFImage() {
        this.width = 0;
        this.height = 0;
        this.components = 0;
        this.pixels = new ArrayList<>();
    }

   @XmlValue
    public String getStringValue() {
        return this.toString();
    }

    public void setStringValue(String value)
        throws IllegalArgumentException {
        //ArrayList<String> tokenList = X3DArrayFieldTokenizer.getListFromString(value);

        //for (String token : tokenList){
            //StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            StringTokenizer tokenizer = new StringTokenizer(value, " ", false);
            String widthToken = tokenizer.nextToken();
            String heightToken = tokenizer.nextToken();
            String componentsToken = tokenizer.nextToken();

            this.width = Integer.parseInt(widthToken);
            this.height = Integer.parseInt(heightToken);
            this.components = Integer.parseInt(componentsToken);

            ArrayList<Integer> somePixels = new ArrayList<>();
            while (tokenizer.hasMoreTokens()){
                try{
                    String theToken = tokenizer.nextToken();


                    Integer theValue = Integer.decode(theToken);

                    somePixels.add(theValue);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
            this.setPixels(somePixels);
        //}
    }

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += width + " " + height + " " + components;

        if (!this.pixels.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            Formatter formatter = new Formatter(stringBuilder);

            for (Integer value : this.pixels){
                formatter.format("%scale", " ");
                formatter.format("%#x", value);
            }

            returnValue += stringBuilder.toString();
            formatter.close();
            
            formatter.close();
        }
        return returnValue;
    }



}
