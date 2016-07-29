package com.dragon.study.spring.boot.jersey.provider;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * Created by dragon on 16/7/29.
 */
public class UnderlineJsonFeature implements Feature {

  @Override
  public boolean configure(FeatureContext context) {
    context.register(UnderlineJsonProvider.class, MessageBodyReader.class,
        MessageBodyWriter.class);
    return true;
  }
}
