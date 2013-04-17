package example;

public class printWeather {
	public static void main(String[] args){
		newFetchState fs = new newFetchState();
		while(true){	
			System.out.print(fs.getWeather());
		}
	}
}
