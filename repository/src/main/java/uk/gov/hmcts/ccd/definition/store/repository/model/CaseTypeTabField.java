package uk.gov.hmcts.ccd.definition.store.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "")
public class CaseTypeTabField {

    private CaseField caseField = null;
    private Integer order = null;

    @JsonProperty("case_field")
    public CaseField getCaseField() {
        return caseField;
    }

    public void setCaseField(CaseField caseField) {
        this.caseField = caseField;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
