package uk.gov.hmcts.ccd.definition.designer.rest.endpoint.exceptions;

public class DuplicateFoundException extends RuntimeException {

    public DuplicateFoundException(String message) {
        super(message);
    }

}
