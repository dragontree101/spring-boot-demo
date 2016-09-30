package com.dragon.study.spring.boot.mvc.validator;

import com.dragon.study.spring.boot.mvc.annotation.ParamValidator;
import com.dragon.study.spring.boot.mvc.exception.common.CommonMvcExceptionFactor;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/9/30.
 */
@Slf4j
public class Validator implements ConstraintValidator<ParamValidator, Object> {

  private ParamValidator validator;
  private ParamRange range;

  public Validator() {
  }

  @Override
  public void initialize(final ParamValidator validator) {
    this.validator = validator;
    this.range = RangeFactory.getRangeInstance(validator.range());
  }

  @Override
  public boolean isValid(final Object value,
      final ConstraintValidatorContext constraintValidatorContext) {
    try {
      if(value == null) {
        if (validator.isRequired()) {
          return false;
        } else {
          return true;
        }
      }
      if (this.range != null && !this.range.isInRange(value)) {
        MvcExceptionModel mvcExceptionModel = CommonMvcExceptionFactor.PARAM_ERROR.getExModel();
        mvcExceptionModel.setDetailMsg("Parameter {0} error! range: " + this.range.getDesc());
        return false;
      }
    } catch (Exception e) {
      log.error("valid error", e);
      MvcExceptionModel mvcExceptionModel = CommonMvcExceptionFactor.PARAM_ERROR.getExModel();
      mvcExceptionModel.setDetailMsg("Parameter {0} error! range: " + this.range.getDesc());
      return false;
    }
    return true;
  }

}
