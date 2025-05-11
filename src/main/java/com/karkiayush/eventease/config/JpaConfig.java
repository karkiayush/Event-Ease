package com.karkiayush.eventease.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // This class is used to enable JPA Auditing. It will automatically populate the created and updated date fields in the entity classes.
    // We can also specify the date format here if we want to.
    // But for now, we are not specifying any date format. So, it will use the default date format.
}
