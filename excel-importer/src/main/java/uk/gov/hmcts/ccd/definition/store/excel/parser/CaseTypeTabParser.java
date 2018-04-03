package uk.gov.hmcts.ccd.definition.store.excel.parser;

import uk.gov.hmcts.ccd.definition.store.domain.showcondition.ShowConditionParser;
import uk.gov.hmcts.ccd.definition.store.excel.util.mapper.ColumnName;
import uk.gov.hmcts.ccd.definition.store.excel.util.mapper.SheetName;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupPurpose;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupType;

public class CaseTypeTabParser extends AbstractDisplayGroupParser {

    public CaseTypeTabParser(ParseContext parseContext, ShowConditionParser showConditionParser, EntityToDefinitionDataItemRegistry entityToDefinitionDataItemRegistry) {
        super(parseContext, showConditionParser, entityToDefinitionDataItemRegistry);
        this.displayGroupId = ColumnName.TAB_ID;
        this.displayGroupLabel = ColumnName.TAB_LABEL;
        this.displayGroupOrder = ColumnName.TAB_DISPLAY_ORDER;
        this.displayGroupFieldDisplayOrder = ColumnName.TAB_FIELD_DISPLAY_ORDER;
        this.displayGroupPurpose = DisplayGroupPurpose.VIEW;
        this.displayGroupType = DisplayGroupType.TAB;
        this.displayGroupItemMandatory = true;
        this.sheetName = SheetName.CASE_TYPE_TAB;
    }
}