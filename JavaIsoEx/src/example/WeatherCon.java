package example;

public class WeatherCon {
	String weather;
	int Timestamp;
	public WeatherCon(String w,int t){
		weather = w;
		Timestamp =t;
	}
	public String getWeather(){
		return weather;
	}
	public int getTimestamp(){
		return Timestamp;
	}
	
	public void setWeather(String w){
		weather = w;
	}
	public void setTimestamp(int t){
		Timestamp = t;
	}
}
