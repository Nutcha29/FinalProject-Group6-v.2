package example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ProcessAction {
	static String result;
	static char quotationMark = '"';
	static char colon = ':';
	static char comma = ',';
	static char openBracket = '{';
	static char closeBracket = '}';
	static int timestamp;
	Processor p = new Processor();
	/*
	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException{
		
		System.out.println(ProcessReq("actions "));
		
	}
	*/
	
	//{"status":1,"data":[{"aid":"1161","action_type":"2","uid":"16","timestamp":"1365686291","detail":"TalkingTest"},{"aid":"1160","action_type":"3","uid":"16","timestamp":"1365686278","detail":"YellingTest"},{"aid":"1159","action_type":"1","uid":"16","timestamp":"1365685077","detail":"(68,70)"},{"aid":"1158","action_type":"1","uid":"1805","timestamp":"1365679396","detail":"(0,0)"},{"aid":"1157","action_type":"1","uid":"1804","timestamp":"1365678479","detail":"(0,0)"},{"aid":"1156","action_type":"1","uid":"1803","timestamp":"1365674683","detail":"(0,0)"},{"aid":"1155","action_type":"1","uid":"13","timestamp":"1365672336","detail":"(15,88)"},{"aid":"1154","action_type":"1","uid":"1802","timestamp":"1365667557","detail":"(5,12)"}]}
	
	// if no action
	// then skip
	// else if not empty
	// process action
	
	//offline testing resources
	//String action = "{\"status\":1,\"data\":[{\"aid\":\"1161\",\"action_type\":\"2\",\"uid\":\"16\",\"timestamp\":\"1365686291\",\"detail\":\"TalkingTest\"},{\"aid\":\"1160\",\"action_type\":\"3\",\"uid\":\"16\",\"timestamp\":\"1365686278\",\"detail\":\"YellingTest\"},{\"aid\":\"1159\",\"action_type\":\"1\",\"uid\":\"16\",\"timestamp\":\"1365685077\",\"detail\":\"(68,70)\"},{\"aid\":\"1158\",\"action_type\":\"1\",\"uid\":\"1805\",\"timestamp\":\"1365679396\",\"detail\":\"(0,0)\"},{\"aid\":\"1157\",\"action_type\":\"1\",\"uid\":\"1804\",\"timestamp\":\"1365678479\",\"detail\":\"(0,0)\"},{\"aid\":\"1156\",\"action_type\":\"1\",\"uid\":\"1803\",\"timestamp\":\"1365674683\",\"detail\":\"(0,0)\"},{\"aid\":\"1155\",\"action_type\":\"1\",\"uid\":\"13\",\"timestamp\":\"1365672336\",\"detail\":\"(15,88)\"},{\"aid\":\"1154\",\"action_type\":\"1\",\"uid\":\"1802\",\"timestamp\":\"1365667557\",\"detail\":\"(5,12)\"}]}";
	
	public Action[] getAllActions(){
		return getActionSinceTime(0);
	}
	
	//will return raw action list
	public Action[] getActionSinceTime(int t){
		
		//get this string from p.ProcessReq
		String actionData;
		
		int[] oBracket;
		int[] eBracket;
		int oCnt;
		int eCnt;
		int cCnt;
		int Index;
		String[] DataList = null;
		Action[] aList = null;
		try {
			cCnt=0;
			actionData = p.ProcessReq("actions "+t);
			if(actionData.indexOf("[")+1 == actionData.indexOf("]")){
				return null;
			}
			for (int i = 0 ; i<actionData.length();i++){
				if(actionData.charAt(i)==colon){
					cCnt+=1;	
				}
			}
			actionData = actionData.substring(actionData.indexOf("[")+1,actionData.indexOf("]"));
			if((cCnt-2)%5==0){
				Index=(cCnt-2)/5;
				DataList = new String[Index];
				aList = new Action[Index];
				oBracket = new int[Index];
				eBracket = new int[Index];
			} else { 
				System.out.println("Error : Data corrupted : " + actionData);
				return null;
			}
			oCnt=0;
			eCnt=0;
			for(int i=0;i<actionData.length();i++){
				if(actionData.charAt(i)==openBracket){
					oBracket[oCnt]=i;
					oCnt += 1;
				}
				if(actionData.charAt(i)==closeBracket){
					eBracket[eCnt]=i;
					eCnt += 1;
				}
			}
			for (int i =0;i<Index;i++){
				DataList[i]=actionData.substring(oBracket[i],eBracket[i]);
			}
			for(int i=0;i<Index;i++){
				aList[i] = refineData(DataList[i]);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}
	//:"1161","action_type":"2","uid":"16","timestamp":"1365686291","detail":"TalkingTest"
	public Action refineData(String s){
		int cCnt=0;
		int Pivot = 0;
		String[] actionData = new String[5];
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) == colon){
				cCnt += 1;		
				switch (cCnt) {
				case 1:
					actionData[0]=s.substring(i+1,s.indexOf(","));
	            	break;
	            case 2:
	            	Pivot = i;
	            	break;
	            case 3:
	            	actionData[1] = s.substring(Pivot+1,i-6);
	            	Pivot = i;
	            	break;
	            case 4:
	            	actionData[2] = s.substring(Pivot+1,i-12);
	            	Pivot = i;
	            	break;
	            case 5:
	            	actionData[3] = s.substring(Pivot+1,i-9);
	            	actionData[4] = s.substring(i+1,s.length());
	            	break;
	            default:
	            	actionData=null;
	              	break;
				}
		}
	}
		for (int i =0;i<actionData.length;i++){
			actionData[i]=p.removeQuotationMark(actionData[i]);
		}
		return new Action(actionData);
			
	}
}
