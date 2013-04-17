package view;

public class Timer extends Thread{
	Canvas cv;
	public Timer(Canvas c){
		cv = c;
	}
	public void run(){
		for (int i =0;i<5;i++){
			try {
				cv.yl.setVisible(true);
				sleep(660);
				cv.yl.setVisible(false);
				sleep(660);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cv.yl.setVisible(false);
		
	}
}
