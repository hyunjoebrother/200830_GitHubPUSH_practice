package examples.dto;

public class Movie {
	private Integer Movie_id;
	private String Title;
	private String Genre;
	private Integer Year;
	
	public Movie(Integer movie_id, String title, String genre, Integer year) {
		super();
		Movie_id = movie_id;
		Title = title;
		Genre = genre;
		Year = year;
	}

	public Integer getMovie_id() {
		return Movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		Movie_id = movie_id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public Integer getYear() {
		return Year;
	}
	public void setYear(Integer year) {
		Year = year;
	}
	@Override
	public String toString() {
		return "Movie [Movie_id=" + Movie_id + ", Title=" + Title + ", Genre=" + Genre + ", Year=" + Year + "]";
	}
}
