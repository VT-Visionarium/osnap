/**
 * 
 */
package graphVisualizer.gui.wizards;


import javafx.stage.Stage;
import graphVisualizer.graph.Universe;
import graphVisualizer.gui.wizards.pages.ViewPointOffsetPage;
import graphVisualizer.gui.wizards.statusobjects.ViewPointStatusObject;
import graphVisualizer.gui.wizards.statuspanes.ViewPointStatusPane;
import graphVisualizer.layout.mappedComponents.MappedViewpointLayoutComponent;


/**
 * @author Shawn P Neuman
 * 
 */
public class ViewPointWizard
        extends BaseLayoutWizard {


private ViewPointStatusPane pane;
    /**
     * @param uni
     */
    public ViewPointWizard(Universe uni) {

        this(null, uni);
    }

    /**
     * @param primary
     * @param uni
     */
    public ViewPointWizard(Stage primary, Universe uni) {

        super(primary, new ViewPointStatusPane("ViewPoint Selection Wizard"),
                new ViewPointStatusObject(), MappedViewpointLayoutComponent.capabilities(), 2, uni, new ViewPointOffsetPage(
                        "Set X, Y, and Z offsets"));
        getStatusObject().setLayoutComponent(
                new MappedViewpointLayoutComponent());
        pane = (ViewPointStatusPane) this.getStatusPane();
        getStatusObject().addObserver(pane);

    }


    @Override
    public ViewPointStatusObject getStatusObject() {

        if (super.getStatusObject() instanceof ViewPointStatusObject) {
            return (ViewPointStatusObject) super.getStatusObject();
        }
        return null;
    }

}
