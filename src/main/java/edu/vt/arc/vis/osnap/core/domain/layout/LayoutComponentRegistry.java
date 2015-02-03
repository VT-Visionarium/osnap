package edu.vt.arc.vis.osnap.core.domain.layout;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;
import org.jutility.io.xml.XmlSerializer;

import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.Grid2DLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphericalLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedColorLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedLabelTextLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedScaleLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedShapeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseBalloonTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseForceDirectedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseFruchtermanReingoldLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleLabelTextLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleViewpointLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.Grid2DLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.IConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ILayoutConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.MappedLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.PrefuseBalloonTreeLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.PrefuseForceDirectedLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.PrefuseFruchtermanReingoldLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.PrefuseNodeLinkTreeLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.PrefuseRadialTreeLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SimpleColorLayoutConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SimpleLabelTextLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SimpleScaleLayoutConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SimpleShapeLayoutConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SimpleViewpointLayoutConfigurationWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SpericalLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.TieredOrbitalLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;


/**
 * The {@code LayoutComponentRegistry} singleton class provides the registry of
 * available {@link ILayout LayoutComponents} and their
 * {@link IConfigurationWizard Wizards}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class LayoutComponentRegistry {

    private final Map<String, Class<? extends ILayout>>                                            classMap;
    private final Map<Class<? extends ILayout>, String>                                            nameMap;
    private final Map<Class<? extends ILayout>, Class<? extends ILayoutConfigurationWizard<?, ?>>> wizardMap;
    private final Map<String, String>                                                              descriptionMap;
    private final Map<String, Set<VisualProperty>>                                                 capabilityMap;


    private static LayoutComponentRegistry                                                         s_Instance;

    /**
     * Returns the singleton instance of the class.
     * 
     * @return the singleton instance.
     */
    public static LayoutComponentRegistry Instance() {

        if (s_Instance == null) {

            s_Instance = new LayoutComponentRegistry();
        }

        return s_Instance;
    }


    private LayoutComponentRegistry() {

        this.classMap = new LinkedHashMap<>();
        this.nameMap = new LinkedHashMap<>();
        this.wizardMap = new LinkedHashMap<>();
        this.descriptionMap = new LinkedHashMap<>();
        this.capabilityMap = new LinkedHashMap<>();


        // Simple layout components
        this.registerLayoutComponent(SimpleColorLayout.class);
        this.registerWizardForLayoutComponentClass(SimpleColorLayout.class,
        // SimpleColorLayoutConfiguration.class,
                SimpleColorLayoutConfigurationWizard.class);

        this.registerLayoutComponent(SimpleShapeLayout.class);
        this.registerWizardForLayoutComponentClass(SimpleShapeLayout.class,
                SimpleShapeLayoutConfigurationWizard.class);

        this.registerLayoutComponent(SimpleScaleLayout.class);
        this.registerWizardForLayoutComponentClass(SimpleScaleLayout.class,
                SimpleScaleLayoutConfigurationWizard.class);

        this.registerLayoutComponent(SimpleLabelTextLayout.class);
        this.registerWizardForLayoutComponentClass(SimpleLabelTextLayout.class,
                SimpleLabelTextLayoutWizard.class);


        // mapped layout components
        this.registerLayoutComponent(BaseMappedLayout.class);
        this.registerWizardForLayoutComponentClass(IMappedLayout.class,
                MappedLayoutWizard.class);
        this.registerLayoutComponent(SimpleViewpointLayout.class);
        this.registerWizardForLayoutComponentClass(SimpleViewpointLayout.class,
                SimpleViewpointLayoutConfigurationWizard.class);

        XmlSerializer.Instance().registerClass(MappedColorLayout.class);
        XmlSerializer.Instance().registerClass(MappedCoordinateLayout.class);
        XmlSerializer.Instance().registerClass(MappedLabelTextLayout.class);
        XmlSerializer.Instance().registerClass(MappedScaleLayout.class);
        XmlSerializer.Instance().registerClass(MappedShapeLayout.class);

        // complex layout components
        this.registerLayoutComponent(SphericalLayout.class);
        this.registerWizardForLayoutComponentClass(SphericalLayout.class,
                SpericalLayoutWizard.class);
        this.registerLayoutComponent(Grid2DLayout.class);
        this.registerWizardForLayoutComponentClass(Grid2DLayout.class,
                Grid2DLayoutWizard.class);
        this.registerLayoutComponent(TieredOrbitalLayout.class);
        this.registerWizardForLayoutComponentClass(TieredOrbitalLayout.class,
                TieredOrbitalLayoutWizard.class);

        // prefuse layout components
        this.registerLayoutComponent(PrefuseBalloonTreeLayout.class);
        this.registerWizardForLayoutComponentClass(
                PrefuseBalloonTreeLayout.class,
                PrefuseBalloonTreeLayoutWizard.class);
        this.registerLayoutComponent(PrefuseForceDirectedLayout.class);
        this.registerWizardForLayoutComponentClass(
                PrefuseForceDirectedLayout.class,
                PrefuseForceDirectedLayoutWizard.class);
        this.registerLayoutComponent(PrefuseFruchtermanReingoldLayout.class);
        this.registerWizardForLayoutComponentClass(
                PrefuseFruchtermanReingoldLayout.class,
                PrefuseFruchtermanReingoldLayoutWizard.class);
        this.registerLayoutComponent(PrefuseNodeLinkTreeLayout.class);
        this.registerWizardForLayoutComponentClass(
                PrefuseNodeLinkTreeLayout.class,
                PrefuseNodeLinkTreeLayoutWizard.class);
        this.registerLayoutComponent(PrefuseRadialTreeLayout.class);
        this.registerWizardForLayoutComponentClass(
                PrefuseRadialTreeLayout.class,
                PrefuseRadialTreeLayoutWizard.class);

    }

    /**
     * Returns the set of the names of all registered LayoutComponents.
     * 
     * @return a set of names.
     */
    public Set<String> getLayoutComponentNames() {

        return Collections.unmodifiableSet(this.classMap.keySet());
    }

    /**
     * Returns the set of the classes of all registered LayoutComponents.
     * 
     * @return a set of classes.
     */
    public Collection<Class<? extends ILayout>> getLayoutComponentClasses() {

        return Collections.unmodifiableCollection(this.classMap.values());
    }

    /**
     * Returns the description of the LayoutComponent with the given name.
     * 
     * @param name
     *            the name of the LayoutComponent.
     * @return the description of the LayoutComponent.
     */
    public String getDescriptionOfLayoutComponent(String name) {

        return this.descriptionMap.get(name);
    }

    /**
     * Returns the set of capabilities of the LayoutComponent with the given
     * name.
     * 
     * @param name
     *            the name of the LayoutComponent.
     * @return the set of capabilities of the LayoutComponent.
     */
    public Set<VisualProperty> getCapabilityOfLayoutComponent(String name) {

        return this.capabilityMap.get(name);
    }

    /**
     * Returns the class of the LayoutComponent with the given name.
     * 
     * @param name
     *            the name of the LayoutComponent.
     * @return the class of the LayoutComponent.
     */
    public Class<? extends ILayout> getClassOfLayoutComponent(String name) {

        return this.classMap.get(name);
    }

    /**
     * Returns the name of the LayoutComponent with the given class.
     * 
     * @param layoutComponentClass
     *            the class of the LayoutComponent.
     * @return the name of the LayoutComponent.
     */
    public String getNameOfLayoutComponentClass(
            Class<? extends ILayout> layoutComponentClass) {

        return this.nameMap.get(layoutComponentClass);
    }


    /**
     * Registers the provided LayoutComponent. Assumes that the layoutComponent
     * has static methods for name, description, and capabilities.
     * 
     * @param layout
     *            the LayoutComponent to register.
     */
    public void registerLayoutComponent(Class<? extends ILayout> layout) {

        String name = null;
        String description = null;
        Set<VisualProperty> capabilities = null;

        Object nameObject = this.invokeStaticMethod("name", layout,
                String.class);
        Object descriptionObject = this.invokeStaticMethod("description",
                layout, String.class);
        Object capabilitiesObject = this.invokeStaticMethod("capabilities",
                layout, Set.class);


        if (nameObject != null && nameObject instanceof String) {

            name = (String) nameObject;
        }
        else {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "class with static name() method that "
                    + "does not return a String!");
        }

        if (descriptionObject != null && descriptionObject instanceof String) {

            description = (String) descriptionObject;
        }
        else {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "class with static description() method that "
                    + "does not return a String!");
        }


        if (capabilitiesObject != null && capabilitiesObject instanceof Set<?>) {

            Set<?> capabilitiesSet = (Set<?>) capabilitiesObject;

            capabilities = new LinkedHashSet<>();
            for (Object object : capabilitiesSet) {

                if (object instanceof VisualProperty) {
                    capabilities.add((VisualProperty) object);
                }
                else {

                    throw new IllegalArgumentException(
                            "Trying to register Layout "
                                    + "Component class with static "
                                    + "capabilities() method that "
                                    + "does not return a Set of VisualProperty!");
                }

            }

        }
        else {

            throw new IllegalArgumentException(
                    "Trying to register Layout "
                            + "Component class with static capabilities() method that "
                            + "does not return a Set!");
        }



        this.classMap.put(name, layout);
        this.nameMap.put(layout, name);
        this.descriptionMap.put(name, description);
        this.capabilityMap.put(name, capabilities);

        XmlSerializer.Instance().registerClass(layout);
    }

    /**
     * Registers a {@link ILayoutConfigurationWizard Wizard} that can be used to
     * create an instance of the {@link ILayout Layout class}.
     * 
     * @param layoutComponentClass
     *            the {@link ILayout Layout class}.
     * @param wizardClass
     *            the {@link ILayoutConfigurationWizard Wizard}.
     */
    public <O extends ILayout, C extends ILayoutConfiguration<? extends O>, W extends ILayoutConfigurationWizard<? extends O, ? extends C>> void registerWizardForLayoutComponentClass(
            Class<O> layoutComponentClass, Class<W> wizardClass) {

        if (!this.nameMap.containsKey(layoutComponentClass)) {

            this.registerLayoutComponent(layoutComponentClass);
        }

        this.wizardMap.put(layoutComponentClass, wizardClass);
    }


    /**
     * Creates an instance of the wizard for the provided layout component
     * class.
     * 
     * @param layoutComponentClass
     *            the class of the {@link ILayout} to create.
     * @param parameters
     *            the constructor parameters of the
     *            {@link ILayoutConfigurationWizard Wizard}.
     * @return the {@link ILayoutConfigurationWizard Wizard}.
     */
    public ILayoutConfigurationWizard<?, ?> createWizardForLayoutComponentClass(
            Class<? extends ILayout> layoutComponentClass, Object... parameters) {



        Class<? extends ILayoutConfigurationWizard<?, ?>> wizardClass = this.wizardMap
                .get(layoutComponentClass);

        if (wizardClass != null) {

            try {

                return ReflectionUtils.createInstance(wizardClass, parameters);
            }
            catch (ReflectionException e) {

                e.printStackTrace();
            }
        }
        return null;
    }

    private Object invokeStaticMethod(String name, Class<?> clazz,
            Class<?> returnType) {

        try {
            return ReflectionUtils.invokeMethod(clazz,
                    this.getMethod(name, clazz, returnType), null);
        }
        catch (ReflectionException e) {

            throw new IllegalArgumentException(
                    "Trying to register Layout "
                            + "Component class that cannot execute static "
                            + name + "() method!", e);
        }


    }

    private Method getMethod(String name, Class<?> clazz, Class<?> returnType) {

        try {
            return ReflectionUtils.getMethod(clazz, returnType, name, null);
        }
        catch (ReflectionException e) {
            throw new IllegalArgumentException(
                    "Trying to register Layout "
                            + "Component class that does not declare static "
                            + name + "() method!", e);
        }
    }

    /**
     * Unregisters the provided LayoutComponent.
     * 
     * @param layout
     *            the LayoutComponent to unregister.
     */
    public void unregisterLayoutComponent(Class<? extends ILayout> layout) {

        String name = this.nameMap.remove(layout);
        if (name != null) {
            this.classMap.remove(name);
            this.descriptionMap.remove(name);
            this.capabilityMap.remove(name);
        }

        XmlSerializer.Instance().unregisterClass(layout);
    }

}
