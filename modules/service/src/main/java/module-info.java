module com.samuel.service {
    exports com.samuel.service;
    exports com.samuel.service.config;
    exports com.samuel.service.auth;
    exports com.samuel.service.dto;

    requires spring.context;
    requires lombok;

    requires com.samuel.repository;
    requires spring.tx;
    requires spring.security.core;
    requires spring.beans;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires org.apache.commons.lang3;
    requires spring.security.crypto;
    requires modelmapper;

    opens com.samuel.service.impl to spring.beans;
    exports com.samuel.service.util;
}