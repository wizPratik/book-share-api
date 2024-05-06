package com.book_share.api.exchange_request.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.book_share.api.exchange_request.service.ExchangeRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the id value isn't taken yet.
 */
@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = ExchangeRequestUserBookUnique.ExchangeRequestUserBookUniqueValidator.class
)
public @interface ExchangeRequestUserBookUnique {

    String message() default "{Exists.exchangeRequest.userBook}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    final class ExchangeRequestUserBookUniqueValidator implements
        ConstraintValidator<ExchangeRequestUserBookUnique, Long> {

        private final ExchangeRequestService exchangeRequestService;
        private final HttpServletRequest request;

        ExchangeRequestUserBookUniqueValidator(
            final ExchangeRequestService exchangeRequestService1,
            final HttpServletRequest request1) {
            this.exchangeRequestService = exchangeRequestService1;
            this.request = request1;
        }

        @Override
        public boolean isValid(final Long value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(
                exchangeRequestService.get(Long.parseLong(currentId)).getUserBook())) {
                // value hasn't changed
                return true;
            }
            return !exchangeRequestService.userBookExists(value);
        }

    }

}
