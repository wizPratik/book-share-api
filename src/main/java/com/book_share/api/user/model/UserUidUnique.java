package com.book_share.api.user.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.book_share.api.user.service.UserService;
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
import java.util.UUID;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the uid value isn't taken yet.
 */
@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = UserUidUnique.UserUidUniqueValidator.class
)
public @interface UserUidUnique {

    String message() default "{Exists.user.uid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    final class UserUidUniqueValidator implements ConstraintValidator<UserUidUnique, UUID> {

        private final UserService userService;
        private final HttpServletRequest request;

        UserUidUniqueValidator(final UserService userService1, final HttpServletRequest request1) {
            this.userService = userService1;
            this.request = request1;
        }

        @Override
        public boolean isValid(final UUID value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(userService.get(Long.parseLong(currentId)).getUid())) {
                // value hasn't changed
                return true;
            }
            return !userService.uidExists(value);
        }

    }

}
