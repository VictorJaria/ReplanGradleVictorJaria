package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * ProjectData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class ProjectData   {
  @JsonProperty("effort_unit")
  private String effortUnit = null;

  @JsonProperty("hours_per_effort_unit")
  private BigDecimal hoursPerEffortUnit = null;

  @JsonProperty("hours_per_week_and_full_time_resource")
  private BigDecimal hoursPerWeekAndFullTimeResource = null;

  public ProjectData effortUnit(String effortUnit) {
    this.effortUnit = effortUnit;
    return this;
  }

   /**
   * e.g. \"bin\"
   * @return effortUnit
  **/
  @ApiModelProperty(value = "e.g. \"bin\"")
  public String getEffortUnit() {
    return effortUnit;
  }

  public void setEffortUnit(String effortUnit) {
    this.effortUnit = effortUnit;
  }

  public ProjectData hoursPerEffortUnit(BigDecimal hoursPerEffortUnit) {
    this.hoursPerEffortUnit = hoursPerEffortUnit;
    return this;
  }

   /**
   * Get hoursPerEffortUnit
   * @return hoursPerEffortUnit
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getHoursPerEffortUnit() {
    return hoursPerEffortUnit;
  }

  public void setHoursPerEffortUnit(BigDecimal hoursPerEffortUnit) {
    this.hoursPerEffortUnit = hoursPerEffortUnit;
  }

  public ProjectData hoursPerWeekAndFullTimeResource(BigDecimal hoursPerWeekAndFullTimeResource) {
    this.hoursPerWeekAndFullTimeResource = hoursPerWeekAndFullTimeResource;
    return this;
  }

   /**
   * Get hoursPerWeekAndFullTimeResource
   * @return hoursPerWeekAndFullTimeResource
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getHoursPerWeekAndFullTimeResource() {
    return hoursPerWeekAndFullTimeResource;
  }

  public void setHoursPerWeekAndFullTimeResource(BigDecimal hoursPerWeekAndFullTimeResource) {
    this.hoursPerWeekAndFullTimeResource = hoursPerWeekAndFullTimeResource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectData projectData = (ProjectData) o;
    return Objects.equals(this.effortUnit, projectData.effortUnit) &&
        Objects.equals(this.hoursPerEffortUnit, projectData.hoursPerEffortUnit) &&
        Objects.equals(this.hoursPerWeekAndFullTimeResource, projectData.hoursPerWeekAndFullTimeResource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(effortUnit, hoursPerEffortUnit, hoursPerWeekAndFullTimeResource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectData {\n");
    
    sb.append("    effortUnit: ").append(toIndentedString(effortUnit)).append("\n");
    sb.append("    hoursPerEffortUnit: ").append(toIndentedString(hoursPerEffortUnit)).append("\n");
    sb.append("    hoursPerWeekAndFullTimeResource: ").append(toIndentedString(hoursPerWeekAndFullTimeResource)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

