package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Author;

@Repository
public interface ArtistDAO extends JpaRepository<Artist, Long>{
	@Query("Select o from Artist o where account.email = :email")
	Artist findByEmail(@Param("email") String email);
	
	@Query("Select o from Artist o where o.isVerify = :verify")
	List<Artist> findAllIsVerify(@Param("verify") Boolean verify);
	
	@Query("select o from Artist o where o.artistId <> :id and o.artistName like :artistName and o.isVerify = true")
	List<Artist> findAllArtistVerify(@Param("id") Long id, @Param("artistName") String artistName);
	

	@Query(value = "select * from artist where artistid= :artistId", nativeQuery = true)
	Artist findbyID(@Param("artistId") Long artistId);


	@Query(value = "select art.*, imgart.url from Artist art left join images imgart on art.profileimage = imgart.accessid where art.artistName like %:keyword%",nativeQuery = true)
	List<Object> findByName(@Param("keyword") String keyword);

	@Query(value = "exec sp_Statistic_forManagerArtist :idAccount",nativeQuery = true)
	Object totalAlbumAndSong(@Param("idAccount") String idAccount);
	
	@Query(value = "select SUM(recording.listened) from recording left join songs on  songs.songsid = recording.songsid\r\n"
			+ "						left join writter on writter.songsid = songs.songsid\r\n"
			+ "						left join artist on artist.artistid = writter.artistid\r\n"
			+ "						where artist.email = ?1", nativeQuery = true)
	String sumListenedArtist(String idAccount);
	
	@Query("SELECT COUNT(*) FROM Follow f WHERE f.authorsAccountB.authorId = ?1")
	int countFollowerArtist(Long author);

}
