package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


import java.util.List;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;



/**
 * The {@code IMappedLayoutConfiguration} interface provides the contract for
 * {@link ILayoutConfiguration LayoutConfigurations} of {@link IMappedLayout
 * MappedLayouts}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public interface IMappedLayoutConfiguration
        extends
        ILayoutConfiguration<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>> {

    /**
     * Returns the {@link IGraphObjectBasedValueTypeContainer ValueContainer}
     * used in the layout (the "key").
     * 
     * @return the {@link IGraphObjectBasedValueTypeContainer ValueContainer}
     *         used in the layout (the "key").
     */
    public abstract IGraphObjectBasedValueTypeContainer getDomainKey();

    /**
     * Sets the {@link IGraphObjectBasedValueTypeContainer ValueContainer} used
     * in the layout (the "key").
     * 
     * @param key
     *            the {@link IGraphObjectBasedValueTypeContainer ValueContainer}
     *            used in the layout (the "key").
     */
    public abstract void setKey(final IGraphObjectBasedValueTypeContainer key);

    /**
     * Returns the {@link List} of domain values.
     * 
     * @return the {@link List} of domain values
     */
    public abstract List<?> getDomainValues();

    /**
     * Sets the {@link List} of domain values
     * 
     * @param domainValues
     *            the {@link List} of domain values
     */
    public abstract void setDomainValues(final List<?> domainValues);


    /**
     * Returns the domain value type.
     * 
     * @return the domain value type.
     */
    public abstract Class<?> getDomainValueType();

    /**
     * Sets the domain value type.
     * 
     * @param domainValueType
     *            the domain value type.
     */
    public abstract void setDomainValueType(final Class<?> domainValueType);


    /**
     * Returns the {@link List} of {@link IValueMapping ValueMappings}.
     * 
     * @return the {@link List} of {@link IValueMapping ValueMappings}.
     */
    public abstract List<IValueMapping<?, ?>> getValueMappings();

    /**
     * Sets the {@link List} of {@link IValueMapping ValueMappings}.
     * 
     * @param valueMappings
     *            the {@link List} of {@link IValueMapping ValueMappings}.
     */
    public abstract void setValueMappings(
            final List<IValueMapping<?, ?>> valueMappings);
}