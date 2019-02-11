package uk.gov.hmcts.ccd.definition.designer.rest.endpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.ccd.definition.designer.domain.exception.NotFoundException;
import uk.gov.hmcts.ccd.definition.designer.rest.endpoint.exceptions.DuplicateFoundException;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

class ControllerExceptionHandlerTest {

    private static final String INNER_MESSAGE = "Exception message";
    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Nested
    @DisplayName("objectNotFound()")
    class ObjectNotFound {
        @Test
        @DisplayName("should return error details as map")
        void shouldReturnErrorDetailsAsMap() {
            final Map<String, String> details = handler.objectNotFound(new NotFoundException(INNER_MESSAGE));

            assertAll(
                () -> assertThat(details, is(notNullValue())),
                () -> assertThat(details.get("message"), equalTo("Object Not Found for:" + INNER_MESSAGE))
            );
        }
    }

    @Nested
    @DisplayName("objectFound()")
    class ObjectFound {
        @Test
        @DisplayName("should return error details as map")
        void shouldReturnErrorDetailsAsMap() {
            final Map<String, String> details = handler.objectFound(new DuplicateFoundException(INNER_MESSAGE));

            assertAll(
                () -> assertThat(details, is(notNullValue())),
                () -> assertThat(details.get("message"), equalTo("Object already exists for:" + INNER_MESSAGE))
            );
        }
    }

    @Nested
    @DisplayName("generalIllegal()")
    class GeneralIllegal {
        @Test
        @DisplayName("should return error details as map")
        void shouldReturnErrorDetailsAsMap() {
            final Map<String, String> details = handler.generalIllegal(new IllegalArgumentException(INNER_MESSAGE));

            assertAll(
                () -> assertThat(details, is(notNullValue())),
                () -> assertThat(details.get("message"), equalTo("Illegal Input:" + INNER_MESSAGE))
            );
        }
    }

    @Nested
    @DisplayName("handle IO Exception")
    class HandleIoExceptionCase {
        @Test
        @DisplayName("should return error details as map")
        void shouldReturnErrorDetailsAsMap() {
            final Map<String, String> details = handler.handleIOException(new IOException(INNER_MESSAGE));

            assertAll(
                () -> assertThat(details, is(notNullValue())),
                () -> assertThat(details.get("message"), equalTo("Unexpected Error: " + INNER_MESSAGE))
            );
        }
    }
}
