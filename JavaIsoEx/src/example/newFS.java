package example;

public class newFS {
	public static void main(String[] args){
		newFetchState nfs = new newFetchState();
		WeatherCon wc = nfs.getWeather();
		System.out.println(wc.getWeather());
		System.out.println(wc.getTimestamp());
	}
}
