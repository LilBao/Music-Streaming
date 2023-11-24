package com.rhymthwave.API.GraphQL;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.RecordService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.Song;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RecordingGraphQL {
	private final CRUD<Recording, Long> crudRecording;
	
	private final RecordService recordSer;
	
	private final GetHostByRequest host;
	
	@QueryMapping("recordingById")
	public Recording findOne(@Argument("recordingId") Long id) {
		return crudRecording.findOne(id);
	}
	
	@QueryMapping("recommendedListRecording")
	public List<Recording> findListRandom() {
		return recordSer.findListRecordRandom();
	}
	
	@QueryMapping("mySongProject")
	public List<Recording> listSongProject(@Argument("artistid") Long id){
		return recordSer.findMyProject(id);
	}
	
	@QueryMapping("getListSongReleased")
	public List<Recording> getListSongReleased(@Argument("email") String email){
		return recordSer.findRecordByCreater(email);
	}
	
	@QueryMapping("findSongPl")
	public List<Recording> findSongPl(@Argument("songName") String songName){
		return recordSer.findSongPl(songName);
	}
}
