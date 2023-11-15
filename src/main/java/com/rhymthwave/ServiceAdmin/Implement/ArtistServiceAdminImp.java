package com.rhymthwave.ServiceAdmin.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.ArtistDAO;
import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.DAO.RoleDAO;
import com.rhymthwave.Service.EmailService;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.ServiceAdmin.INotification;
import com.rhymthwave.Utilities.SendMailTemplateService;
import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Email;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceAdminImp implements IArtistService, INotification<Artist> {

	private final ArtistDAO artistDAO;
	private final AuthorDAO authorDAO;
	private final AccountDAO accountDAO;
	private final RoleDAO roleDAO;
	private final EmailService mailService;
	private final SendMailTemplateService sendMailTemplateSer;
	public static final String TEMPLATE = "templateRoleArtist";
	
	@Override
	public Artist getOneArtistByEmail(String id) {
		Artist artist = artistDAO.findByEmail(id);
		if (artist == null) {
			return null;
		}

		return artist;
	}

	@Override
	public Object TotalAlbumAndSong(String idAccount) {

		return artistDAO.totalAlbumAndSong(idAccount);
	}

	@Override
	public int sumListenedArtist(String idAccount) {
		String sum = artistDAO.sumListenedArtist(idAccount);
		if (sum == null) {
			return 0;
		}
		return Integer.parseInt(sum);
	}

	@Override
	public int followerArtist(Integer idRole, String idAccount) {
		Author author = authorDAO.findAuthor(idRole, idAccount);

		return artistDAO.countFollowerArtist(author.getAuthorId());
	}

	@Override
	public List<Artist> getIsVerityArtist() {
		return artistDAO.findAllIsVerify(false);
	}

	@Override
	public Artist approveRolesArtist(Long idUser) {
		var artist = artistDAO.findById(idUser).orElse(null);
		if (artist != null) {
			var user = accountDAO.findByEmail(artist.getAccount().getEmail());
			var author = authorDAO.findByEmailAccount(user.getEmail());
			var role = roleDAO.findById(2).orElse(null);
			author.setAccount(user);
			author.setRole(role);
			artist.setIsVerify(true);
			artistDAO.save(artist);
			
			sendNotification(artist, null);
		}

		return artist;
	}

	@Override
	public void sendNotification(Artist noti,String urlImg) {

		Email email = new Email();
		email.setFrom("nguyenkhoalolm@gmail.com");
		email.setTo(noti.getAccount().getEmail());
		email.setSubject("Congratulations");
		email.setBody(sendMailTemplateSer.getContentForArtist(noti.getArtistName(),TEMPLATE));
			mailService.enqueue(email);
		}

}

