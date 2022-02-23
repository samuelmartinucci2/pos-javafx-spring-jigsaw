module com.samuel.desktopclient {
    requires javafx.controls;
    requires org.slf4j;
    requires ch.qos.logback.classic;
    requires com.samuel.service;
    requires spring.beans;
    requires javafx.fxml;
    requires spring.context;
    requires lombok;
    requires java.logging;
    requires org.kordamp.ikonli.javafx;

    exports com.samuel.desktopclient;
    exports com.samuel.desktopclient.helper;
}