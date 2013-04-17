package example;

import java.awt.image.BufferedImage;


public class User {
	
	private int UID;
	private String username;
	private int type;
	private String ip;
	private int port;
	private int pid;
	private int LKDPosX;
	private int LKDPosY;
	private int LKDTimestamp;
	
	public User(String[] s){
		setAllUserData(s);
	}
	
	public void setAllUserData(String[] s){
		//System.out.print(s[7]);
		setUID(Integer.parseInt(s[0]));
		setUsername(s[1]);
		setType(Integer.parseInt(s[2]));
		setIP(s[3]);
		setPort(Integer.parseInt(s[4]));
		setPID(Integer.parseInt(s[5]));
		setLKDPosX(translateToX(s[6]));
		setLKDPosY(translateToY(s[6]));
		if(!s[7].equalsIgnoreCase("null")) {
			setLKDTimestamp(Integer.parseInt(s[7]));
		} else {
			setLKDTimestamp(0);
		}
	}
	public int translateToX(String s){
		String coordX;
		if(!s.equalsIgnoreCase("null")){
			coordX = s.substring(1,s.indexOf(','));
			return Integer.parseInt(coordX);
		} return 0;
		
	}
	public int translateToY(String s){
		String coordY;
		if(!s.equalsIgnoreCase("null")){
			coordY = s.substring(s.indexOf(',')+1,s.length()-1);
			return Integer.parseInt(coordY);
		} return 0;
	}
	
	public void setUID(int n){
		this.UID=n;
	}
	public void setUsername(String u){
		this.username=u;
	}
	public void setType(int t){
		this.type=t;
	}
	public void setIP(String i){
		this.ip=i;
	}
	public void setPort(int p){
		this.port=p;
	}
	public void setPID(int p){
		this.pid=p;
	}
	public void setLKDPosX(int x){
		this.LKDPosX=x;
	}
	public void setLKDPosY(int y){
		this.LKDPosY=y;
	}
	public void setLKDTimestamp(int t){
		this.LKDTimestamp=t;
	}
	
	public String getAllUserData(){
		String data="";
		data += "UID : "+getUID()+"\n";
		data += "Username : "+getUsername()+"\n";
		data += "Type : "+getType()+" "+getTypeName()+"\n";
		data += "IP : "+getIP()+"\n";
		data += "Port : "+getPort()+"\n";
		data += "Pid : "+getPID()+"\n";
		data += "Last Known Destination : "+getLKDPosX()+","+getLKDPosY()+" : at "+getLKDTimestamp()+" UnixTime";
		return data;
	}
	
	public int getUID(){
		return this.UID;
	}
	public String getUsername(){
		return this.username;
	}
	public int getType(){
		return this.type;
	}
	public String getTypeName(){
		if(type == 0){
			return "Alien";
		} else if(type == 1){
			return "IceTizen";
		}
		return null;
	}
	public String getIP(){
		return this.ip;
	}
	public int getPort(){
		return this.port;
	}
	public int getPID(){
		return this.pid;
	}
	public int getLKDPosX(){
		return this.LKDPosX;
	}
	public int getLKDPosY(){
		return this.LKDPosY;
	}
	public int getLKDTimestamp(){
		return this.LKDTimestamp;
	}
}
