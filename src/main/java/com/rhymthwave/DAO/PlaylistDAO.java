package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.UserType;

@Repository
public interface PlaylistDAO extends JpaRepository<Playlist, Long>{	
	List<Playlist> findByUsertype(UserType usertype);
	
	@Query(value = "select playlists.* from usertype\r\n"
			+ "						join accounts on usertype.accountid = accounts.email\r\n"
			+ "						join author on accounts.email = author.email\r\n"
			+ "						join playlists on playlists.usertypeid = usertype.usertypeid\r\n"
			+ "						join playlist_recording on  playlist_recording.playlistsid = playlists.playlistid\r\n"
			+ "					where author.idrole = 4 or author.idrole = 5\r\n"
			+ "					group by playlists.playlistid, \r\n"
			+ "						playlists.createdate,\r\n"
			+ "						playlists.description,\r\n"
			+ "						playlists.ispublic,\r\n"
			+ "						playlists.playlistname,\r\n"
			+ "						playlists.quantity,\r\n"
			+ "						playlists.image,\r\n"
			+ "						playlists.usertypeid",nativeQuery = true)
	List<Playlist> getplayListSongAdmin();
	
	@Query(value = "select playlists.* from usertype\r\n"
			+ "						join accounts on usertype.accountid = accounts.email\r\n"
			+ "						join author on accounts.email = author.email\r\n"
			+ "						join playlists on playlists.usertypeid = usertype.usertypeid\r\n"
			+ "						join playlist_podcast on  playlist_podcast.playlistid = playlists.playlistid\r\n"
			+ "					where author.idrole = 4 or author.idrole = 5\r\n"
			+ "					group by playlists.playlistid, \r\n"
			+ "						playlists.createdate,\r\n"
			+ "						playlists.description,\r\n"
			+ "						playlists.ispublic,\r\n"
			+ "						playlists.playlistname,\r\n"
			+ "						playlists.quantity,\r\n"
			+ "						playlists.image,\r\n"
			+ "						playlists.usertypeid",nativeQuery = true)
	List<Playlist> getPodcastPlayListAdmin();
	
}
