package edu.ucar.unidata.ncjworkspace.jsonb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

public class CollectionTest {

  @Test
  public void testParseJsonFromText() throws JsonProcessingException {
    String json = "{"
        + "\"id\" : \"anID\","
        + "\"title\" : \"a title\","
        + "\"description\" : \"a description\","
        + "\"keywords\" : ["
        + "  \"a keyword\","
        + "  \"b key\""
        + "],"
        + "\"extent\" : {"
        + "  \"spatial\" : \"a spat ext\","
        + "  \"vertical\" : \"a vert ext\""
        + "},"
        + "\"parameter_names\" : {"
        + "  \"temperature\" : {"
        + "    \"type\" : \"a param type\","
        + "    \"description\" : \"a param desc\","
        + "    \"unit\" : \"a param unit\","
        + "    \"observedProperty\" : \"a param obsProp\""
        + "  },"
        + "  \"pressure\" : {"
        + "    \"type\" : \"b param type\","
        + "    \"description\" : \"b param desc\","
        + "    \"unit\" : \"b param unit\","
        + "    \"observedProperty\" : \"b param obsProp\""
        + "  }"
        + "}"
        + "}";
    Collection collection = new ObjectMapper()
        .readValue(json, Collection.class);

    assertEquals("anID", collection.getId());
    assertEquals("a title", collection.getTitle());
    assertEquals("a description", collection.getDescription());
    assertEquals( "a keyword", collection.getKeywords().get(0));
    assertEquals( "b key", collection.getKeywords().get(1));
    assertEquals( "a spat ext", collection.getExtent().getSpatial());
    assertEquals( "a vert ext", collection.getExtent().getVertical());
    assertEquals( 2, collection.getParameterNames().size());
    assertEquals( "a param type", collection.getParameterNames().get("temperature").getType());
    assertEquals( "b param obsProp", collection.getParameterNames().get("pressure").getObservedProperty());
  }

  @Test
  public void testParseJsonFromFile() throws IOException {
    Collection collection = new ObjectMapper()
        .readValue(new File("src/test/data/jsonb/collection.json"), Collection.class);

    assertEquals("anID", collection.getId());
    assertEquals("a title", collection.getTitle());
    assertEquals("a description", collection.getDescription());
    assertEquals( 2, collection.getKeywords().size());
    assertEquals( "word", collection.getKeywords().get(0));
    assertEquals( "a phrase", collection.getKeywords().get(1));
    assertEquals( "a spatial ext", collection.getExtent().getSpatial());
    assertEquals( "a temporal ext", collection.getExtent().getTemporal());
    assertEquals( 2, collection.getParameterNames().size());
    Parameter tempParam = collection.getParameterNames().get("temperature");
    assertNotNull( tempParam);
    assertEquals( "Parameter", tempParam.getType());
    assertEquals( "the temperature", tempParam.getDescription());
    assertEquals( "degree_K", tempParam.getUnit());
    assertEquals( "temperature (obsProp)", tempParam.getObservedProperty());
    Parameter presParam = collection.getParameterNames().get("pressure");
    assertNotNull( presParam);
    assertEquals( "Parameter - b", presParam.getType());
    assertEquals( "b param desc", presParam.getDescription());
    assertEquals( "b param unit", presParam.getUnit());
    assertEquals( "b param obsProp", presParam.getObservedProperty());
  }

  @Test
  public void testWriteJson() throws IOException {
    ExtentSpatial.Builder extentSpatialBuilder = new ExtentSpatial.Builder().withBbox("0,0,1,1").withCrs("WGS84");
    Extent.Builder extentBuilder = new Extent.Builder().withSpatial( extentSpatialBuilder ).withTemporal("a temporal ext");
    Map<String,Parameter.Builder> parameterNamesWithBuilders = new java.util.HashMap<String, Parameter.Builder>();
    Parameter.Builder parameterBuilder = new Parameter.Builder("Parameter", "temperature");
    parameterNamesWithBuilders.put( "temp", parameterBuilder);
    Collection.Builder collectionBuilder = new Collection.Builder().withId("anID").withExtent(extentBuilder).withParameterNames(parameterNamesWithBuilders);
    List<String> keywords = new ArrayList<>();
    keywords.add("word");
    keywords.add("a phrase");
    collectionBuilder.withKeywords( keywords);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.writeValue(new File("src/test/data/jsonb/collection-write.json"), collectionBuilder.build());
  }
}
