package com.samuel.desktopclient;

import com.samuel.desktopclient.helper.FXMLDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Lazy
@Configuration
public class ScreensConfiguration {
    private Stage primaryStage;

    public void setPrimaryStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Bean
    @Scope("prototype")
    FXMLDialog loginDialog() {
        return new FXMLDialog(loginController(),
                getClass().getResource("login.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    LoginFormController loginController() {
        return new LoginFormController(this);
    }

    @Bean
    @Scope("prototype")
    FXMLDialog mainMenuDialog() {
        return new FXMLDialog(mainMenuController(),
                getClass().getResource("main.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    MenuFormController mainMenuController() {
        return new MenuFormController(this);
    }

    @Bean
    @Scope("prototype")
    FXMLDialog customerRegistrationDialog() {
        return new FXMLDialog(customerRegistrationController(),
                getClass().getResource("customer_registration.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    CustomerRegistrationFormController customerRegistrationController() {
        return new CustomerRegistrationFormController(this);
    }

    @Bean
    @Scope("prototype")
    FXMLDialog employeeRegistrationDialog() {
        return new FXMLDialog(employeeRegistrationController(),
                getClass().getResource("employee_registration.fxml"), primaryStage, StageStyle.DECORATED);
    }

    @Bean
    @Scope("prototype")
    EmployeeRegistrationFormController employeeRegistrationController() {
        return new EmployeeRegistrationFormController(this);
    }
}