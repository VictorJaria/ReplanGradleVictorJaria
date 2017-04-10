package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class Project   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("effort_unit")
  private String effortUnit = null;

  @JsonProperty("hours_per_effort_unit")
  private BigDecimal hoursPerEffortUnit = null;

  @JsonProperty("hours_per_week_and_full_time_resource")
  private BigDecimal hoursPerWeekAndFullTimeResource = null;

  @JsonProperty("resources")
  private List<Resource> resources = new ArrayList<Resource>();

  public Project id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String projectId) {
    this.id = projectId;
  }

  public Project name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Display name of project.
   * @return name
  **/
  @ApiModelProperty(value = "Display name of project.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Project description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project effortUnit(String effortUnit) {
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

  public Project hoursPerEffortUnit(BigDecimal hoursPerEffortUnit) {
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

  public Project hoursPerWeekAndFullTimeResource(BigDecimal hoursPerWeekAndFullTimeResource) {
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

  public Project resources(List<Resource> resources) {
    this.resources = resources;
    return this;
  }

  public Project addResourcesItem(Resource resourcesItem) {
    this.resources.add(resourcesItem);
    return this;
  }

   /**
   * Get resources
   * @return resources
  **/
  @ApiModelProperty(value = "")
  public List<Resource> getResources() {
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(this.id, project.id) &&
        Objects.equals(this.name, project.name) &&
        Objects.equals(this.description, project.description) &&
        Objects.equals(this.effortUnit, project.effortUnit) &&
        Objects.equals(this.hoursPerEffortUnit, project.hoursPerEffortUnit) &&
        Objects.equals(this.hoursPerWeekAndFullTimeResource, project.hoursPerWeekAndFullTimeResource) &&
        Objects.equals(this.resources, project.resources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, effortUnit, hoursPerEffortUnit, hoursPerWeekAndFullTimeResource, resources);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    effortUnit: ").append(toIndentedString(effortUnit)).append("\n");
    sb.append("    hoursPerEffortUnit: ").append(toIndentedString(hoursPerEffortUnit)).append("\n");
    sb.append("    hoursPerWeekAndFullTimeResource: ").append(toIndentedString(hoursPerWeekAndFullTimeResource)).append("\n");
    sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
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

