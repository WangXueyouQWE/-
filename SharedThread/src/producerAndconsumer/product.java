package producerAndconsumer;

public class product {
	private int sId;
	private String sName;
	public product(int sId,String sName) {
		// TODO Auto-generated constructor stub
		this.sId=sId;
		this.sName=sName;
	}
	@Override
	public String toString() {
		return "product [sId=" + sId + ", sName=" + sName + "]";
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	

}
