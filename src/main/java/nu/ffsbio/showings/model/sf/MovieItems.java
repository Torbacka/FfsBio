
package nu.ffsbio.showings.model.sf;

import java.util.HashMap;
import java.util.List;
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
    "totalNbrOfItems",
    "items"
})
public class MovieItems {

    @JsonProperty("totalNbrOfItems")
    private Integer totalNbrOfItems;
    @JsonProperty("items")
    private List<Item> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("totalNbrOfItems")
    public Integer getTotalNbrOfItems() {
        return totalNbrOfItems;
    }

    @JsonProperty("totalNbrOfItems")
    public void setTotalNbrOfItems(Integer totalNbrOfItems) {
        this.totalNbrOfItems = totalNbrOfItems;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
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
        return new HashCodeBuilder().append(totalNbrOfItems).append(items).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieItems) == false) {
            return false;
        }
        MovieItems rhs = ((MovieItems) other);
        return new EqualsBuilder().append(totalNbrOfItems, rhs.totalNbrOfItems).append(items, rhs.items).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
