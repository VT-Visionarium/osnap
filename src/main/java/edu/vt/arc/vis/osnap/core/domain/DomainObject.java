package edu.vt.arc.vis.osnap.core.domain;

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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.EndpointBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.GraphBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.UniverseBase;
import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;


/**
 * The abstract {@link DomainObject} class provides the basis for domain objects
 * in the core layer.
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
@XmlRootElement(name = "DomainObject")
@XmlSeeAlso({ Project.class, UniverseBase.class, Universe.class,
        GraphBase.class, Graph.class, NodeBase.class, Node.class,
        EdgeBase.class, Edge.class, HyperEdgeBase.class, HyperEdge.class,
        EndpointBase.class, Endpoint.class })
@XmlType(name = "DomainObject")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DomainObject
        implements IDomainObject {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;

    @XmlAttribute
    private Long   version;


    @Override
    public String getId() {

        return this.id;
    }

    @Override
    public void setId(final String id) {

        this.id = id;
    }

    @Override
    public long getVersion() {

        return this.version;
    }

    @Override
    public void setVersion(final long version) {

        this.version = version;
    }



    /**
     * Creates a new instance of the {@link DomainObject} class (Serialization
     * Constructor).
     */
    protected DomainObject() {

        this(null, true);
    }

    /**
     * Creates a new instance of the {@link DomainObject} class.
     * 
     * @param id
     */
    public DomainObject(final String id) {

        this(id, false);
    }

    /**
     * Creates a new instance of the {@link DomainObject} class with the
     * provided id.
     * 
     * @param id
     *            the id
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected DomainObject(final String id, final boolean serialization) {

        this.id = id;
        this.version = 0L;
    }


    /**
     * Creates a new instance of the {@link DomainObject} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    protected DomainObject(final DomainObjectDetails details) {

        this.setId(details.getId());
        this.setVersion(details.getVersion());
    }

    @Override
    public DomainObjectDetails toDetails() {

        DomainObjectDetails details = new DomainObjectDetails();

        details.setId(this.getId());
        details.setVersion(this.getVersion());

        return details;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof IDomainObject) {
            IDomainObject other = (IDomainObject) obj;

            boolean sameID = this.getId() == other.getId();

            if (!sameID && this.getId() != null) {

                sameID = this.getId().equals(other.getId());
            }

            return sameID;
        }

        return false;
    }

    @Override
    public int hashCode() {

        return (this.getId() != null ? this.getId().hashCode() : 0);
    }

    @Override
    public String toString() {

        return this.getId();
    }
}
