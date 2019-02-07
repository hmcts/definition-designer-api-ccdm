package uk.gov.hmcts.ccd.definition.designer.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class ApplicationParams {

    @Value("${auth.idam.client.baseUrl}")
    private String idamHost;

    public String idamUserProfileURL() {
        return idamHost + "/details";
    }
}
