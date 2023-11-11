package com.rhymthwave.API.GraphQL;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.WishlistService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.UserType;
import com.rhymthwave.entity.Wishlist;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PlaylistGraphQL {
	private final CRUD<Playlist, Long> crudPlaylist;
	
	private final CRUD<Account, String> crudAccount;
	
	private final WishlistService wishlistSer;
		
	@QueryMapping("playlistById")
	public Playlist findPlaylistById(@Argument("playlistId") Long id) {
		return crudPlaylist.findOne(id);
	}
	
	@QueryMapping("myWishlist")
	public List<Wishlist> findMyWishlist(@Argument("email") String email){
		Account account = crudAccount.findOne(email);
		UserType basic = account.getUserType().get(0);
		UserType premium = account.getUserType().get(1);
		List<Wishlist> list = wishlistSer.myWishlist(basic);
		if(premium !=null) {
			list.addAll( wishlistSer.myWishlist(premium));
		}
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
}
