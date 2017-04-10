package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Feature;
import io.swagger.model.Skill;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Feature
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class Feature   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("effort")
  private BigDecimal effort = null;

  @JsonProperty("deadline")
  private Date deadline = null;

  @JsonProperty("priority")
  private Integer priority = null;

  @JsonProperty("required_skills")
  private List<Skill> requiredSkills = new ArrayList<Skill>();

  @JsonProperty("depends_on")
  private List<Feature> dependsOn = new ArrayList<Feature>();

  public Feature id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier representing a feature
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier representing a feature")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Feature code(Integer code) {
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

  public Feature name(String name) {
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

  public Feature description(String description) {
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

  public Feature effort(BigDecimal effort) {
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

  public Feature deadline(Date deadline) {
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

  public void setDeadline(Date dateline) {
    this.deadline = dateline;
  }

  public Feature priority(Integer priority) {
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

  public Feature requiredSkills(List<Skill> requiredSkills) {
    this.requiredSkills = requiredSkills;
    return this;
  }

  public Feature addRequiredSkillsItem(Skill requiredSkillsItem) {
    this.requiredSkills.add(requiredSkillsItem);
    return this;
  }

   /**
   * Get requiredSkills
   * @return requiredSkills
  **/
  @ApiModelProperty(value = "")
  public List<Skill> getRequiredSkills() {
    return requiredSkills;
  }

  public void setRequiredSkills(List<Skill> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  public Feature dependsOn(List<Feature> dependsOn) {
    this.dependsOn = dependsOn;
    return this;
  }

  public Feature addDependsOnItem(Feature dependsOnItem) {
    this.dependsOn.add(dependsOnItem);
    return this;
  }

   /**
   * array of features
   * @return dependsOn
  **/
  @ApiModelProperty(value = "array of features")
  public List<Feature> getDependsOn() {
    return dependsOn;
  }

  public void setDependsOn(List<Feature> dependsOn) {
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
    Feature feature = (Feature) o;
    return Objects.equals(this.id, feature.id) &&
        Objects.equals(this.code, feature.code) &&
        Objects.equals(this.name, feature.name) &&
        Objects.equals(this.description, feature.description) &&
        Objects.equals(this.effort, feature.effort) &&
        Objects.equals(this.deadline, feature.deadline) &&
        Objects.equals(this.priority, feature.priority) &&
        Objects.equals(this.requiredSkills, feature.requiredSkills) &&
        Objects.equals(this.dependsOn, feature.dependsOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, name, description, effort, deadline, priority, requiredSkills, dependsOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Feature {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    effort: ").append(toIndentedString(effort)).append("\n");
    sb.append("    deadline: ").append(toIndentedString(deadline)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    requiredSkills: ").append(toIndentedString(requiredSkills)).append("\n");
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

