package net.exp.audio;

import javazoom.jl.player.jlp;

public class JukeBoxPlayer implements Runnable {
	private PlayQueue playlist;

	public JukeBoxPlayer(PlayQueue playlist) {
		this.playlist = playlist;
	}

	public void run() {
		while (true) {
			if (this.playlist.isEmpty()) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				play(playlist.nextSound());
			}
		}
	}

	private void play(String soundfile) {
		try {
			String[] params = new String[2];
			params[0] = "-url";
			params[1] = soundfile;
			jlp player = jlp.createInstance(params);
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
