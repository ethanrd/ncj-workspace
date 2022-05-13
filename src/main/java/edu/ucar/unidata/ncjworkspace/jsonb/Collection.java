package edu.ucar.unidata.ncjworkspace.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Formatter;
import javax.validation.constraints.NotNull;
import com.google.common.collect.ImmutableMap;
import edu.ucar.unidata.ncjworkspace.jsonb.Parameter.Builder;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = Collection.Builder.class)
@JsonInclude(Include.NON_NULL)
public class Collection {
  @NotNull
  private final String id; // REQUIRED
  private final String title;
  private final String description;
  private final List<String> keywords;
  @NotNull
  private final Extent extent;  // REQUIRED
  //@JsonProperty("parameter_names")
  private final Map<String, Parameter> parameterNames;  // REQUIRED
  //private final String cvs; //???
  //@JsonProperty("output_formats")
  //private final List<String> outputFormats;  // REQUIRED
  //@JsonProperty("links")
  //private final Links links;
  //@JsonProperty("data_queries")
  //private final DataQueries dataQueries;

  public String getId() { return this.id; }
  public String getTitle() { return this.title; }
  public String getDescription() { return this.description; }
  public List<String> getKeywords() { return this.keywords; }
  public Extent getExtent() { return this.extent; }
  @JsonProperty("parameter_names")
  public Map<String,Parameter> getParameterNames() { return this.parameterNames; }

  private Collection( Builder builder ) {
    this.id = builder.id;
    this.title = builder.title;
    this.description = builder.description;
    this.keywords = builder.keywords;
    this.extent = builder.extentBuilder.build();

    ImmutableMap.Builder<String,Parameter> mapBuilder = ImmutableMap.<String,Parameter>builder();
    for (String key : builder.parameterNamesBuilder.keySet()) {
      mapBuilder.put(key, builder.parameterNamesBuilder.get(key).build());
    }
    this.parameterNames = mapBuilder.build();
  }

  @JsonPOJOBuilder
  public static class Builder {
    private String id;
    private String title;
    private String description;
    private List<String> keywords;
    private Extent.Builder extentBuilder;
    private Map<String, Parameter.Builder> parameterNamesBuilder;

    public Builder() {
      //   @JsonProperty("id") String id, @JsonProperty("extent") Extent.Builder extentBuilder,
      //   @JsonProperty("parameter_names") Map<String, Parameter.Builder> parameterNamesBuilders) {
    }

    @JsonProperty("id")
    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    @JsonProperty("extent")
    public Builder withExtent(Extent.Builder extentBuilder) {
      this.extentBuilder = extentBuilder;
      return this;
    }

    @JsonProperty("parameter_names")
    public Builder withParameterNames(Map<String, Parameter.Builder> parameterNamesBuilders) {
      this.parameterNamesBuilder = parameterNamesBuilders;
      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withKeywords(List<String> keywords) {
      this.keywords = List.copyOf(keywords);
      return this;
    }

    public String getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public List<String> getKeywords() { return this.keywords; }
    public Extent.Builder getExtent() { return this.extentBuilder; }
    @JsonProperty("parameter_names")
    public Map<String,Parameter.Builder> getParameterNames() { return this.parameterNamesBuilder; }

    public Collection build() {
      Formatter validationLog = new Formatter();
      validateCollectionBuilder( this, validationLog);
      Collection collection = new Collection( this);
      return collection;
    }

    private void validateCollectionBuilder(Collection.Builder collectionBuilder, Formatter validationLog) {
      validateCollectionID(collectionBuilder.getId(), validationLog);
      //Do some basic validations to check
      //if user object does not break any assumption of system
    }

    private boolean validateCollectionID( String id, Formatter validationLog) {
      if (id == null )
        validationLog.format("Collection must have an ID.");
      else if (! id.isBlank())
        validationLog.format("Collection must have an ID.");
      else
        return true;
      return false;
    }

//    private boolean validateCollectionExtentBuilder( Extent.Builder extentBuilder, Formatter validationLog ) {
//      extentBuilder.
//    }
  }
}
