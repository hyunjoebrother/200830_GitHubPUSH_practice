package examples.dto;

public class Director {
	
	private Integer Director_id;
	private String Director_name;
	private String Director_bdate;
	
	public Director(Integer director_id, String director_name, String director_bdate) {
		super();
		Director_id = director_id;
		Director_name = director_name;
		Director_bdate = director_bdate;
	}

	public Integer getDirector_id() {
		return Director_id;
	}
	public void setDirector_id(Integer director_id) {
		Director_id = director_id;
	}
	public String getDirector_name() {
		return Director_name;
	}
	public void setDirector_name(String director_name) {
		Director_name = director_name;
	}
	public String getDirector_bdate() {
		return Director_bdate;
	}
	public void setDirector_bdate(String director_bdate) {
		Director_bdate = director_bdate;
	}
	
	@Override
	public String toString() {
		return "Director [Director_id=" + Director_id + ", Director_name=" + Director_name + ", Director_bdate=" + Director_bdate + "]";
	}
	
	

}
