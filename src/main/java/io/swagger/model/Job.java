package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Feature;
import io.swagger.model.Job;
import io.swagger.model.Resource;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Job
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class Job   {
  @JsonProperty("starts")
  private DateTime starts = null;

  @JsonProperty("ends")
  private DateTime ends = null;

  @JsonProperty("resource")
  private Resource resource = null;

  @JsonProperty("feature")
  private Feature feature = null;

  @JsonProperty("depends_on")
  private List<Job> dependsOn = new ArrayList<Job>();

  public Job starts(DateTime starts) {
    this.starts = starts;
    return this;
  }

   /**
   * Get starts
   * @return starts
  **/
  @ApiModelProperty(value = "")
  public DateTime getStarts() {
    return starts;
  }

  public void setStarts(DateTime starts) {
    this.starts = starts;
  }

  public Job ends(DateTime ends) {
    this.ends = ends;
    return this;
  }

   /**
   * Get ends
   * @return ends
  **/
  @ApiModelProperty(value = "")
  public DateTime getEnds() {
    return ends;
  }

  public void setEnds(DateTime ends) {
    this.ends = ends;
  }

  public Job resource(Resource resource) {
    this.resource = resource;
    return this;
  }

   /**
   * Get resource
   * @return resource
  **/
  @ApiModelProperty(value = "")
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public Job feature(Feature feature) {
    this.feature = feature;
    return this;
  }

   /**
   * Get feature
   * @return feature
  **/
  @ApiModelProperty(value = "")
  public Feature getFeature() {
    return feature;
  }

  public void setFeature(Feature feature) {
    this.feature = feature;
  }

  public Job dependsOn(List<Job> dependsOn) {
    this.dependsOn = dependsOn;
    return this;
  }

  public Job addDependsOnItem(Job dependsOnItem) {
    this.dependsOn.add(dependsOnItem);
    return this;
  }

   /**
   * array of jobs
   * @return dependsOn
  **/
  @ApiModelProperty(value = "array of jobs")
  public List<Job> getDependsOn() {
    return dependsOn;
  }

  public void setDependsOn(List<Job> dependsOn) {
    this.dependsOn = dependsOn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Job job = (Job) o;
    return Objects.equals(this.starts, job.starts) &&
        Objects.equals(this.ends, job.ends) &&
        Objects.equals(this.resource, job.resource) &&
        Objects.equals(this.feature, job.feature) &&
        Objects.equals(this.dependsOn, job.dependsOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(starts, ends, resource, feature, dependsOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Job {\n");
    
    sb.append("    starts: ").append(toIndentedString(starts)).append("\n");
    sb.append("    ends: ").append(toIndentedString(ends)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    feature: ").append(toIndentedString(feature)).append("\n");
    sb.append("    dependsOn: ").append(toIndentedString(dependsOn)).append("\n");
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

