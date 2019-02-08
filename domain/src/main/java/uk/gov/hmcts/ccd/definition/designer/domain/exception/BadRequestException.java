package uk.gov.hmcts.ccd.definition.designer.domain.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
