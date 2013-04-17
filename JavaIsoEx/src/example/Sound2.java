package example;

import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JDialog;

public class Sound2 // Holds one audio file
{
	Clip clip;

	public Sound2(String filename)
	{	        
	        try {
		        URL url = this.getClass().getClassLoader().getResource(filename);
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		       
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void playSound()
	{
		AudioClip audioClip = (AudioClip) clip;
		audioClip.loop(); // Play continuously
	}
	public void stopSound()
	{
		clip.stop(); // Stop
	}
	public void playSoundOnce()
	{
		AudioClip audioClip = (AudioClip) clip;
		audioClip.play(); // Play only once
	}
	public void decrease()//decrease volume
	{	
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	 float a = gainControl.getValue();   
	 System.out.println(a);
	 gainControl.setValue(a-3f); // Reduce volume by 10 decibels.
	    float b = gainControl.getValue();
	    System.out.println(b);
	}
	public void increase()//increase volume
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		 float a = gainControl.getValue();   
		 System.out.println(a);
		 if(a+3f<=6.02){
		 gainControl.setValue(a+3f);}
		 else{ gainControl.setValue(6);
		 JDialog yo = new JDialog();
		 yo.setSize(100,100);
		 yo.setVisible(true);
		 }
		    float b = gainControl.getValue();
		    System.out.println(b);
	  }

	public void set(int num) {
		// TODO Auto-generated method stub
		 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		    gainControl.setValue(num);
	}
}

