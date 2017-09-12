
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
    "ncgId",
    "remoteSystemAlias",
    "remoteEntityId",
    "title",
    "slug",
    "attributes",
    "subtitlesLanguageInfo",
    "audioLanguageInfo",
    "rating"
})
public class Version {

    @JsonProperty("ncgId")
    private String ncgId;
    @JsonProperty("remoteSystemAlias")
    private String remoteSystemAlias;
    @JsonProperty("remoteEntityId")
    private String remoteEntityId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("attributes")
    private List<Attribute> attributes = null;
    @JsonProperty("subtitlesLanguageInfo")
    private Object subtitlesLanguageInfo;
    @JsonProperty("audioLanguageInfo")
    private AudioLanguageInfo audioLanguageInfo;
    @JsonProperty("rating")
    private Rating rating;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ncgId")
    public String getNcgId() {
        return ncgId;
    }

    @JsonProperty("ncgId")
    public void setNcgId(String ncgId) {
        this.ncgId = ncgId;
    }

    @JsonProperty("remoteSystemAlias")
    public String getRemoteSystemAlias() {
        return remoteSystemAlias;
    }

    @JsonProperty("remoteSystemAlias")
    public void setRemoteSystemAlias(String remoteSystemAlias) {
        this.remoteSystemAlias = remoteSystemAlias;
    }

    @JsonProperty("remoteEntityId")
    public String getRemoteEntityId() {
        return remoteEntityId;
    }

    @JsonProperty("remoteEntityId")
    public void setRemoteEntityId(String remoteEntityId) {
        this.remoteEntityId = remoteEntityId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("attributes")
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("subtitlesLanguageInfo")
    public Object getSubtitlesLanguageInfo() {
        return subtitlesLanguageInfo;
    }

    @JsonProperty("subtitlesLanguageInfo")
    public void setSubtitlesLanguageInfo(Object subtitlesLanguageInfo) {
        this.subtitlesLanguageInfo = subtitlesLanguageInfo;
    }

    @JsonProperty("audioLanguageInfo")
    public AudioLanguageInfo getAudioLanguageInfo() {
        return audioLanguageInfo;
    }

    @JsonProperty("audioLanguageInfo")
    public void setAudioLanguageInfo(AudioLanguageInfo audioLanguageInfo) {
        this.audioLanguageInfo = audioLanguageInfo;
    }

    @JsonProperty("rating")
    public Rating getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
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
        return new HashCodeBuilder().append(ncgId).append(remoteSystemAlias).append(remoteEntityId).append(title).append(slug).append(attributes).append(subtitlesLanguageInfo).append(audioLanguageInfo).append(rating).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Version) == false) {
            return false;
        }
        Version rhs = ((Version) other);
        return new EqualsBuilder().append(ncgId, rhs.ncgId).append(remoteSystemAlias, rhs.remoteSystemAlias).append(remoteEntityId, rhs.remoteEntityId).append(title, rhs.title).append(slug, rhs.slug).append(attributes, rhs.attributes).append(subtitlesLanguageInfo, rhs.subtitlesLanguageInfo).append(audioLanguageInfo, rhs.audioLanguageInfo).append(rating, rhs.rating).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
