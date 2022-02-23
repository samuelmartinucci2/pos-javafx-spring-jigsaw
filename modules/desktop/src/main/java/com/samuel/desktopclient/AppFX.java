package com.samuel.desktopclient;

import com.samuel.service.config.SpringContextConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppFX extends Application {

    @Override
    public void start(final Stage stage) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(SpringContextConfig.class);
        final ScreensConfiguration screens = context.getBean(ScreensConfiguration.class);
        screens.setPrimaryStage(stage);
        screens.loginDialog().show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
