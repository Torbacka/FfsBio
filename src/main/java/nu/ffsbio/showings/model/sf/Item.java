
package nu.ffsbio.showings.model.sf;

import java.time.LocalDateTime;
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
    "versions",
    "ncgId",
    "title",
    "slug",
    "releaseDate",
    "rating",
    "posterUrl",
    "images",
    "videoStreams"
})
public class Item {

    @JsonProperty("versions")
    private List<Version> versions = null;
    @JsonProperty("ncgId")
    private String ncgId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("releaseDate")
    private LocalDateTime releaseDate;
    @JsonProperty("rating")
    private Rating_ rating;
    @JsonProperty("posterUrl")
    private String posterUrl;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("videoStreams")
    private List<VideoStream> videoStreams = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("versions")
    public List<Version> getVersions() {
        return versions;
    }

    @JsonProperty("versions")
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @JsonProperty("ncgId")
    public String getNcgId() {
        return ncgId;
    }

    @JsonProperty("ncgId")
    public void setNcgId(String ncgId) {
        this.ncgId = ncgId;
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

    @JsonProperty("releaseDate")
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("rating")
    public Rating_ getRating() {
        return rating;
    }

    @JsonProperty("posterUrl")
    public String getPosterUrl() {
        return posterUrl;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("videoStreams")
    public List<VideoStream> getVideoStreams() {
        return videoStreams;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(versions).append(ncgId).append(title).append(slug).append(releaseDate).append(rating).append(posterUrl).append(images).append(videoStreams).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(versions, rhs.versions).append(ncgId, rhs.ncgId).append(title, rhs.title).append(slug, rhs.slug).append(releaseDate, rhs.releaseDate).append(rating, rhs.rating).append(posterUrl, rhs.posterUrl).append(images, rhs.images).append(videoStreams, rhs.videoStreams).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
