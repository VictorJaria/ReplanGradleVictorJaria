package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * SkillId
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class SkillId   {
  @JsonProperty("skill_id")
  private Integer skillId = null;

  public SkillId skillId(Integer skillId) {
    this.skillId = skillId;
    return this;
  }

   /**
   * Unique identifier representing a skill
   * @return skillId
  **/
  @ApiModelProperty(value = "Unique identifier representing a skill")
  public Integer getSkillId() {
    return skillId;
  }

  public void setSkillId(Integer skillId) {
    this.skillId = skillId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillId skillId = (SkillId) o;
    return Objects.equals(this.skillId, skillId.skillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skillId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SkillId {\n");
    
    sb.append("    skillId: ").append(toIndentedString(skillId)).append("\n");
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

