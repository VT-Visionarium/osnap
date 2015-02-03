package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ICoordinateLayoutConfiguration;


/**
 * The {@link ICoordinateLayoutConfigurationView} provides a
 * contract for all status panes for
 * {@link ICoordinateLayoutConfiguration
 * CoordinateLayoutComponentConfigurations}.
 * 
 * @param <O>
 *            the type of the {@link ICoordinateLayout}.
 * @param <C>
 *            the concrete type of the
 *            {@link ICoordinateLayoutConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface ICoordinateLayoutConfigurationView<O extends ICoordinateLayout, C extends ICoordinateLayoutConfiguration<O>>
        extends ILayoutConfigurationView<O, C> {

    // tagging interface
}
