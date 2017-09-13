package nu.ffsbio.showings.model.internal;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import nu.ffsbio.showings.model.sf.SfMovie;

@Entity
public class Movie {
    @Id
    @JsonProperty
    private String ncgId;
    @JsonProperty
    private String slug;
    @JsonProperty
    private String title;
    @JsonProperty
    private String ratingImdb;
    @JsonProperty
    private String ratingRottenTomatoes;
    @JsonProperty
    private LocalDateTime releaseDate;

    protected Movie() {
    }

    public Movie(SfMovie movie) {
        this.ncgId = movie.getNcgId();
        this.slug = movie.getSlug();
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
    }

    public String getNcgId() {
        return ncgId;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getRatingImdb() {
        return ratingImdb;
    }

    public String getRatingRottenTomatoes() {
        return ratingRottenTomatoes;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
}
