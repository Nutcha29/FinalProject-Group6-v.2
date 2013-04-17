package example;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JPanel;

import util.ImageLoader;


public class IcetizenLoader extends JPanel{
	/*	 http://iceworld.sls-atl.com/graphics/weapon/stick1.png
		http://iceworld.sls-atl.com/graphics/weapon/sword4.png
		
		>>gurl
			request:  http://iceworld.sls-atl.com/api/&cmd=gurl&gid=w044
			get:      {"status":1,"data":{"gid":"W044","location":"graphics\/weapon\/stick1.png"}}

       >>gresource
			request: http://iceworld.sls-atl.com/api/&cmd=gresources&uid=0
			get all resources name:
	{"status":1,"data":["B001","B002","B003","B004","B005","B006","B007","H008","H009","H010","H011","H012","H013","H014","H015","H016","H017","H018","S019","S020","S021","S022","S023","S024","S025","S026","S027","S028","S029","S030","S031","S032","S033","S034","S035","S036","S037","S038","S039","S040","S041","S042","S043","W044","W045","W046","W047","W048","W049","W050","W051","W052","W053","W054","W055","W056","W057","H058","H059","H060","H061","H062","H063","H064","H065","H066","H067","H068","H069","H070","S071","S072","S073","W074","W075","W076","W077","W078","W079","H080","H081","H082","H083","H084","S085","S086","S087","S088","S089","S090","S091","H092","H093","H094","H095","H096","H097","B098","B099","B100","B101","B102","S103","S104","S105","S106","S107","H108","H109","H110","H111","H112","H113","S114","S115","S116","S117","S118","S119"]}

			request:http://iceworld.sls-atl.com/api/&cmd=gresources&uid=1
			get custo kong that id: 
			{"status":1,"data":[{"B":"B001","H":"H008","S":"S019","W":"W046"}]}
			
			IcetizenLook look = new IcetizenLook();
			look.gidB = "B001";
			look.gidS = "S001";
			look.gidH = "H001";
			look.gidW = "W001";
		
	*/
	static String[] shirts=null;
	static String[] bodys=null;
	static String[] heads=null;
	static String[] weapons=null;
	/*String[] uBodyURL;
	String[] uHeadURL;
	String[] uShirtURL;
	String[] uWeaponURL;*/
	static BufferedImage shirt,body,head,weapon,bg;
	static int b = 0;
	static int h= 0;
	static int s = 0;
	static int w = 0;
	static int bC = 0;
	static int hC= 0;
	static int sC = 0;
	static int wC = 0;
	static String custH=null;
	static String custB=null;
	static String custW=null;
	static String custS=null;
	
