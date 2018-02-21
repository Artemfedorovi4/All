package application;
	
import java.awt.Frame;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.application.Application;
import javafx.stage.Stage;

public class RunRun extends Application {

static class Boom{
	public static void runrun(String str,int size, JFrame frame) 
	{
		String str2="";
		for(int i =0; i<size;i++) str=str+" "; 
		
		while(true)
		{
			if(str.length()>=size/5.1)
			{
				for(int i=0; i<size/5.1;i++){
				str2=str.substring((int) (size/5.1)-1,(int) (size/5.1));
				str = str2 + str.substring(0,(int) (size/5.1));
				frame.setTitle(str);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
			else{
				frame.setTitle(str);
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				str=" "+str;
			}
		}
	}
}
	
	public static void main(String[] args){
		int i = 5; 
		System.out.println(++i + ++i);
	}
}