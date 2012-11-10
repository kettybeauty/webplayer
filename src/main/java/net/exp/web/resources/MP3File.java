package net.exp.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javazoom.jl.player.jlp;
import net.exp.audio.AsyncPlayer;

@Path("/mp3")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class MP3File {
	
	@GET
	@Path("/play/{mp3name}/{repeatTimes}")
	public String play(@PathParam("mp3name") String mp3name, @PathParam("repeatTimes") int repeatTimes){
		String [] params = new String[2];
		params[0] = "-url";
		params[1] = "http://localhost:8000/mp3/"+mp3name;
		jlp player = jlp.createInstance(params);
		
		new AsyncPlayer(player, repeatTimes).start();
		return "200";
	}
}
