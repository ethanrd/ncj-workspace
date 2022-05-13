package edu.ucar.unidata.ncjworkspace.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Extent.Builder.class)
@JsonInclude(Include.NON_NULL)
public class Extent {
  public final ExtentSpatial spatial;
  public final String temporal;
  public final String vertical;

  public Extent(Builder builder) {
    this.spatial = builder.extentSpatialBuilder.build();
    this.temporal = builder.temporal;
    this.vertical = builder.vertical;
  }

  public ExtentSpatial getSpatial() { return this.spatial; }
  public String getTemporal() { return this.temporal; }
  public String getVertical() { return this.vertical; }

  @JsonPOJOBuilder
  public static class Builder {
    public ExtentSpatial.Builder extentSpatialBuilder;
    public String temporal;
    public String vertical;

    public Builder() {}

    public Builder withSpatial(ExtentSpatial.Builder extentSpatialBuilder) {
      this.extentSpatialBuilder = extentSpatialBuilder;
      return this;
    }

    public Builder withTemporal(String temporal) {
      this.temporal = temporal;
      return this;
    }

    public Builder withVertical(String vertical) {
      this.vertical = vertical;
      return this;
    }

    public Extent build() {
      Extent extent = new Extent( this);
      validateExtentObject( extent);
      return extent;
    }

    private void validateExtentBuilder(Extent.Builder extentBuilder) {
      //Do some basic validations to check
      //if user object does not break any assumption of system
    }
    private void validateExtentObject(Extent extent) {
      //Do some basic validations to check
      //if user object does not break any assumption of system
    }
  }
}
