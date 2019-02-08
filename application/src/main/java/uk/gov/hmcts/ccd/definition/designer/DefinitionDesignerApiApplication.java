package uk.gov.hmcts.ccd.definition.designer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@ComponentScan("uk.gov.hmcts.ccd.definition.designer")
public class DefinitionDesignerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefinitionDesignerApiApplication.class, args);
    }
}



