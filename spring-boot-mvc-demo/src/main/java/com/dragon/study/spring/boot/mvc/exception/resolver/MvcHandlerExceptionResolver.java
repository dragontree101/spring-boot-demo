package com.dragon.study.spring.boot.mvc.exception.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.dragon.study.spring.boot.mvc.exception.common.MvcException;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.AbstractJackson2View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dragon on 16/7/12.
 */
public class MvcHandlerExceptionResolver extends AbstractHandlerExceptionResolver {


  @Override
  protected ModelAndView doResolveException(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) {
    if (e instanceof MvcException) {
      MvcExceptionModel model = ((MvcException) e).getExceptionFactor().getExModel();
      ModelAndView mv = new ModelAndView();
      MappingJackson2JsonView view = new MappingJackson2JsonView();
      view.setExtractValueFromSingleKeyModel(true);
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("response", model);
      view.setAttributesMap(attributes);
      httpServletResponse.setStatus(model.getHttpCode());
      mv.setView(view);
      return mv;

    }
    return null;
  }
}
