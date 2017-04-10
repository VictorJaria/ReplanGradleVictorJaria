package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Resource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Release
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class Release   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("starts_at")
  private Date startsAt = null;

  @JsonProperty("deadline")
  private Date deadline = null;

  @JsonProperty("resources")
  private List<Resource> resources = new ArrayList<Resource>();

  public Release id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Release name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Display name of release.
   * @return name
  **/
  @ApiModelProperty(value = "Display name of release.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Release description(String description) {
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

  public Release startsAt(Date startsAt) {
    this.startsAt = startsAt;
    return this;
  }

   /**
   * Get startsAt
   * @return startsAt
  **/
  @ApiModelProperty(value = "")
  public Date getStartsAt() {
    return startsAt;
  }

  public void setStartsAt(Date startsAt) {
    this.startsAt = startsAt;
  }

  public Release deadline(Date deadline) {
    this.deadline = deadline;
    return this;
  }

   /**
   * Get deadline
   * @return deadline
  **/
  @ApiModelProperty(value = "")
  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline2) {
    this.deadline = deadline2;
  }

  public Release resources(List<Resource> resources) {
    this.resources = resources;
    return this;
  }

  public Release addResourcesItem(Resource resourcesItem) {
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
    Release release = (Release) o;
    return Objects.equals(this.id, release.id) &&
        Objects.equals(this.name, release.name) &&
        Objects.equals(this.description, release.description) &&
        Objects.equals(this.startsAt, release.startsAt) &&
        Objects.equals(this.deadline, release.deadline) &&
        Objects.equals(this.resources, release.resources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, startsAt, deadline, resources);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Release {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    startsAt: ").append(toIndentedString(startsAt)).append("\n");
    sb.append("    deadline: ").append(toIndentedString(deadline)).append("\n");
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

