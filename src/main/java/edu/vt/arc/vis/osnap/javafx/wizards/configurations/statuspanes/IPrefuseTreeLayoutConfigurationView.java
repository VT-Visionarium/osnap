package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseTreeLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IPrefuseTreeLayoutConfiguration;


/**
 * The {@link IPrefuseTreeLayoutConfigurationView} interface provides a contract
 * for all status panes for {@link IPrefuseTreeLayoutConfiguration}.
 * 
 * @param <O>
 *            the type of the {@link IPrefuseTreeLayoutComponent}.
 * @param <C>
 *            the concrete type of the {@link IPrefuseTreeLayoutConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface IPrefuseTreeLayoutConfigurationView<O extends IPrefuseTreeLayoutComponent, C extends IPrefuseTreeLayoutConfiguration<O>>
        extends IPrefuseLayoutConfigurationView<O, C>,
        ITreeLayoutConfigurationView<O, C> {

    // tagging interface
}
