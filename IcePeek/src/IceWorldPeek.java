/*
 *  IceWorldPeek by Group 6 ICE of Intania 95#
 *  Member : Pink, Ja, Natt, Prinn.
 *  
 *  User Manual
 *  
 *  time			: return time of the IceWorld
 *  states			: return states of the ICEWorld
 *  actions s		: return actions of 's'
 *  gresources s	: return gresources of 's'
 *  gurl s			: return gurl of 's'
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
/*  I don't use '*' symbol when importing to reduce the program loads.
 *  It doesn't need to loads everything from some java package.
 *  I only use some of it. Save system resources.
 *  Only drawback is program size since I have to write a few more line of code and this comment.
 *  It's only a few byte.
 *  You probably have a terrabyte harddisk already. :P
 *  Yes, This is an easter egg.
 *  Have fun.
 */ 
public class IceWorldPeek extends Thread{
	public static Operation op = new Operation(null);
	public static void main(String args[]) throws IOException, InterruptedException{
		System.out.println("\nUser Manual");
		System.out.println("\n----------------------------------\n");
		System.out.println("time\t\t\t: return time of the IceWorld");
		System.out.println("states\t\t\t: return states of the ICEWorld");
		System.out.println("actions s\t\t: return actions of 's'");
		System.out.println("gresources s\t\t: return gresources of 's'");
		System.out.println("gurl s\t\t\t: return gurl of 's'");
		System.out.println("\n----------------------------------\n");
		// using another thread so 'abort' feature via CTRL+Z might be implements later.
		op.start();
	}
}
class Operation extends Thread{
	public static Operation op = new Operation(null);
	public static String cmd;
	String c = "";
	public Operation(String cmd) {
		c=cmd;
	}
	public void run(){
		String input="";
		String result ="";
		String server = "iceworld.sls-atl.com";
		try{
			while(true){
				System.out.println("Checking server...");
				if(isReachable(server)){
					System.out.println("Server online");
					System.out.print("Input HTTP Request here : ");
					BufferedReader HTTP = new BufferedReader(new InputStreamReader(System.in));
					input = HTTP.readLine();
					result = "\n"+ProcessReq(input)+"\n";
					System.out.print(result);
				} else {
					System.out.println("Server is down");
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public boolean isReachable(String address) {
		Socket socket = null;
		boolean reachable = false;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(address, 80), 1000);
			reachable = true;
		}
		catch (Exception e) {
		}
		finally {
			if (socket != null)
				try {
					socket.close();
				}
			catch (IOException e) {}
		}
    return reachable;
	}
	public String ProcessReq(String req) throws MalformedURLException, IOException{
		
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
	public String ProcessReq(String req1, String req2) throws MalformedURLException, IOException{
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
}