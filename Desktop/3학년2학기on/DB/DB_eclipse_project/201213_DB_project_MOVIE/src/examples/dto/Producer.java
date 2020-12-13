package examples.dto;

public class Producer {
	private Integer Producer_id;
	private String Producer_name;
	private String Producer_bdate;
	
	public Producer(Integer producer_id, String producer_name, String producer_bdate) {
		super();
		Producer_id = producer_id;
		Producer_name = producer_name;
		Producer_bdate = producer_bdate;
	}

	public Integer getProducer_id() {
		return Producer_id;
	}
	public void setProducer_id(Integer producer_id) {
		Producer_id = producer_id;
	}
	public String getProducer_name() {
		return Producer_name;
	}
	public void setProducer_name(String producer_name) {
		Producer_name = producer_name;
	}
	public String getProducer_bdate() {
		return Producer_bdate;
	}
	public void setProducer_bdate(String producer_bdate) {
		Producer_bdate = producer_bdate;
	}
	@Override
	public String toString() {
		return "Producer [Producer_id=" + Producer_id + ", Producer_name=" + Producer_name + ", Producer_bdate="
				+ Producer_bdate + "]";
	}
}
