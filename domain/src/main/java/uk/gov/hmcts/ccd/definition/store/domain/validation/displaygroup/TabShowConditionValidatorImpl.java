package uk.gov.hmcts.ccd.definition.store.domain.validation.displaygroup;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.store.domain.showcondition.InvalidShowConditionException;
import uk.gov.hmcts.ccd.definition.store.domain.showcondition.ShowCondition;
import uk.gov.hmcts.ccd.definition.store.domain.showcondition.ShowConditionParser;
import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationResult;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupCaseFieldEntity;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupEntity;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TabShowConditionValidatorImpl implements DisplayGroupValidator {

    private final ShowConditionParser showConditionParser;

    @Autowired
    public TabShowConditionValidatorImpl(ShowConditionParser showConditionParser) {
        this.showConditionParser = showConditionParser;
    }

    @Override
    public ValidationResult validate(DisplayGroupEntity thisDisplayGroup, Collection<DisplayGroupEntity> allDisplayGroups) {
        ValidationResult validationResult = new ValidationResult();
        List allTabDisplayGroups = getAllTabDisplayGroups(thisDisplayGroup, allDisplayGroups);

        if (tabPreconditions(thisDisplayGroup)) {
            ShowCondition showCondition;
            try {
                showCondition = showConditionParser.parseShowCondition(thisDisplayGroup.getShowCondition());
            } catch (InvalidShowConditionException e) {
                validationResult.addError(new DisplayGroupInvalidShowConditionFieldForTab(thisDisplayGroup));
                return validationResult;
            }

            String showConditionField = showCondition.getField();

            if (!isInTabDisplayGroups(allTabDisplayGroups, showConditionField)) {
                validationResult.addError(new DisplayGroupInvalidShowConditionFieldForTab(showConditionField, thisDisplayGroup));
            }
        }

        if (tabFieldsPreconditions(thisDisplayGroup)) {
            for (DisplayGroupCaseFieldEntity caseField : thisDisplayGroup.getDisplayGroupCaseFields()) {
                if (caseField.getShowCondition() != null) {
                    ShowCondition showCondition;
                    try {
                        showCondition = showConditionParser.parseShowCondition(caseField.getShowCondition());
                    } catch (InvalidShowConditionException e) {
                        validationResult.addError(new DisplayGroupInvalidShowConditionFieldForTabField(caseField));
                        return validationResult;
                    }

                    String showConditionField = showCondition.getField();

                    if (!isInTabDisplayGroups(allTabDisplayGroups, showConditionField)) {
                        validationResult.addError(new DisplayGroupInvalidShowConditionFieldForTabField(showConditionField, caseField));
                    }
                }
            }
        }
        return validationResult;
    }

    private List getAllTabDisplayGroups(DisplayGroupEntity thisDisplayGroup, Collection<DisplayGroupEntity> allDisplayGroups) {
        return allDisplayGroups
            .stream()
            .filter(dg -> dg.getType() == DisplayGroupType.TAB)
            .filter(dg -> dg.getCaseType().getReference().equals(thisDisplayGroup.getCaseType().getReference()))
            .collect(Collectors.toList());
    }

    private boolean isInTabDisplayGroups(List<DisplayGroupEntity> tabDisplayGroups, String showConditionField) {
        return tabDisplayGroups.stream().filter(tdg -> tdg.hasField(showConditionField)).findFirst().isPresent();
    }

    public boolean tabPreconditions(DisplayGroupEntity displayGroup) {
        List<Predicate<DisplayGroupEntity>> preconditions = new ArrayList<>();
        preconditions.add(dg -> !StringUtils.isBlank(dg.getShowCondition()));
        preconditions.add(dg -> dg.getType() == DisplayGroupType.TAB);
        return preconditions.stream().allMatch(p -> p.test(displayGroup));
    }

    public boolean tabFieldsPreconditions(DisplayGroupEntity displayGroup) {
        List<Predicate<DisplayGroupEntity>> preconditions = new ArrayList<>();
        preconditions.add(dg -> !isAllDisplayGroupCaseFieldsShowConditionBlank(dg));
        preconditions.add(dg -> dg.getType() == DisplayGroupType.TAB);
        return preconditions.stream().allMatch(p -> p.test(displayGroup));
    }

    private boolean isAllDisplayGroupCaseFieldsShowConditionBlank(DisplayGroupEntity dg) {
        return dg.getDisplayGroupCaseFields().stream().allMatch(dgcf -> StringUtils.isBlank(dgcf.getShowCondition()));
    }
}
