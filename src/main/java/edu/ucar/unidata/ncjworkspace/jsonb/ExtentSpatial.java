package edu.ucar.unidata.ncjworkspace.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Extent.Builder.class)
@JsonInclude(Include.NON_NULL)
public class ExtentSpatial {
  public final String bbox; // REQUIRED
  public final String crs; // REQUIRED
  public final String name;

  public ExtentSpatial(Builder builder) {
    this.bbox = builder.bbox;
    this.crs = builder.crs;
    this.name = builder.name;
  }

  @JsonPOJOBuilder
  public static class Builder {
    public String bbox;
    public String crs;
    public String name;

    public Builder() {}

    public Builder withBbox(String bbox) {
      this.bbox = bbox;
      return this;
    }

    public Builder withCrs(String crs) {
      this.crs = crs;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public ExtentSpatial build() {
      ExtentSpatial extentSpatial = new ExtentSpatial( this);
      validateExtentSpatialObject( extentSpatial);
      return extentSpatial;
    }

    private void validateExtentSpatialObject(ExtentSpatial extentSpatial) {
      //Do some basic validations to check
      //if user object does not break any assumption of system
    }
  }

}
