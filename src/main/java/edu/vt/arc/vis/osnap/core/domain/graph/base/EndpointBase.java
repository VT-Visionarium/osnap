package edu.vt.arc.vis.osnap.core.domain.graph.base;


import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEndpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link EndpointBase} class provides a base implementation of the
 * {@link IEndpoint} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "EndpointBase")
@XmlSeeAlso({ Endpoint.class, Node.class, NodeBase.class })
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EndpointBase
        extends DomainObject
        implements IEndpoint {


    private INode   node;

    @XmlAttribute(name = "incoming")
    private Boolean isIncoming;
    @XmlAttribute(name = "outgoing")
    private Boolean isOutgoing;


    @Override
    public void setId(String id) {

        if (this.getNode().getGraph().getUniverse().changeID(this, id)) {

            super.setId(id);
        }
    }

    /**
     * Creates a new instance of the {@link EndpointBase} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private EndpointBase() {

        this(null, null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link EndpointBase} class with the
     * provided id, node, and directions.
     * 
     * @param id
     *            the id.
     * @param node
     *            the {@link INode node}.
     * @param isIncoming
     *            whether or not the {@link EndpointBase} is incoming.
     * @param isOutgoing
     *            whether or not the {@link EndpointBase} is outgoing.
     */
    public EndpointBase(String id, INode node, Boolean isIncoming,
            Boolean isOutgoing) {

        this(id, node, isIncoming, isOutgoing, false);
    }

    /**
     * Creates a new instance of the {@link EndpointBase} class with the
     * provided id, node, and directions.
     * 
     * @param id
     *            the id.
     * @param node
     *            the {@link INode node}.
     * @param isIncoming
     *            whether or not the {@link EndpointBase} is incoming.
     * @param isOutgoing
     *            whether or not the {@link EndpointBase} is outgoing.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected EndpointBase(String id, INode node, Boolean isIncoming,
            Boolean isOutgoing, boolean serialization) {

        super(EndpointBase.createIdentifier(id, node, serialization),
                serialization);

        this.node = node;

        this.isIncoming = isIncoming;
        this.isOutgoing = isOutgoing;
    }


    /**
     * Sets the node.
     * 
     * @param node
     *            the node.
     */
    protected void setNode(INode node) {

        this.node = node;
    }

    /**
     * Switches the endpoint to incoming.
     */
    protected void setIncoming() {

        this.isIncoming = true;
        this.isOutgoing = false;
    }

    /**
     * Switches the endpoint to be not incoming.
     */
    protected void setNotIncoming() {

        this.isIncoming = false;
    }

    /**
     * Switches the endpoint to outgoing.
     */
    protected void setOutgoing() {

        this.isOutgoing = true;
        this.isIncoming = false;
    }

    /**
     * Switches the endpoint to not outgoing.
     */
    protected void setNotOutgoing() {

        this.isOutgoing = false;
    }


    @Override
    public INode getNode() {

        return this.node;
    }


    @Override
    public Boolean isIncoming() {

        return this.isIncoming;
    }

    @Override
    public Boolean isOutgoing() {

        return this.isOutgoing;
    }


    @Override
    public Set<GraphObjectProperty> hasGraphProperties() {

        return Collections.unmodifiableSet(EnumSet.of(
                GraphObjectProperty.INCOMING, GraphObjectProperty.OUTGOING));
    }

    @Override
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty) {

        switch (graphObjectProperty) {
            case INCOMING:
                return this.isIncoming();
            case OUTGOING:
                return this.isOutgoing();
            default:
                throw new UnsupportedOperationException("GraphObjectProperty "
                        + graphObjectProperty
                        + " is not supported by this endpoint.");

        }
    }

    @Override
    public boolean isIdentical(IGraphObject other) {

        if (other instanceof IEndpoint) {

            return this.isIdentical((IEndpoint) other, true);
        }

        return false;
    }

    @Override
    public boolean isIdentical(IEndpoint other, boolean recurse) {


        if (this.equals(other)) {

            boolean sameDirection = this.isIncoming()
                    .equals(other.isIncoming())
                    && this.isOutgoing().equals(other.isOutgoing());

            if (sameDirection) {

                if (recurse) {

                    boolean nodesIdentical = this.getNode().isIdentical(
                            other.getNode(), false);

                    return nodesIdentical;
                }
                else {

                    boolean nodesEqual = this.getNode().equals(other.getNode());

                    return nodesEqual;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {

        return "<EndpointBaseDetails id=\"" + this.getId() + "\" node=\""
                + this.getNode().getId() + "\" incoming=\"" + this.isIncoming()
                + "\" + outgoing=\"" + this.isOutgoing() + "\"/>";
    }


    private static String createIdentifier(final String id, final INode node,
            final boolean serialization) {

        String identifier = null;
        if ((id == null || "".equals(id)) && !serialization) {
            throw new IllegalArgumentException("Cannot create an endpoint "
                    + "without an id!");
        }

        if (node != null) {
            identifier = id + '_' + node.getId();
        }
        else {
            identifier = id;
        }

        return identifier;
    }
}
