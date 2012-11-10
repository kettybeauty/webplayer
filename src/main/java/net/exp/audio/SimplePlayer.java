package net.exp.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.jlp;

public class SimplePlayer {
	public static void main(String [] args){
		if (args.length<1){
			usage();
			return;
		}
		String [] params = new String[1];
		params[0] = args[0];
		jlp player = jlp.createInstance(params);
		
		int repeatTimes = 1;
		if (args.length>1){
			repeatTimes = Integer.parseInt(args[1]);
		}
		try
		{
			while(repeatTimes>0){
				repeatTimes = repeatTimes - 1;
				player.play();
			}
		}
		catch (JavaLayerException e)
		{
			e.printStackTrace();
		}
	}
	
	static void usage(){
		System.out.println("java javazoom.jl.player.jlp localfile.mp3 (repeat times)");
	}
}
