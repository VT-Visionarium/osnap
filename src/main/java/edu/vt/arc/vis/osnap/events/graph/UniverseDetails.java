/**
 * 
 */
package edu.vt.arc.vis.osnap.events.graph;

import edu.vt.arc.vis.osnap.events.DomainObjectDetails;


/**
 * @author Willy Lund
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class UniverseDetails
        extends DomainObjectDetails {

    // TODO: add the universe metadata, schema, graph list, etc.

    /**
     * Creates a new instance of the {@link UniverseDetails} class.
     */
    public UniverseDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public UniverseDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public UniverseDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public UniverseDetails(final UniverseDetails details) {

        super(details);
    }
}
