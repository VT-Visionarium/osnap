//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:21:15 AM EDT 
//


package org.x3d.model;

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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.x3d.fields.SFBool;
import org.x3d.fields.SFFloat;
import org.x3d.fields.SFVec3f;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DLightNode">
 *       &lt;attribute name="attenuation" type="{}SFVec3f" default="1 0 0" />
 *       &lt;attribute name="location" type="{}SFVec3f" default="0 0 0" />
 *       &lt;attribute name="radius" default="100">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="global" type="{}SFBool" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "PointLight")
public class PointLight
    extends X3DLightNode
{

    @XmlAttribute(name = "attenuation")
    protected SFVec3f attenuation;
    @XmlAttribute(name = "location")
    protected SFVec3f location;
    @XmlAttribute(name = "radius")
    protected SFFloat radius;
    @XmlAttribute(name = "global")
    protected SFBool global;

    /**
     * Gets the value of the attenuation property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getAttenuation() {
        return attenuation;
    }

    /**
     * Sets the value of the attenuation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setAttenuation(SFVec3f value) {
        this.attenuation = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setLocation(SFVec3f value) {
        this.location = value;
    }

    /**
     * Gets the value of the radius property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getRadius() {
        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setRadius(SFFloat value) {
        if (value.getValue() < 0){
            throw new IllegalArgumentException("Radius needs to be greater than or equal to zero!");
        }
        this.radius = value;
    }

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link SFBool }
     *     
     */
    public SFBool isGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFBool }
     *     
     */
    public void setGlobal(SFBool value) {
        this.global = value;
    }

    public PointLight() {
        this.attenuation = new SFVec3f (1.0f, 0.0f, 0.0f);
        this.location = new SFVec3f(0.0f, 0.0f, 0.0f);
        this.radius = new SFFloat(100.0f);
        this.global = new SFBool(true);
    }

}