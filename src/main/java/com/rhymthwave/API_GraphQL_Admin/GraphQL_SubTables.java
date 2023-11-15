package com.rhymthwave.API_GraphQL_Admin;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.ICultureServiceAdmin;
import com.rhymthwave.ServiceAdmin.IGenreService;
import com.rhymthwave.ServiceAdmin.IInstrumentServiceAdmin;
import com.rhymthwave.ServiceAdmin.IMoodServiceAdmin;
import com.rhymthwave.ServiceAdmin.ISongTypeServiceAdmin;
import com.rhymthwave.entity.Culture;
import com.rhymthwave.entity.Genre;
import com.rhymthwave.entity.Instrument;
import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.SongStyle;
import com.rhymthwave.entity.Tag;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphQL_SubTables {

	private final IMoodServiceAdmin mood;
	private final IInstrumentServiceAdmin instrument;
	private final ISongTypeServiceAdmin songType;
	private final ICultureServiceAdmin cultureServiceAdmin;
	private final IGenreService genreService;
	private final CRUD<Tag, Integer> tagService;

	@QueryMapping("getAllMood")
	public List<Mood> getAllMood() {
		return mood.findAllMood();
	}

	@QueryMapping("getAllInstrument")
	public List<Instrument> getAllInstrument() {
		return instrument.findAllInstrument();
	}

	@QueryMapping("getAllSongStyle")
	public List<SongStyle> getAllSongStyle() {
		return songType.findAllSongStyle();
	}

	@QueryMapping("getAllCulture")
	public List<Culture> getAllCulture() {
		return cultureServiceAdmin.findAllCulture();
	}

	@QueryMapping("getAllGener")
	public List<Genre> getAllGener() {
		return genreService.findAllGenre();
	}
	
	@QueryMapping("getAllTag")
	public List<Tag> getAllTag() {
		return tagService.findAll();
	}
}
