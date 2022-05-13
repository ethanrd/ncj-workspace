package edu.ucar.unidata.ncjworkspace.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Parameter.Builder.class)
@JsonInclude(Include.NON_NULL)
public class Parameter {
  public final String type; // REQUIRED
  public final String description;
  public final String unit;
  public final String observedProperty; // REQUIRED

  public Parameter(Builder builder) {
    this.type = builder.type;
    this.description = builder.description;
    this.unit = builder.unit;
    this.observedProperty = builder.observedProperty;
  }

  public String getType() { return this.type; }
  public String getDescription() { return this.description; }
  public String getUnit() { return this.unit; }
  public String getObservedProperty() { return this.observedProperty; }

  @JsonPOJOBuilder
  public static class Builder {
    public final String type; // = "Parameter";
    public String description;
    public String unit;
    public final String observedProperty;

    public Builder(@JsonProperty("type") String type, @JsonProperty("observedProperty") String observedProperty) {
      this.type = type;
      this.observedProperty = observedProperty;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withUnit(String unit) {
      this.unit = unit;
      return this;
    }

    public String getType() { return this.type; }
    public String getDescription() { return this.description; }
    public String getUnit() { return this.unit; }
    public String getObservedProperty() { return this.observedProperty; }

    public Parameter build() {
      Parameter parameter = new Parameter( this);
      validateExtentObject( parameter);
      return parameter;
    }

    private void validateExtentObject(Parameter parameter) {
      //Do some basic validations to check
      //if user object does not break any assumption of system
    }
  }

}
