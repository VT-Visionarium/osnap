package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


/**
 * The {@code IConfiguration} interface provides a common contract for
 * configurations.
 * 
 * @param <T>
 *            the type of the configured object.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 1.2.3
 */
public interface IConfiguration<T> {

    /**
     * Creates an instance of the configured object.
     * 
     * @return an instance of the configured object.
     */
    public abstract T createConfiguredObject();
}
