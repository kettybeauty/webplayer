package net.exp.audio;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class PlayQueue {
	
	private Queue<String> queue = new ArrayBlockingQueue<String>(1000);
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	public String nextSound(){
		return queue.poll();
	}
	public void reset(){
		queue.clear();
	}
	public void addSound(String soundfile){
		queue.add(soundfile);
	}
}
