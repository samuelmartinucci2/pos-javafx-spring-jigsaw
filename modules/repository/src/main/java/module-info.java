module com.samuel.repository {
    requires java.persistence;
    requires lombok;
    requires spring.data.jpa;
    requires org.hibernate.orm.core;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.tx;
    requires spring.orm;
    requires java.sql;
    requires spring.boot;

    exports com.samuel.repository;
    exports com.samuel.repository.model;

    opens com.samuel.repository.config to spring.beans, spring.context;
    opens com.samuel.repository.model to org.hibernate.orm.core;
}