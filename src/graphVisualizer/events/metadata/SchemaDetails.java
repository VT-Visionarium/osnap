/**
 * 
 */
package graphVisualizer.events.metadata;

import graphVisualizer.events.DomainObjectDetails;


/**
 * @author Willy Lund
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class SchemaDetails
        extends DomainObjectDetails {

    // TODO: add the schema data.

    /**
     * Creates a new instance of the {@link SchemaDetails} class.
     */
    public SchemaDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link SchemaDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public SchemaDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link SchemaDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public SchemaDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link SchemaDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public SchemaDetails(final SchemaDetails details) {

        super(details);
    }
}
