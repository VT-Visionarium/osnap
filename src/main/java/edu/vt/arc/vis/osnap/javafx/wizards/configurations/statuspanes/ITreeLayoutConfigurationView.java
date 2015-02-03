package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import edu.vt.arc.vis.osnap.core.domain.layout.common.ITreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ITreeLayoutConfiguration;


/**
 * The {@link ITreeLayoutConfigurationView} provides a
 * contract for all status panes for
 * {@link ITreeLayoutConfiguration
 * TreeLayoutComponentConfigurations}.
 * 
 * @param <O>
 *            the type of the {@link ITreeLayout}.
 * @param <C>
 *            the concrete type of the
 *            {@link ITreeLayoutConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface ITreeLayoutConfigurationView<O extends ITreeLayout, C extends ITreeLayoutConfiguration<O>>
        extends ILayoutConfigurationView<O, C> {

    // tagging interface
}
