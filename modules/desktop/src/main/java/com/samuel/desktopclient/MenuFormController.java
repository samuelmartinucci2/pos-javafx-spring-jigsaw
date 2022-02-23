package com.samuel.desktopclient;

import com.samuel.desktopclient.helper.DialogController;
import com.samuel.desktopclient.helper.FXMLDialog;
import com.samuel.service.AuthenticationService;
import com.samuel.service.util.LoginRoles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MenuFormController implements DialogController, Initializable {
    @Autowired
    private AuthenticationService authenticationService;
    private ScreensConfiguration screens;
    private FXMLDialog dialog;

    public void setDialog(final FXMLDialog dialog) {
        this.dialog = dialog;
    }

    public MenuFormController(final ScreensConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    public MenuItem customersMenuItem;
    @FXML
    public MenuItem employeesMenuItem;
    @FXML
    public MenuItem productsMenuItem;
    @FXML
    public MenuItem cashierMenuItem;

    @FXML
    public void handleCustomerRegistrationMenu() {
        screens.customerRegistrationDialog().show();
    }

    @FXML
    public void handleEmployeeRegistrationMenu() {
        screens.employeeRegistrationDialog().show();
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        final Set<String> loggedRoles = authenticationService.loggedUserRoles();
        if (!loggedRoles.contains(LoginRoles.ADMIN)) {
            employeesMenuItem.setVisible(false);
        }
    }
}
