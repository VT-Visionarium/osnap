package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import javafx.beans.property.ObjectProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IConfiguration;


/**
 * The {@link IConfigurationView} provides a contract for all status panes for
 * {@link IConfiguration Configurations}.
 * 
 * @param <O>
 *            the type of the configured object.
 * @param <C>
 *            the concrete type of the {@link IConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface IConfigurationView<O, C extends IConfiguration<O>> {

    /**
     * Returns the {@link IConfiguration} property.
     * 
     * @return the {@link IConfiguration} property.
     */
    public abstract ObjectProperty<C> configurationProperty();

    /**
     * Returns the value of the {@link IConfiguration} property.
     * 
     * @return the value of the {@link IConfiguration} property.
     */
    public abstract C getConfiguration();

    /**
     * Sets the value of the {@link IConfiguration} property.
     * 
     * @param value
     *            the value of the {@link IConfiguration} property.
     */
    public abstract void setConfiguration(final C value);


    /**
     * Refreshes the view.
     */
    public abstract void refreshView();
}
