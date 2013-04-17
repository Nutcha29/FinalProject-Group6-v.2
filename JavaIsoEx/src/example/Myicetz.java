package example;
import iceworld.given.*;
public class Myicetz implements MyIcetizen {
	IcetizenLook look;
	int pordID;
	int listeningPort;
	String uname;
	@Override
	public int getIcePortID() {

		return this.pordID;
	}

	@Override
	public IcetizenLook getIcetizenLook() {
		return look;
	}

	@Override
	public int getListeningPort() {

		return this.listeningPort;
	}

	@Override
	public String getUsername() {

		return this.uname;
	}

	@Override
	public void setIcePortID(int a) {
		pordID=a;

	}

	@Override
	public void setIcetizenLook(IcetizenLook a) {
		look=a;
		//String gidB,gidS,gidH,gidW = null;

	}

	@Override
	public void setListeningPort(int a) {
		listeningPort = a;

	}

	@Override
	public void setUsername(String a) {
		uname=a;

	}

}