package br.com.YagoPrazim.ApiControleContatos.validations;

import br.com.YagoPrazim.ApiControleContatos.validations.constraints.TipoContato;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoContatoValidation implements ConstraintValidator<TipoContato, Integer> {
    @Override
    public boolean isValid(Integer valor, ConstraintValidatorContext context) {
        if (valor == null) {
            return true;
        }
        return valor == 0 || valor == 1;
    }
}
