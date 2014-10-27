/**
 * 
 */
package graphVisualizer.events;


/**
 * @author Peter J. Radics
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class DomainObjectDetails {

    private String id;
    private Long   version;



    /**
     * @return the id
     */
    public String getId() {

        return id;
    }



    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {

        this.id = id;
    }



    /**
     * @return the version
     */
    public long getVersion() {

        return version;
    }



    /**
     * @param version
     *            the version to set
     */
    public void setVersion(long version) {

        this.version = version;
    }



    /**
     * Creates a new instance of the {@link DomainObjectDetails} class.
     */
    public DomainObjectDetails() {


    }

    /**
     * Creates a new instance of the {@link DomainObjectDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public DomainObjectDetails(final String id) {

        this.id = id;
    }

    /**
     * Creates a new instance of the {@link DomainObjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public DomainObjectDetails(final DomainObjectDetails details) {

        this.setId(details.getId());
        this.setVersion(this.getVersion());
    }
}
