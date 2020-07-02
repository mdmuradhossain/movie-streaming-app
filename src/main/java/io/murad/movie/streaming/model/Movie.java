package io.murad.movie.streaming.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "featured_image", nullable = true)
	private String thumbnail;

//	public byte[] getImageFile() {
//		return imageFile;
//	}
//
//	public void setImageFile(byte[] imageFile) {
//		this.imageFile = imageFile;
//	}
//
//	@Lob
//	@Column(name = "image_file",length = Integer.MAX_VALUE,nullable = true)
//	private byte[] imageFile;
	@Column(name = "video_name",nullable = true)
	private String video;
	// private date releaseYear;
	// private string directorName;

	@Column(name = "imdb_rating")
	private String imdbRating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@Column(name = "lang")
	private String language;

	@DateTimeFormat
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@DateTimeFormat
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Movie() {

	}

	public Movie(Long id, String title, String description, String imdbRating, String thumbnail, String video,
			Category category, String language, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imdbRating = imdbRating;
		this.thumbnail = thumbnail;
		this.category = category;
		this.language = language;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.video = video;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
