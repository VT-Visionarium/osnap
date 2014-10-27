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

/**
 *
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SFRotation")
public class SFRotation
        extends X3DField  {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFFloat xValue;
    private SFFloat yValue;
    private SFFloat zValue;
    private SFFloat angle;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    public SFFloat getXValue() {
        return xValue;
    }

    public void setXValue(SFFloat xValue)
        throws IllegalArgumentException {
        if (xValue.getValue() < -1.0f || xValue.getValue() > 1.0f){
            throw new IllegalArgumentException("");
        }
        this.xValue = xValue;
    }

    public SFFloat getYValue() {
        return yValue;
    }

    public void setYValue(SFFloat yValue)
        throws IllegalArgumentException {
        if (yValue.getValue() < -1.0f || yValue.getValue() > 1.0f){
            throw new IllegalArgumentException("");
        }
        this.yValue = yValue;
    }

    public SFFloat getZValue() {
        return zValue;
    }

    public void setZValue(SFFloat zValue)
        throws IllegalArgumentException {
        if (zValue.getValue() < -1.0f || zValue.getValue() > 1.0f){
            throw new IllegalArgumentException("");
        }
        this.zValue = zValue;
    }

    public SFFloat getAngle() {
        return angle;
    }

    public void setAngle(SFFloat angle) {
        this.angle = angle;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();
        String thirdToken = tokenizer.nextToken();
        String fourthToken = tokenizer.nextToken();

        this.setXValue(new SFFloat(Float.parseFloat(firstToken)));
        this.setYValue(new SFFloat (Float.parseFloat(secondToken)));
        this.setZValue(new SFFloat (Float.parseFloat(thirdToken)));
        this.setAngle(new SFFloat (Float.parseFloat(fourthToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFRotation(){
        this (0.0f, 0.0f, 1.0f, 0.0f);
    }

    public SFRotation (SFVec3f axis, SFFloat angle) {
        this (axis, angle.getValue());
    }

    public SFRotation (SFVec3f axis, Float angle) {
        this (axis.getFirstValue(), axis.getSecondValue(), axis.getThirdValue(),
                angle);
    }

    public SFRotation (SFFloat xValue, SFFloat yValue, SFFloat zValue, SFFloat angle){
        this (xValue.getValue(), yValue.getValue(), zValue.getValue(), angle.getValue());
    }

    public SFRotation (SFFloat xValue, SFFloat yValue, SFFloat zValue, Float angle){
        this (xValue.getValue(), yValue.getValue(), zValue.getValue(), angle);
    }

    public SFRotation (Float xValue, Float yValue, Float zValue, Float angle) {
        if (xValue < -1.0f || xValue > 1.0f){
            throw new IllegalArgumentException("");
        }
        if (yValue < -1.0f || yValue > 1.0f){
            throw new IllegalArgumentException("");
        }
        if (zValue < -1.0f || zValue > 1.0f){
            throw new IllegalArgumentException("");
        }
        this.xValue = new SFFloat (xValue);
        this.yValue = new SFFloat (yValue);
        this.zValue = new SFFloat (zValue);
        this.angle = new SFFloat (angle);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Overrides and Overridables">

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.xValue;
        returnValue += " ";
        returnValue += this.yValue;
        returnValue += " ";
        returnValue += this.zValue;
        returnValue += " ";
        returnValue += this.angle;

        return returnValue;
    }

    // </editor-fold>

}
