package edu.vt.arc.vis.osnap.core.domain.graph;

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


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.base.EndpointBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The <CODE>EndpointBaseDetails</CODE> class provides an abstraction of the
 * elements involved in <CODE>Edge</CODE>scale and <CODE>HyperEdge</CODE>scale.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see Edge
 * @see HyperEdge
 * 
 */
@XmlType(name = "Endpoint")
@XmlSeeAlso({ Node.class })
public class Endpoint
        extends EndpointBase {

    @Override
    public void setId(String id) {

        super.setId(id);
    }

    @Override
    @XmlIDREF
    @XmlAttribute
    public Node getNode() {

        if (super.getNode() instanceof Node) {

            return (Node) super.getNode();
        }
        return null;
    }

    /**
     * Sets the node.
     * 
     * @param node
     *            the node.
     */
    protected void setNode(Node node) {

        super.setNode(node);
    }


    @SuppressWarnings("unused")
    private Endpoint() {

        this(null, null, null, null, true);
    }

    /**
     * Creates a new EndpointBaseDetails with the provided id, node, and
     * directions.
     * 
     * @param id
     *            the id.
     * @param node
     *            the node.
     * @param isIncoming
     *            whether or not the endpoint is incoming.
     * @param isOutgoing
     *            whether or not the endpoint is outgoing.
     */
    public Endpoint(String id, INode node, Boolean isIncoming,
            Boolean isOutgoing) {

        this(id, node, isIncoming, isOutgoing, false);
    }

    /**
     * Creates a new EndpointBaseDetails with the provided id, node, and
     * directions.
     * 
     * @param id
     *            the id.
     * @param node
     *            the node.
     * @param isIncoming
     *            whether or not the endpoint is incoming.
     * @param isOutgoing
     *            whether or not the endpoint is outgoing.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Endpoint(String id, INode node, Boolean isIncoming,
            Boolean isOutgoing, boolean serialization) {

        super(id, node, isIncoming, isOutgoing, serialization);

        if (node == null && !serialization) {
            throw new IllegalArgumentException("An endpoint edge must contain "
                    + "a node.");
        }
    }


    @Override
    protected void setIncoming() {

        super.setIncoming();
    }

    @Override
    protected void setNotIncoming() {

        super.setNotIncoming();
    }

    @Override
    protected void setNotOutgoing() {

        super.setNotOutgoing();
    }

    @Override
    protected void setOutgoing() {

        super.setOutgoing();
    }

}
