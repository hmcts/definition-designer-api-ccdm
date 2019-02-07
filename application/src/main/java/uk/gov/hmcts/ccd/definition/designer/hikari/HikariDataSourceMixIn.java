package uk.gov.hmcts.ccd.definition.designer.hikari;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaxxer.hikari.HikariConfigMXBean;
import java.io.PrintWriter;

@SuppressWarnings("squid:S1610")
public abstract class HikariDataSourceMixIn {

    @JsonIgnore
    abstract PrintWriter getLogWriter();

    @JsonIgnore
    abstract HikariConfigMXBean getHikariConfigMXBean();
}
