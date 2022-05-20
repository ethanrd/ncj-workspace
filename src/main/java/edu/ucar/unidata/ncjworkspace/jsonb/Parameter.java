package edu.ucar.unidata.ncjworkspace.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Formatter;
import javax.annotation.Nonnull;

@JsonDeserialize(builder = Parameter.Builder.class)
@JsonInclude(Include.NON_NULL)
public class Parameter {
  private final String type; // REQUIRED
  private final String description;
  private final String unit;
  private final String observedProperty; // REQUIRED
  private final boolean isValid;
  private final Formatter validationLog;

  private Parameter(Builder builder, boolean isValid, Formatter validationLog) {
    this.isValid = isValid;
    this.validationLog = validationLog;

    this.type = builder.type;
    this.description = builder.description;
    this.unit = builder.unit;
    this.observedProperty = builder.observedProperty;
  }

  public boolean isValid() { return this.isValid; }
  public String getValidationLog() {
    return this.validationLog.toString();
  }

  public String getType() { return this.type; }
  public String getDescription() { return this.description; }
  public String getUnit() { return this.unit; }
  public String getObservedProperty() { return this.observedProperty; }

  @JsonPOJOBuilder
  public static class Builder {
    public String type; // = "Parameter";
    public String description;
    public String unit;
    public String observedProperty;

    public Builder() {}
//    public Builder(
//        @JsonProperty("type") String type,
//        @JsonProperty("observedProperty") String observedProperty) {
//    this.type = type;
//    this.observedProperty = observedProperty;
//    }

    @JsonProperty(value = "type", required = true)
    public Builder withType(String type) {
      this.type = type;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withUnit(String unit) {
      this.unit = unit;
      return this;
    }

    @JsonProperty(value = "observedProperty", required = true)
    public Builder withObservedProperty(String observedProperty) {
      this.observedProperty = observedProperty;
      return this;
    }

    public String getType() { return this.type; }
    public String getDescription() { return this.description; }
    public String getUnit() { return this.unit; }
    public String getObservedProperty() { return this.observedProperty; }

    public Parameter build() {
      return this.build(new Formatter());
    }

    public Parameter build(@Nonnull Formatter validationLog) {
      Parameter parameter = new Parameter( this, isValidParameterBuilder(this, validationLog), validationLog);
      return parameter;
    }

    public boolean isValid(@Nonnull Formatter validationLog) {
      return isValidParameterBuilder(this, validationLog);
    }

    private boolean isValidParameterBuilder(@Nonnull Parameter.Builder parameterBuilder, @Nonnull Formatter validationLog) {
      if ( isValidParameterType(parameterBuilder.getType(), validationLog)
          && isValidParameterObsProp(parameterBuilder.getObservedProperty(), validationLog))
        return true;
      return false;
    }

    private boolean isValidParameterType( String type, Formatter validationLog) {
      if (type != null && ! type.isBlank() )
        return true;
      validationLog.format("Parameter must have a type.");
      return false;
    }

    private boolean isValidParameterObsProp( String type, Formatter validationLog) {
      if (type != null && ! type.isBlank() )
        return true;
      validationLog.format("Parameter must have a type.");
      return false;
    }
  }

}
