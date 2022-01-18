package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsSceneController;
import javafx.fxml.FXML;

/**
 * Controller für {@link TrialCreator}.
 */
public class TrialCreatorController extends AbsSceneController {

    @FXML
    private void startFunctionChooser() {
        Logger.info("trialcreator:content Starting Function Chooser");
        FunctionChooser.start();
    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
    }

}
