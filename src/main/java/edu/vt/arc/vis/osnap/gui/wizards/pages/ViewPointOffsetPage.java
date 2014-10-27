/**
 * 
 */
package edu.vt.arc.vis.osnap.gui.wizards.pages;



import org.controlsfx.dialog.Dialogs;

import edu.vt.arc.vis.osnap.gui.wizards.ViewPointWizard;
import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.ViewPointStatusObject;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class ViewPointOffsetPage
        extends WizardPage {


    private GridPane  grid;
    private Text      cameraOffset;
    private Text      xOffset;
    private Text      yOffset;
    private Text      zOffset;
    private TextField xOffsetTF;
    private TextField yOffsetTF;
    private TextField zOffsetTF;
    private boolean parseableFloat;



    /**
     * @param title
     */
    public ViewPointOffsetPage(String title) {

        super(title);
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.gui.wizards.pages.WizardPage#getContent()
     */
    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        cameraOffset = new Text("Set ViewPoint Offsets");
        xOffset = new Text("X Offset");
        yOffset = new Text("Y Offset");
        zOffset = new Text("Z Offset");

        xOffsetTF = new TextField();
        yOffsetTF = new TextField();
        zOffsetTF = new TextField();

        xOffsetTF.setText("0.0");
        yOffsetTF.setText("0.0");
        zOffsetTF.setText("20.0");

        grid.add(cameraOffset, 0, 0);
        grid.add(xOffset, 0, 1);
        grid.add(xOffsetTF, 1, 1);
        grid.add(yOffset, 0, 2);
        grid.add(yOffsetTF, 1, 2);
        grid.add(zOffset, 0, 3);
        grid.add(zOffsetTF, 1, 3);
        
        ViewPointOffsetPage.this.getFinishButton().setDisable(true);
        

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }



    @Override
    public ViewPointWizard getWizard() {

        if (super.getWizard() instanceof ViewPointWizard) {

            return (ViewPointWizard) super.getWizard();
        }
        return null;
    }


    @Override
    protected void nextPage() {

        if (getWizard() != null) {
            parseableFloat = true;
            ViewPointStatusObject base = getWizard().getStatusObject();

            float offX = parseFloat(xOffsetTF.getText());
            float offY = parseFloat(yOffsetTF.getText());
            float offZ = parseFloat(zOffsetTF.getText());
            
            if(!parseableFloat) {
                popUpWarning();
            }
            else {
                base.setVector(offX, offY, offZ);
                super.nextPage();
            }

        }
    }


    private float parseFloat(String str) {

        float f = 0f;
        try {
            f = Float.parseFloat(str);
        }
        catch (NumberFormatException ex) {

            parseableFloat = false;
            return Float.NaN;
        }
        return f;
    }

    private void popUpWarning() {

        Dialogs.create().title("Invalid value")
                .message("Please enter a float value for the offset")
                .showError();

    }

}
