package examples.dto;

public class Actor {

	private Integer Actor_id;
	private String Actor_name;
	private String Actor_bdate;
	
	/* Constructor */
	public Actor(Integer actor_id, String actor_name, String actor_bdate) {
		super();
		Actor_id = actor_id;
		Actor_name = actor_name;
		Actor_bdate = actor_bdate;
	}


	/* Getter & Setter */
	public Integer getActor_id() {
		return Actor_id;
	}

	public void setActor_id(Integer actor_id) {
		Actor_id = actor_id;
	}

	public String getActor_name() {
		return Actor_name;
	}

	public void setActor_name(String actor_name) {
		Actor_name = actor_name;
	}

	public String getActor_bdate() {
		return Actor_bdate;
	}

	public void setActor_bdate(String actor_bdate) {
		Actor_bdate = actor_bdate;
	}

	/* toString() */
	@Override
	public String toString() {
		return "Actor [Actor_id=" + Actor_id + ", Actor_name=" + Actor_name + ", Actor_bdate=" + Actor_bdate + "]";
	}
	
}
