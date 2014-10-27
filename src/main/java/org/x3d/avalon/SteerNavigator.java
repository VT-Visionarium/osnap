/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.x3d.avalon;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

import org.x3d.fields.*;

/**
 *
 * @author Peter Radics
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "SteerNavigator")
public class SteerNavigator 
        extends Navigator3D {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    @XmlAttribute(name = "xRotation")
    protected SFFloat 	xRotation;
    @XmlAttribute(name = "yRotation")
    protected SFFloat 	yRotation;
    @XmlAttribute(name = "zRotation")
    protected SFFloat 	zRotation;
    @XmlAttribute(name = "xTranslation")
    protected SFFloat 	xTranslation;
    @XmlAttribute(name = "yTranslation")
    protected SFFloat 	yTranslation;
    @XmlAttribute(name = "zTranslation")
    protected SFFloat 	zTranslation;
    @XmlAttribute(name = "inputRange")
    protected MFVec2f 	inputRange;
    
    @XmlAttribute(name = "rotationSpeed")
    protected SFVec3f 	rotationSpeed;
    @XmlAttribute(name = "translationSpeed")
    protected SFVec3f 	translationSpeed;
    @XmlAttribute(name = "zeroDeflectionTrans")
    protected SFVec3f 	zeroDeflectionTrans;
    @XmlAttribute(name = "zeroDeflectionRot")
    protected SFVec3f 	zeroDeflectionRot;
    @XmlAttribute(name = "updateRotationCenter")
    protected SFBool 	updateRotationCenter;
    
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    public SFFloat getxRotation() {
        if (xRotation == null) {
            xRotation = new SFFloat(0f);
        }
        return xRotation;
    }

    public void setxRotation(SFFloat xRotation) {
        this.xRotation = xRotation;
    }
     
    public SFFloat getyRotation() {
        if (yRotation == null) {
            yRotation = new SFFloat(0f);
        }
        return yRotation;
    }

    public void setyRotation(SFFloat yRotation) {
        this.yRotation = yRotation;
    }
    
    public SFFloat getzRotation() {
        if (zRotation == null) {
            zRotation = new SFFloat(0f);
        }        
        return zRotation;
    }

    public void setzRotation(SFFloat zRotation) {
        this.zRotation = zRotation;
    }
    
    public SFFloat getxTranslation() {
        if (xTranslation == null) {
            xTranslation = new SFFloat(0f);
        }        
        return xTranslation;
    }

    public void setxTranslation(SFFloat xTranslation) {
        this.xTranslation = xTranslation;
    }

    public SFFloat getyTranslation() {
        if (yTranslation == null) {
            yTranslation = new SFFloat(0f);
        }            
        return yTranslation;
    }

    public void setyTranslation(SFFloat yTranslation) {
        this.yTranslation = yTranslation;
    }

    public SFFloat getzTranslation() {
        if (zTranslation == null) {
            zTranslation = new SFFloat(0f);
        }            
        return zTranslation;
    }

    public void setzTranslation(SFFloat zTranslation) {
        this.zTranslation = zTranslation;
    }
    
    public MFVec2f getInputRange() {
        if (inputRange == null) {
            inputRange = new MFVec2f();
        }
        return inputRange;
    }
    
    public SFVec3f getRotationSpeed() {
        if (rotationSpeed == null) {
            rotationSpeed = new SFVec3f(1f, 1f, 1f);
        }
        return rotationSpeed;
    }

    public void setRotationSpeed(SFVec3f rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public SFVec3f getTranslationSpeed() {
        if (translationSpeed == null) {
            translationSpeed = new SFVec3f(1f, 1f, 1f);
        }
        return translationSpeed;
    }

    public void setTranslationSpeed(SFVec3f translationSpeed) {
        this.translationSpeed = translationSpeed;
    }
    
    public SFVec3f getZeroDeflectionRot() {
        if ( zeroDeflectionRot == null) {
            zeroDeflectionRot = new SFVec3f(0.5f, 0.5f, 0.5f);
        }
        return zeroDeflectionRot;
    }

    public void setZeroDeflectionRot(SFVec3f zeroDeflectionRot) {
        this.zeroDeflectionRot = zeroDeflectionRot;
    }

    public SFVec3f getZeroDeflectionTrans() {
        if ( zeroDeflectionTrans == null) {
            zeroDeflectionTrans = new SFVec3f(0.5f, 0.5f, 0.5f);
        }        
        return zeroDeflectionTrans;
    }

    public void setZeroDeflectionTrans(SFVec3f zeroDeflectionTrans) {
        this.zeroDeflectionTrans = zeroDeflectionTrans;
    }
    
    public SFBool getUpdateRotationCenter() {
        if (updateRotationCenter == null) {
            return new SFBool();
        }
        return updateRotationCenter;
    }

    public void setUpdateRotationCenter(SFBool updateRotationCenter) {
        this.updateRotationCenter = updateRotationCenter;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // </editor-fold>

}
