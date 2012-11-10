package net.exp.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.jlp;

public class AsyncPlayer extends Thread{
	private jlp player;
	private int times = 1;
	
	public AsyncPlayer(jlp player, int times){
		this.player = player;
		this.times = times;
	}
	
	public void run(){
		try
		{
			while(times>0){
				times = times - 1;
				player.play();
			}
		}
		catch (JavaLayerException e)
		{
			e.printStackTrace();
		}
	}
}
