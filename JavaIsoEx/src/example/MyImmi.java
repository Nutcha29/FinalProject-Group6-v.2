package example;

import iceworld.given.MyIcetizen;

public class MyImmi extends iceworld.given.ICEWorldImmigration{

	public MyImmi(MyIcetizen icetizen) {
		super(icetizen);
		// TODO Auto-generated constructor stub
	}
	
	public boolean login(String upass){
		return(super.login(upass));
	}

}
