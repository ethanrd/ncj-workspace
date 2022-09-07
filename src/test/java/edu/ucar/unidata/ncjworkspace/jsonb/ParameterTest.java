package edu.ucar.unidata.ncjworkspace.jsonb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableMap;
import edu.ucar.unidata.ncjworkspace.jsonb.Collection.Builder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ParameterTest {
  @Test
  public void testParseJsonFromText() throws JsonProcessingException {
    String json = "{"
        + "  \"type\" : \"a param type\","
        + "  \"description\" : \"a param desc\","
        + "  \"unit\" : \"a param unit\","
        + "  \"observedProperty\" : \"a param obsProp\""
        + "}";
    Parameter parameter = new ObjectMapper().readValue(json, Parameter.class);

    assertTrue( parameter.isValid(), parameter.getValidationLog());
    assertEquals("a param type", parameter.getType());
    assertEquals("a param desc", parameter.getDescription());
    assertEquals( "a param unit", parameter.getUnit());
    assertEquals( "a param obsProp", parameter.getObservedProperty());
  }

  @Test
  public void testParseJsonFromTextNoObsProp() throws JsonProcessingException {
    String json = "{"
        + "  \"type\" : \"a param type\","
        + "  \"description\" : \"a param desc\","
        + "  \"unit\" : \"a param unit\""
        + "}";
    Parameter parameter = new ObjectMapper().readValue(json, Parameter.class);

    assertEquals("a param type", parameter.getType());
    assertEquals("a param desc", parameter.getDescription());
    assertEquals( "a param unit", parameter.getUnit());
    assertNull(parameter.getObservedProperty());

    assertFalse( parameter.isValid(), "Invalid parameter mistakenly validated: " + parameter.getValidationLog());
    System.out.println(parameter.getValidationLog());
  }

  @Test
  public void testParseJsonFromTextEmptyObsProp() throws JsonProcessingException {
    String json = "{"
        + "  \"type\" : \"a param type\","
        + "  \"description\" : \"a param desc\","
        + "  \"unit\" : \"a param unit\","
        + "  \"observedProperty\" : \"\""
        + "}";
    Parameter parameter = new ObjectMapper().readValue(json, Parameter.class);

    assertEquals("a param type", parameter.getType());
    assertEquals("a param desc", parameter.getDescription());
    assertEquals( "a param unit", parameter.getUnit());
    String op = parameter.getObservedProperty();
    assertTrue(op.isBlank(), String.format("Blank obs prop isn't: %s", op));

    assertFalse( parameter.isValid(), "Invalid parameter mistakenly validated:" + parameter.getValidationLog());
    System.out.println(parameter.getValidationLog());
  }
}
