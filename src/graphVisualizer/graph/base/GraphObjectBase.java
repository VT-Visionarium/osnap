/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package graphVisualizer.graph.base;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import graphVisualizer.graph.common.IGraphObject;


/**
 * The abstract {@link GraphObjectBase} class provides a base implementation of
 * the {@link IGraphObject} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlRootElement(name = "GraphObjectBase")
@XmlType(name = "GraphObjectBase")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class GraphObjectBase
        implements IGraphObject {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;



    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.graph.common.IGraphObject#getID()
     */
    @Override
    public String getID() {

        return this.id;
    }


    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.graph.common.IGraphObject#setID(java.lang.String)
     */
    @Override
    public void setID(String id) {

        this.id = id;
    }

    /**
     * Creates a new instance of the {@link GraphObjectBase} class.
     * (Serialization Constructor)
     */
    protected GraphObjectBase() {

        this(null, true);
    }

    /**
     * Creates a new instance of the {@link GraphObjectBase} class with the
     * provided id.
     * 
     * @param id
     *            the id of this {@link IGraphObject}.
     */
    public GraphObjectBase(final String id) {

        this(id, false);
    }

    /**
     * Creates a new instance of the {@link GraphObjectBase} class with the
     * provided id.
     * 
     * @param id
     *            the id of this {@link IGraphObject}.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected GraphObjectBase(final String id, final boolean serialization) {

        if ((id == null || "".equals(id)) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a graph object without an id!");
        }

        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof IGraphObject) {
            IGraphObject other = (IGraphObject) obj;

            boolean sameID = this.getID() == other.getID();

            if (!sameID && this.getID() != null) {

                sameID = this.getID().equals(other.getID());
            }

            return sameID;
        }

        return false;
    }

    @Override
    public int hashCode() {

        return (this.getID() != null ? this.getID().hashCode() : 0);
    }

    @Override
    public String toString() {

        return this.getID();
    }
}
