package com.samuel.desktopclient;

import com.samuel.desktopclient.helper.DialogController;
import com.samuel.desktopclient.helper.FXMLDialog;
import com.samuel.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginFormController implements DialogController {
    @Autowired
    private AuthenticationService authenticationService;
    private ScreensConfiguration screens;
    private FXMLDialog dialog;

    public void setDialog(final FXMLDialog dialog) {
        this.dialog = dialog;
    }

    public LoginFormController(final ScreensConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    public Label header;
    @FXML
    public TextField cpfField;
    @FXML
    public PasswordField passwordField;

    @FXML
    public void handleSubmitButtonAction() {
        final boolean auth = authenticationService.login(cpfField.getText(),
                passwordField.getText());
        if (auth) {
            dialog.close();
            screens.mainMenuDialog().show();
            return;
        }

        header.setText("Login failure, please try again:");
        header.setTextFill(Color.DARKRED);
    }
}
