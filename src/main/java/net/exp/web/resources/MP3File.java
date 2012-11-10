package net.exp.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javazoom.jl.player.jlp;
import net.exp.audio.AsyncPlayer;
import net.exp.web.JukeBox;

@Path("/mp3")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class MP3File {
	private static final String RES_LOCATION = "http://localhost:8000/mp3/";
	private JukeBox jukebox = JukeBox.getInstance();
	
	@GET
	@Path("/play/{mp3name}/{repeatTimes}")
	public String play(@PathParam("mp3name") String mp3name, @PathParam("repeatTimes") int repeatTimes){
		String soundurl = RES_LOCATION+mp3name;
		while(repeatTimes>0){
			jukebox.getPlayList().addSound(soundurl);
			repeatTimes = repeatTimes -1;
		}
		return "200";
	}
	
	@GET
	@Path("/playlist/{mp3names}")
	public String playlist(@PathParam("mp3names") String mp3name){
		if (mp3name==null || mp3name.isEmpty()){
			return "404";
		}
		String [] sounds = mp3name.split(",");
		for (int i=0;i<sounds.length;i++){
			jukebox.getPlayList().addSound(RES_LOCATION+sounds[i]);
		}
		
		return "200";
	}
}
