package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	List<Playlist> findByUsertypeAndIsPublic(UserType usertype, Boolean isPublic);
	
	@Query(value="select pl.* from playlists pl "
			+ "join playlist_recording plr on pl.playlistid = plr.playlistsid "
			+ "join recording r on r.recordingid = plr.recordingid "
			+ "join songs s on s.songsid = r.songsid "
			+ "join writter w on w.songsid = s.songsid "
			+ "join artist a on w.artistid = a.artistid "
			+ "join accounts acc on acc.email = a.email "
			+ "join author auth on auth.email = acc.email "
			+ "where w.artistid = :artistId and auth.idrole in :roleId and pl.ispublic = 1 "
			+ "group by pl.playlistid, pl.playlistname,pl.createdate,pl.description,pl.ispublic,pl.quantity,pl.image,pl.usertypeid",nativeQuery = true)
	List<Playlist> findPlaylistFeaturingByArtist(@Param("artistId") Long artistId,@Param("roleId") List<Integer> roleId);
	
	@Query(value="select top 50 pl.* from playlists pl "
			+ "join playlist_recording plr on pl.playlistid = plr.playlistsid "
			+ "join recording r on r.recordingid = plr.recordingid "
			+ "join songs s on s.songsid = r.songsid "
			+ "join writter w on w.songsid = s.songsid "
			+ "join artist a on w.artistid = a.artistid "
			+ "join accounts acc on acc.email = a.email "
			+ "join author auth on auth.email = acc.email "
			+ "where w.artistid = :artistId and auth.idrole in :roleId and pl.ispublic = 1 "
			+ "group by pl.playlistid, pl.playlistname,pl.createdate,pl.description,pl.ispublic,pl.quantity,pl.image,pl.usertypeid "
			+ "order by NEWID()",nativeQuery = true)
	List<Playlist> findPlaylistDiscoverByArtist(@Param("artistId") Long artistId,@Param("roleId") List<Integer> roleId);

	@Query("select count(p.playlistId) from  Playlist  p")
	int countPlaylist();
}
