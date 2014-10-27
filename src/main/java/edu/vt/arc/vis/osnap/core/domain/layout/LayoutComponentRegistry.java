package edu.vt.arc.vis.osnap.core.domain.layout;


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SolarSystemCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphereCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TwoDGridCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedColorLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedLabelTextLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedScaleLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedShapeLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedViewpointLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.BalloonTreeCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.ForceDirectedCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.FruchtermanReingoldCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.NodeLinkTreeCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.RadialTreeCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleLabelTextLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.BalloonTreeWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ColorLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.CoordinateLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ForceDirectedWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.FruchtermanReingoldWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.LabelTextLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.MappedVisualPropertyProviderWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.NodeLinkTreeWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.RadialTreeWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ScaleLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ShapeLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SolarSystemWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.SphereLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.TwoDGridWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.ViewPointWizard;

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


/**
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class LayoutComponentRegistry {

    private final Map<String, Class<? extends ILayoutComponent>>                             classMap;
    private final Map<Class<? extends ILayoutComponent>, String>                             nameMap;
    private final Map<Class<? extends ILayoutComponent>, Class<? extends IWizardWithStatus>> wizardMap;
    private final Map<String, String>                                                        descriptionMap;
    private final Map<String, Set<VisualProperty>>                                           capabilityMap;


    private static LayoutComponentRegistry                                                   s_Instance;

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
        this.registerLayoutComponent(SimpleColorLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SimpleColorLayoutComponent.class, ColorLayoutWizard.class);

        this.registerLayoutComponent(SimpleShapeLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SimpleShapeLayoutComponent.class, ShapeLayoutWizard.class);

        this.registerLayoutComponent(SimpleScaleLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SimpleScaleLayoutComponent.class, ScaleLayoutWizard.class);

        this.registerLayoutComponent(SimpleLabelTextLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SimpleLabelTextLayoutComponent.class,
                LabelTextLayoutWizard.class);

        this.registerLayoutComponent(SimpleCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SimpleCoordinateLayoutComponent.class,
                CoordinateLayoutWizard.class);

        // mapped layout components
        this.registerLayoutComponent(BaseMappedLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                BaseMappedLayoutComponent.class,
                MappedVisualPropertyProviderWizard.class);
        this.registerLayoutComponent(MappedViewpointLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                MappedViewpointLayoutComponent.class, ViewPointWizard.class);

        XmlSerializer.Instance()
                .registerClass(MappedColorLayoutComponent.class);
        XmlSerializer.Instance().registerClass(
                MappedCoordinateLayoutComponent.class);
        XmlSerializer.Instance().registerClass(
                MappedLabelTextLayoutComponent.class);
        XmlSerializer.Instance()
                .registerClass(MappedScaleLayoutComponent.class);
        XmlSerializer.Instance()
                .registerClass(MappedShapeLayoutComponent.class);

        // complex layout components
        this.registerLayoutComponent(SphereCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SphereCoordinateLayoutComponent.class, SphereLayoutWizard.class);
        this.registerLayoutComponent(TwoDGridCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                TwoDGridCoordinateLayoutComponent.class, TwoDGridWizard.class);
        this.registerLayoutComponent(SolarSystemCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                SolarSystemCoordinateLayoutComponent.class,
                SolarSystemWizard.class);

        // prefuse layout components
        this.registerLayoutComponent(BalloonTreeCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                BalloonTreeCoordinateLayoutComponent.class,
                BalloonTreeWizard.class);
        this.registerLayoutComponent(ForceDirectedCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                ForceDirectedCoordinateLayoutComponent.class,
                ForceDirectedWizard.class);
        this.registerLayoutComponent(FruchtermanReingoldCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                FruchtermanReingoldCoordinateLayoutComponent.class,
                FruchtermanReingoldWizard.class);
        this.registerLayoutComponent(NodeLinkTreeCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                NodeLinkTreeCoordinateLayoutComponent.class,
                NodeLinkTreeWizard.class);
        this.registerLayoutComponent(RadialTreeCoordinateLayoutComponent.class);
        this.registerWizardForLayoutComponentClass(
                RadialTreeCoordinateLayoutComponent.class,
                RadialTreeWizard.class);

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
    public Collection<Class<? extends ILayoutComponent>> getLayoutComponentClasses() {

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
    public Class<? extends ILayoutComponent> getClassOfLayoutComponent(
            String name) {

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
            Class<? extends ILayoutComponent> layoutComponentClass) {

        return this.nameMap.get(layoutComponentClass);
    }


    /**
     * Registers the provided LayoutComponent. Assumes that the layoutComponent
     * has static methods for name, description, and capabilities.
     * 
     * @param layoutComponent
     *            the LayoutComponent to register.
     */
    public void registerLayoutComponent(
            Class<? extends ILayoutComponent> layoutComponent) {

        String name = null;
        String description = null;
        Set<VisualProperty> capabilities = null;

        Object nameObject = this.invokeStaticMethod("name", layoutComponent,
                String.class);
        Object descriptionObject = this.invokeStaticMethod("description",
                layoutComponent, String.class);
        Object capabilitiesObject = this.invokeStaticMethod("capabilities",
                layoutComponent, Set.class);


        if (nameObject != null && nameObject instanceof String) {

            name = (String) nameObject;
        }
        else {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class with static name() method that "
                    + "does not return a String!");
        }

        if (descriptionObject != null && descriptionObject instanceof String) {

            description = (String) descriptionObject;
        }
        else {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class with static description() method that "
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

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class with static capabilities() method that "
                    + "does not return a Set!");
        }



        this.classMap.put(name, layoutComponent);
        this.nameMap.put(layoutComponent, name);
        this.descriptionMap.put(name, description);
        this.capabilityMap.put(name, capabilities);

        XmlSerializer.Instance().registerClass(layoutComponent);
    }

    /**
     * Registers a {@link IWizardWithStatus Wizard} that can be used to create
     * an instance of the {@link ILayoutComponent LayoutComponent class}.
     * 
     * @param layoutComponentClass
     *            the {@link ILayoutComponent LayoutComponent class}.
     * @param wizard
     *            the {@link IWizardWithStatus Wizard}.
     */
    public void registerWizardForLayoutComponentClass(
            Class<? extends ILayoutComponent> layoutComponentClass,
            Class<? extends IWizardWithStatus> wizard) {

        if (!this.nameMap.containsKey(layoutComponentClass)) {

            this.registerLayoutComponent(layoutComponentClass);
        }

        this.wizardMap.put(layoutComponentClass, wizard);
    }


    /**
     * Creates an instance of the wizard for the provided layout component
     * class.
     * 
     * @param layoutComponentClass
     *            the class of the {@link ILayoutComponent} to create.
     * @param parameters
     *            the constructor parameters of the {@link IWizardWithStatus
     *            Wizard}.
     * @return the {@link IWizardWithStatus Wizard}.
     */
    public IWizardWithStatus createWizardForLayoutComponentClass(
            Class<? extends ILayoutComponent> layoutComponentClass,
            Object... parameters) {

        Class<? extends IWizardWithStatus> wizardClass = this.wizardMap
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

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that cannot execute static " + name
                    + "() method!", e);
        }


    }

    private Method getMethod(String name, Class<?> clazz, Class<?> returnType) {

        try {
            return ReflectionUtils.getMethod(clazz, returnType, name, null);
        }
        catch (ReflectionException e) {
            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that does not declare static " + name
                    + "() method!", e);
        }
    }

    /**
     * Unregisters the provided LayoutComponent.
     * 
     * @param layoutComponent
     *            the LayoutComponent to unregister.
     */
    public void unregisterLayoutComponent(
            Class<? extends ILayoutComponent> layoutComponent) {

        String name = this.nameMap.remove(layoutComponent);
        if (name != null) {
            this.classMap.remove(name);
            this.descriptionMap.remove(name);
            this.capabilityMap.remove(name);
        }

        XmlSerializer.Instance().unregisterClass(layoutComponent);
    }

}
