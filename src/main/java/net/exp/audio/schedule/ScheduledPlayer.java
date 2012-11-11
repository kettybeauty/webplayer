package net.exp.audio.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.exp.audio.SoundScheduler;
import net.exp.audio.player.UrlPlayer;

public class ScheduledPlayer implements Runnable {
	private Logger log = LoggerFactory.getLogger(ScheduledPlayer.class);
	
	private List<SoundScheduler> sounds;
	
	public ScheduledPlayer(List<SoundScheduler> sounds){
		this.sounds = new ArrayList<SoundScheduler>(sounds);
		Collections.sort(this.sounds, new SoundScheduleComparator());
	}
	
	public void run() {
		for (SoundScheduler sound: sounds){
			long diff = sound.time - System.currentTimeMillis();
			if (diff<=0){
				//expired
				continue;
			}else{
				try{
					Thread.sleep(diff);
				}catch (Exception e) {
					e.printStackTrace();
				}
				log.debug(String.format("Played %s at %s", sound.sound, System.currentTimeMillis()+""));
				new UrlPlayer(sound.sound).play();				
			}
		}
	}
}
