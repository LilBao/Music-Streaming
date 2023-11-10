package com.rhymthwave.API.GraphQL;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.RecordService;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.Recording;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RecordingGraphQL {
	private final CRUD<Recording, Long> crudRecording;
	
	private final RecordService recordSer ;
	
	@QueryMapping("recordingById")
	public Recording findOne(@Argument("recordingId") Long id) {
		log.info(">>>>>>> GraphQL_Recording:findRecordById | id: {}", id);
		return crudRecording.findOne(id);
	}
	
	@QueryMapping("recommendedListRecording")
	public List<Recording> findListRandom() {
		log.info(">>>>>>> GraphQL_Recording:findRandom");
		return recordSer.findListRecordRandom();
	}
}