	public static void getBufferredImage(String B,String H,String S,String W){
		body = ImageLoader.loadImageFromRemote(B);
		head = ImageLoader.loadImageFromRemote(H);
		shirt = ImageLoader.loadImageFromRemote(S);
		weapon = ImageLoader.loadImageFromRemote(W);
		//bg=ImageLoader.loadImageFromRemote(BG);
	}
	/*public String[] getURLlists(String a[]) throws IOException{
		String uURL[] = new String[a.length];
		String uName,gtemp;
		for (int i = 0; i<a.length;i++){
		
				gtemp = ProcessReq("gurl "+a[i]);
		
			
			//System.out.println("Processing "+glist[i]+" "+(i+1)+" Item Processed");
			a[i]=ProcessReq("gurl "+a[i]).substring(45,gtemp.length()-4);
			//System.out.println("Processing "+glist[i]+" "+(i+1)+" Item Processed");
			uName="http://iceworld.sls-atl.com/"+(a[i].substring(0,a[i].indexOf("/")-1))+(a[i].substring(a[i].indexOf("/"),a[i].lastIndexOf("/")-1))+(a[i].substring(a[i].lastIndexOf("/"),a[i].length()));
		uURL[i]=uName;
		
		}
		return uURL;
		
	}*/
	public String getURLlists(String a) throws IOException{
		//String uURL ="";
		String uName,gtemp;
		
		
				gtemp = ProcessReq("gurl "+a);
		
			
			//System.out.println("Processing "+glist[i]+" "+(i+1)+" Item Processed");
			a=ProcessReq("gurl "+a).substring(45,gtemp.length()-4);
			//System.out.println(a+"\n");
			//System.out.println("Processing "+glist[i]+" "+(i+1)+" Item Processed");
			uName="http://iceworld.sls-atl.com/"+(a.substring(0,a.indexOf("/")-1))+(a.substring(a.indexOf("/"),a.lastIndexOf("/")-1))+(a.substring(a.lastIndexOf("/"),a.length()));
			System.out.print(uName+"\n");
		//uURL=uName;
		
		
		return uName;
		
	}

	
	public static void getresources() throws IOException{
		System.out.println();
		String result ="";
		String[] glist = null;
		//sC=cust.getIcetizenLook().gidS;
		try{
			result = ProcessReq("gresources 0");
			result = result.substring(20,result.lastIndexOf("]")+1);
			//System.out.println(result.substring(0,result.length()-1));
			glist = new String [result.length()/7];
			for (int i=0;i<glist.length;i++){
				glist[i]=result.substring((7*i)+1,(7*i)+5);
			}
			/*
			for (int i=0;i<glist.length;i++){
				System.out.println(glist[i]);
			}
			*/
			//System.out.println(glist.length);
			
			//String[] glist3 = {"B001","B002","B003","B004","B005","B006","B007","H008","H009","H010","H011","H012","H013","H014","H015","H016","H017","H018","S019","S020","S021","S022","S023","S024","S025","S026","S027","S028","S029","S030","S031","S032","S033","S034","S035","S036","S037","S038","S039","S040","S041","S042","S043","W044","W045","W046","W047","W048","W049","W050","W051","W052","W053","W054","W055","W056","W057","H058","H059","H060","H061","H062","H063","H064","H065","H066","H067","H068","H069","H070","S071","S072","S073","W074","W075","W076","W077","W078","W079","H080","H081","H082","H083","H084","S085","S086","S087","S088","S089","S090","S091","H092","H093","H094","H095","H096","H097","B098","B099","B100","B101","B102","S103","S104","S105","S106","S107","H108","H109","H110","H111","H112","H113","S114","S115","S116","S117","S118","S119"};
			//System.out.println(glist3.length);
			//
			b=0;
			h=0;
			s=0;
			w=0;
			
			//glist = new String[glist.length];
			for (int i = 0; i<glist.length;i++){
			if(glist[i].substring(0,1).equals("B")){
				b++;	
			}
			if(glist[i].substring(0,1).equals("H")){
				h++;	
			}if(glist[i].substring(0,1).equals("S")){
				s++;	
			}if(glist[i].substring(0,1).equals("W")){
				w++;	
			}
			}
			System.out.println(b);
			System.out.println(h);
			System.out.println(s);
			System.out.println(w);
			
			bodys=new String[b];
			shirts=new String[s];
			heads= new String[h];
			weapons= new String[w];
				int bC = 0;
				int hC= 0;
				int sC= 0;
				int wC= 0;
			if(bodys[0]==null){
			for (int i=0;i<glist.length;i++){
				
				if(glist[i].substring(0,1).equals("B")){
					bodys[bC] =glist[i]; 
					bC++;
					System.out.println("add body"+bC);
					//System.out.println(b);
				}
				if(glist[i].substring(0,1).equals("H")){
					heads[hC] =glist[i]; 
					hC++;
					System.out.println("add head"+hC);
				}
				if(glist[i].substring(0,1).equals("S")){
					shirts[sC] =glist[i]; 
					sC++;
					System.out.println("add shirt"+sC);
				}
				if(glist[i].substring(0,1).equals("W")){
					weapons[wC] =glist[i]; 
					wC++;
					System.out.println("add weapon"+wC);
				}
				
				}
			}
			System.out.println(bC);
			System.out.println(hC);
			System.out.println(sC);
			System.out.println(wC);
			
			/*uBodyURL= new String[bodys.length];
			
			uHeadURL= new String[heads.length];
			uShirtURL= new String[shirts.length];
			uWeaponURL= new String[weapons.length];
			//uURL= new String[bodys.length];
			//uBodyURL= new String[bodys.length];
			//-------------------------------------
			for(int i=0; i<bodys.length;i++){
				uBodyURL[i]= getURLlists(bodys[i]);
			}
			for(int i=0; i<heads.length;i++){
				uHeadURL[i]= getURLlists(heads[i]);
			}
			for(int i=0; i<shirts.length;i++){
				uShirtURL[i]= getURLlists(shirts[i]);
			}
			for(int i=0; i<weapons.length;i++){
				uWeaponURL[i]= getURLlists(weapons[i]);
			}
			//uBodyURL = getURLlists(bodys);
			//uHeadURL = getURLlists(heads);
			//uShirtURL = getURLlists(shirts);
			//uWeaponURL = getURLlists(weapons);
			
			for (int i = 0; i<uBodyURL.length;i++){
				System.out.println(uBodyURL[i]);
			}

			for (int i = 0; i<uHeadURL.length;i++){
				System.out.println(uHeadURL[i]);
			}
			for (int i = 0; i<uShirtURL.length;i++){
				System.out.println(uShirtURL[i]);
			}
			for (int i = 0; i<uWeaponURL.length;i++){
				System.out.println(uWeaponURL[i]);
			}*/
			
		/*uNamelist = new String [glist.length];
			for (int i = 0; i<glist.length;i++){
				gtemp=ProcessReq("gurl "+glist[i]);
				
				System.out.println("Processing "+glist[i]+" "+(i+1)+" Item Processed");
				glist[i]=ProcessReq("gurl "+glist[i]).substring(45,gtemp.length()-4);
				uName="http://iceworld.sls-atl.com/"+(glist[i].substring(0,glist[i].indexOf("/")-1))+(glist[i].substring(glist[i].indexOf("/"),glist[i].lastIndexOf("/")-1))+(glist[i].substring(glist[i].lastIndexOf("/"),glist[i].length()));
				uNamelist[i]=uName;
				//saveImage(uName,iName);
				//System.out.println(uName);
				//iName=uName.substring(uName.lastIndexOf("/")+1,uName.length());
				//System.out.println(iName+" Saved");
			}
		
			
			
			//-------------------------------------
			
			for (int i = 0; i<uNamelist.length;i++){
				System.out.println(uNamelist[i]);
				//System.out.println(glist[i]);
				//gtemp="http://iceworld.sls-atl.com/"+(glist[i].substring(0,glist[i].indexOf("/")-1))+(glist[i].substring(glist[i].indexOf("/"),glist[i].lastIndexOf("/")-1))+(glist[i].substring(glist[i].lastIndexOf("/"),glist[i].length()));
				//glist[i]="http://iceworld.sls-atl.com/"+gtemp;
			}
			
			for (int i = 0; i<glist.length;i++){
				System.out.println(glist[i]);
			}
			*/
			//System.out.print(glist.length);
			
			//urlList1 = new String[glist.length];
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public static void setIndex(Myicetz cust){
		 custH =cust.getIcetizenLook().gidS;
		
		custB =cust.getIcetizenLook().gidB;
		custS =cust.getIcetizenLook().gidH;
		custW =cust.getIcetizenLook().gidW;
		System.out.println(custS);
		System.out.println(custB);
		System.out.println(custH);
		System.out.println(custW);
		System.out.println(bodys.length);
		System.out.println(bodys[0]);
		System.out.println(bodys[bC]);
		for(int i=0;i<bodys.length;i++){
			if(bodys[i].equals(custB)){
				bC=i;
			}
		}
		
		
		for(int i=0;i<shirts.length;i++){
			if(shirts[i].equals(custS)){
				sC=i;
			}
		}
		for(int i=0;i<weapons.length;i++){
			if(weapons[i].equals(custW)){
				wC=i;
			}
		}
		for(int i=0;i<heads.length;i++){
			if(heads[i].equals(custH)){
				hC=i;
			}
		}
		System.out.println(heads[hC]);
		System.out.println(bodys[bC]);
		System.out.println(shirts[sC]);
		System.out.println(weapons[wC]);
		
	}
	
public static String ProcessReq(String req) throws MalformedURLException, IOException{
		
		String result = "";
		
		if (req.indexOf(' ') == -1){
			URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+req);
	        URLConnection yc = a.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        
	        while ((inputLine = in.readLine()) != null) {
	            result += inputLine+"\n";
	        }
	        in.close();
		}else{
			result = ProcessReq(req.substring(0,req.indexOf(' ')),req.substring(req.indexOf(' ')+1,req.length()));
		}
		return result;
	}
	public static String ProcessReq(String req1, String req2) throws MalformedURLException, IOException{
		String result="";
		String s = "";
		if(req1.equalsIgnoreCase("actions")){
			s=req1+"&from="+req2;
		} else if(req1.equalsIgnoreCase("gresources")){
			s=req1+"&uid="+req2;
		} else if(req1.equalsIgnoreCase("gurl")){
			s=req1+"&gid="+req2;
		}
		
		URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+s);
        URLConnection yc = a.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            result += inputLine+"\n";
        }
        in.close();
		return result;
	}
	public String[] subArrayString(String[] a, int s,int e){
		String[] r = new String[e-s];
		for (int i=0;i<e-s;i++){
			r[i]=a[i+s];
		}
		return a;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/*try {
			getresources();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(shirts.length);
		String bodyURL = null;
		String shirtURL = null;
		String headURL = null;
		String weaponURL = null;
		try {
			shirtURL= getURLlists(shirts[sC]);
			bodyURL = getURLlists(bodys[bC]);
			 headURL=getURLlists(heads[hC]);
			 System.out.print(weapons[wC]);
			 System.out.println(wC);
		weaponURL= getURLlists(weapons[wC]);
		String sCode= shirts[sC];
		String bCode= bodys[bC];
		String hCode= heads[hC];
		String wCode= weapons[wC];
		System.out.println(sCode);
		System.out.println(bCode);
		System.out.println(hCode);
		System.out.println(wCode);
		System.out.println(bodys[0]);
		System.out.println(bodys[bodys.length-1]);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String bgURL="";
		getBufferredImage(bodyURL,headURL,shirtURL,weaponURL);
		g.drawImage(bg,15,25,this);
		g.drawImage(body,15,25,this);
		g.drawImage(head,15,25,this);
		g.drawImage(shirt,15,25,this);
		g.drawImage(weapon,15,25,this);
	}
}

	

	