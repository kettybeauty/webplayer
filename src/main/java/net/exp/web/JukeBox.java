package net.exp.web;

import net.exp.audio.JukeBoxPlayer;
import net.exp.audio.PlayQueue;

public class JukeBox {
	private static final JukeBox jukebox = new JukeBox();
	public static JukeBox getInstance(){
		return jukebox;
	}
	
	private PlayQueue playlist = new PlayQueue();
	private JukeBoxPlayer player = new JukeBoxPlayer(playlist);
	private Thread playerThread = new Thread(player);
	
	private JukeBox(){
		playerThread.start();
	}
	
	public void wakeup(){
		playerThread.interrupt();
	}
	
	public PlayQueue getPlayList(){
		return playlist;
	}
}
