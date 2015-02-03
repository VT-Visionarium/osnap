package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ICoordinateLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;


/**
 * The {@link ILayoutConfigurationView} provides a contract for all status panes
 * for {@link ILayoutConfiguration LayoutComponentConfigurations}.
 * 
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the concrete type of the
 *            {@link ICoordinateLayoutConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface ILayoutConfigurationView<O extends ILayout, C extends ILayoutConfiguration<O>>
        extends IConfigurationView<O, C> {

    // tagging interface
}
