package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ICoordinateLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IPrefuseLayoutConfiguration;


/**
 * The {@link IConfigurationView} provides a contract for all status panes for
 * {@link ILayoutConfiguration LayoutComponentConfigurations}.
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
public interface IPrefuseLayoutConfigurationView<O extends IPrefuseLayout, C extends IPrefuseLayoutConfiguration<O>>
        extends ILayoutConfigurationView<O, C> {

    // tagging interface
}
