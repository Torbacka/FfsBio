
package nu.ffsbio.showings.model.sf;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "displayName",
    "alias",
    "age",
    "ageAccompanied"
})
public class Rating {

    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("ageAccompanied")
    private Integer ageAccompanied;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("ageAccompanied")
    public Integer getAgeAccompanied() {
        return ageAccompanied;
    }

    @JsonProperty("ageAccompanied")
    public void setAgeAccompanied(Integer ageAccompanied) {
        this.ageAccompanied = ageAccompanied;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(displayName).append(alias).append(age).append(ageAccompanied).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rating) == false) {
            return false;
        }
        Rating rhs = ((Rating) other);
        return new EqualsBuilder().append(displayName, rhs.displayName).append(alias, rhs.alias).append(age, rhs.age).append(ageAccompanied, rhs.ageAccompanied).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
