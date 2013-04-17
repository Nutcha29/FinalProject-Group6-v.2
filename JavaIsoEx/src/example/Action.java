package example;

public class Action {
	int aid;
	int type;
	int uid;
	int timestamp;
	String detail;
	//getter method
	public int getAID(){
		return this.aid;
	}
	public int getType(){
		return this.type;
	}
	public int getUID(){
		return this.uid;
	}
	public int getTimestamp(){
		return this.timestamp;
	}
	public String getDetail(){
		return this.detail;
	}
	//---------setter method
	public void setAID(int a){
		aid=a;
	}
	public void setType(int t){
		type=t;
	}
	public void setUID(int u){
		uid=u;
	}
	public void setTimestamp(int t){
		timestamp=t;
	}
	public void setDetail(String d){
		detail=d;
	}
	
	public void setAllActionData(String[] s){
		setAID(Integer.parseInt(s[0]));
		setType(Integer.parseInt(s[1]));
		setUID(Integer.parseInt(s[2]));
		setTimestamp(Integer.parseInt(s[3]));
		setDetail(s[4]);
	}
	//---------Constructor
	public Action(String[] s){
		setAllActionData(s);
	}
}
