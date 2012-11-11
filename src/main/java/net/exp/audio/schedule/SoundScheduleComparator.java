package net.exp.audio.schedule;

import java.util.Comparator;

import net.exp.audio.SoundScheduler;

public class SoundScheduleComparator implements Comparator<SoundScheduler>{

	public int compare(SoundScheduler o1, SoundScheduler o2) {
		return (int)(o1.time - o2.time);
	}

}
