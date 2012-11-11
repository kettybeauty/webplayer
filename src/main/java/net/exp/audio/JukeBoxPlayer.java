package net.exp.audio;

import net.exp.audio.player.UrlPlayer;

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
		new UrlPlayer(soundfile).play();
	}
}
