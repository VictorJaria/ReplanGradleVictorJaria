package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.joda.time.DateTime;

/**
 * NewFeatureData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class NewFeatureData   {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("effort")
  private BigDecimal effort = null;

  @JsonProperty("deadline")
  private DateTime deadline = null;

  @JsonProperty("priority")
  private Integer priority = null;

  public NewFeatureData code(Integer code) {
    this.code = code;
    return this;
  }

   /**
   * Unique identifier from WP3.3
   * @return code
  **/
  @ApiModelProperty(value = "Unique identifier from WP3.3")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public NewFeatureData name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Display name of feature.
   * @return name
  **/
  @ApiModelProperty(value = "Display name of feature.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewFeatureData description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Description of feature.
   * @return description
  **/
  @ApiModelProperty(value = "Description of feature.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NewFeatureData effort(BigDecimal effort) {
    this.effort = effort;
    return this;
  }

   /**
   * Effort to implement the feature.
   * @return effort
  **/
  @ApiModelProperty(value = "Effort to implement the feature.")
  public BigDecimal getEffort() {
    return effort;
  }

  public void setEffort(BigDecimal effort) {
    this.effort = effort;
  }

  public NewFeatureData deadline(DateTime deadline) {
    this.deadline = deadline;
    return this;
  }

   /**
   * Get deadline
   * @return deadline
  **/
  @ApiModelProperty(value = "")
  public DateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(DateTime deadline) {
    this.deadline = deadline;
  }

  public NewFeatureData priority(Integer priority) {
    this.priority = priority;
    return this;
  }

   /**
   * It coms from Wp3.3
   * @return priority
  **/
  @ApiModelProperty(value = "It coms from Wp3.3")
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewFeatureData newFeatureData = (NewFeatureData) o;
    return Objects.equals(this.code, newFeatureData.code) &&
        Objects.equals(this.name, newFeatureData.name) &&
        Objects.equals(this.description, newFeatureData.description) &&
        Objects.equals(this.effort, newFeatureData.effort) &&
        Objects.equals(this.deadline, newFeatureData.deadline) &&
        Objects.equals(this.priority, newFeatureData.priority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, name, description, effort, deadline, priority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewFeatureData {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    effort: ").append(toIndentedString(effort)).append("\n");
    sb.append("    deadline: ").append(toIndentedString(deadline)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
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

