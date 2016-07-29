package com.dragon.study.spring.boot.jersey.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * Created by dragon on 16/7/29.
 */
@Provider
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class UnderlineJsonProvider implements  MessageBodyWriter<Object> {
  private static final String JSON_ORIG = "json";
  private static final String PLUS_JSON = "+json";

  public final static Charset charset = Charset.forName("UTF-8");

  public static final SerializerFeature[] features = new SerializerFeature[]{
      SerializerFeature.WriteNullBooleanAsFalse,
      SerializerFeature.WriteNullStringAsEmpty,
      SerializerFeature.WriteNullListAsEmpty,
      SerializerFeature.WriteNullNumberAsZero,
      SerializerFeature.SkipTransientField,
      SerializerFeature.DisableCircularReferenceDetect
  };

  private final UnderlineNameFilter underlineNameFilter = new UnderlineNameFilter();

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return Object.class.isAssignableFrom(type) && supportsMediaType(mediaType);
  }

  @Override
  public long getSize(Object o, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return 0;
  }

  @Override
  public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
      throws IOException, WebApplicationException {
    String text;
    if (o == null) {
      text = "{}";
    } else {
      text = JSON.toJSONString(o, underlineNameFilter, features);
    }
    byte[] bytes = text.getBytes(charset);
    entityStream.write(bytes);
  }

  private static boolean supportsMediaType(final MediaType mediaType) {
    return mediaType.getSubtype().equals(JSON_ORIG) || mediaType.getSubtype().endsWith(PLUS_JSON);
  }
}
