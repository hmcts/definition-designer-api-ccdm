package uk.gov.hmcts.ccd.definition.store.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "")
public class WizardPage {

    private String id = null;
    private String label = null;
    private Integer order = null;
    private List<WizardPageField> wizardPageFields = new ArrayList<>();
    private String showCondition;

    @ApiModelProperty(value = "")
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("wizard_page_fields")
    public List<WizardPageField> getWizardPageFields() {
        return wizardPageFields;
    }

    public void setWizardPageFields(List<WizardPageField> wizardPageFields) {
        this.wizardPageFields = wizardPageFields;
    }

    public void setShowCondition(String showCondition) {
        this.showCondition = showCondition;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("show_condition")
    public String getShowCondition() {
        return showCondition;
    }
}
