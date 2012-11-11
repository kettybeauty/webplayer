package net.exp.audio.player;

import javazoom.jl.player.jlp;

public class UrlPlayer implements Player{
	jlp player;
	
	public UrlPlayer(String soundfile){
		String[] params = new String[2];
		params[0] = "-url";
		params[1] = soundfile;
		player = jlp.createInstance(params);
	}
	public void play() {
		try{
			player.play();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
