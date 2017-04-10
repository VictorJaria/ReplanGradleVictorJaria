package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Job;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

/**
 * Plan
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class Plan   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("created_at")
  private LocalDate createdAt = null;

  @JsonProperty("release_id")
  private Integer releaseId = null;

  @JsonProperty("jobs")
  private List<Job> jobs = new ArrayList<Job>();

  public Plan id(Integer id) {
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

  public Plan createdAt(LocalDate createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")
  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public Plan releaseId(Integer releaseId) {
    this.releaseId = releaseId;
    return this;
  }

   /**
   * Get releaseId
   * @return releaseId
  **/
  @ApiModelProperty(value = "")
  public Integer getReleaseId() {
    return releaseId;
  }

  public void setReleaseId(Integer releaseId) {
    this.releaseId = releaseId;
  }

  public Plan jobs(List<Job> jobs) {
    this.jobs = jobs;
    return this;
  }

  public Plan addJobsItem(Job jobsItem) {
    this.jobs.add(jobsItem);
    return this;
  }

   /**
   * array of jobs
   * @return jobs
  **/
  @ApiModelProperty(value = "array of jobs")
  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Plan plan = (Plan) o;
    return Objects.equals(this.id, plan.id) &&
        Objects.equals(this.createdAt, plan.createdAt) &&
        Objects.equals(this.releaseId, plan.releaseId) &&
        Objects.equals(this.jobs, plan.jobs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, releaseId, jobs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Plan {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    releaseId: ").append(toIndentedString(releaseId)).append("\n");
    sb.append("    jobs: ").append(toIndentedString(jobs)).append("\n");
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

