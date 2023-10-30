package br.com.YagoPrazim.ApiControleContatos.validations.constraints;

import br.com.YagoPrazim.ApiControleContatos.validations.TipoContatoValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoContatoValidation.class)
public @interface TipoContato {
    String message() default "Digite '0' (telefone) ou '1' (celular)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
