package example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Processor {
	static char quotationMark = '"';
	static char colon = ':';
	static char comma = ',';
	static char endBracket = '}';
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
	public String removeQuotationMark(String s){
		String r="";
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) != quotationMark){
				r += s.charAt(i);
			}
		}
		return r;
	}	
	//refine image link from ID
}
