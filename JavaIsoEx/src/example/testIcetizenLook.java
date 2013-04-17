package example;

import iceworld.given.IcetizenLook;

public class testIcetizenLook {
	
	public static Myicetz m = new Myicetz();
	public static IcetizenLook l = new IcetizenLook();
	
	public static void main(String[] args){
		l.gidB="B001";
		l.gidH="H001";
		l.gidS="S001";
		l.gidW="W001";
		m.setIcetizenLook(l);
		System.out.println(m.getIcetizenLook().gidB);
		System.out.println(m.getIcetizenLook().gidH);
		System.out.println(m.getIcetizenLook().gidS);
		System.out.println(m.getIcetizenLook().gidW);
	}
}
