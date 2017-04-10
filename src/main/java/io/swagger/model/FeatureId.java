package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FeatureId
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

public class FeatureId   {
  @JsonProperty("feature_id")
  private Integer featureId = null;

  public FeatureId featureId(Integer featureId) {
    this.featureId = featureId;
    return this;
  }

   /**
   * Unique identifier representing a feature
   * @return featureId
  **/
  @ApiModelProperty(value = "Unique identifier representing a feature")
  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeatureId featureId = (FeatureId) o;
    return Objects.equals(this.featureId, featureId.featureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeatureId {\n");
    
    sb.append("    featureId: ").append(toIndentedString(featureId)).append("\n");
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

