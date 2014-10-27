/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package x3d.fields;


import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlTransient;


/**
 * 
 * @author peter
 */
@XmlTransient
public abstract class X3DArrayField<T>
        extends X3DField {

    private ArrayList<T> value;

    public ArrayList<T> getValue() {

        return this.value;
    }

    public void setValue(ArrayList<T> value) {

        this.value = value;
    }

    public X3DArrayField() {

        this.value = new ArrayList<>();
    }

    @SafeVarargs
	public X3DArrayField(T... values) {

        this.value = new ArrayList<>();

        for (T value : values) {
            this.value.add(value);
        }

    }


    public void add(T value) {

        this.getValue().add(value);
    }

    public void clear() {

        this.getValue().clear();
    }

    protected ArrayList<String> getListFromString(String value) {

        ArrayList<String> tokenList = new ArrayList<>();

        String tokenString = value;

        if (tokenString.contains(",")) {
            StringTokenizer tokenizer = new StringTokenizer(tokenString, ",",
                    false);
            while (tokenizer.hasMoreTokens()) {
                tokenList.add(tokenizer.nextToken());
            }
        }
        else {
            tokenList.add(tokenString);
        }
        return tokenList;
    }

    protected String getStringFromList(ArrayList<T> value) {

        String returnValue = new String();
        int i = 0;
        for (T field : value) {
            if (field != null) {
                returnValue += field.toString();
                if (i != value.size() - 1) {
                    returnValue += ", ";
                }
            }
            i++;
        }

        return returnValue;
    }

    @Override
    public String toString() {

        return this.getStringFromList(this.getValue());
    }

    public abstract String getStringValue();

    public abstract void setStringValue(String value);
}
