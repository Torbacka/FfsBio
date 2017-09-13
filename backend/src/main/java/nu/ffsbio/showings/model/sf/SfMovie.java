
package nu.ffsbio.showings.model.sf;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ncgId",
    "title",
    "releaseDate",
    "rating",
    "posterUrl",
    "badge",
    "isNew",
    "isUpcoming",
    "slug"
})
public class SfMovie {

    @JsonProperty("ncgId")
    private String ncgId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("releaseDate")
    private LocalDateTime releaseDate;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("posterUrl")
    private String posterUrl;
    @JsonProperty("badge")
    private String badge;
    @JsonProperty("isNew")
    private Boolean isNew;
    @JsonProperty("isUpcoming")
    private Boolean isUpcoming;
    @JsonProperty("slug")
    private String slug;

    protected SfMovie() {
    }

    public String getNcgId() {
        return ncgId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public Rating getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBadge() {
        return badge;
    }

    public Boolean getNew() {
        return isNew;
    }

    public Boolean getUpcoming() {
        return isUpcoming;
    }

    public String getSlug() {
        return slug;
    }
}
