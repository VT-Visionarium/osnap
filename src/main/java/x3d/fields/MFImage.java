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

/**
 *
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFImage")
public class MFImage 
        extends X3DArrayField <SFImage> {


    public MFImage() {
        super();
    }


   @Override
@XmlValue
    public String getStringValue() {
        return this.toString();
    }

    @Override
    public void setStringValue(String value) {
 
        ArrayList<String> tokenList = this.getListFromString(value);
        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);

            while(tokenizer.hasMoreTokens()){

                String widthToken = tokenizer.nextToken();
                String heightToken = tokenizer.nextToken();
                String componentsToken = tokenizer.nextToken();


                SFImage newImage = new SFImage();

                newImage.setWidth(Integer.parseInt(widthToken));
                newImage.setHeight(Integer.parseInt(heightToken));
                newImage.setComponents(Integer.parseInt(componentsToken));


                ArrayList<Integer> somePixels = new ArrayList<>();

                int i = 0;
                int sfImageDimensions = newImage.getWidth() * newImage.getHeight();

                while (tokenizer.hasMoreTokens() && i < sfImageDimensions){
                    try{
                        
                        String theToken = tokenizer.nextToken();


                        Integer theValue = Integer.decode(theToken);

                        somePixels.add(theValue);
                        i++;
                    }
                    catch (Exception ex){
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }
                try{
                    newImage.setPixels(somePixels);
                }
                    
                catch (IllegalArgumentException ex){
                    throw ex;
                }
                    
                this.add(newImage);
            }
        }
    }

    @Override
    public String toString() {
        String returnValue = new String();

        int i = 0;
        for (SFImage image : this.getValue()){
            returnValue += image.toString();
            i++;
            if (i < this.getValue().size()){
                returnValue += " ";
            }

        }
        return returnValue;
    }
}
