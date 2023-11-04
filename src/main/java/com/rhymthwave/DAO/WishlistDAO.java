package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rhymthwave.entity.Wishlist;

public interface WishlistDAO extends JpaRepository<Wishlist, Integer>{

	@Query("SELECT COUNT(w.usertype.userTypeId) AS w FROM Wishlist w where w.usertype.account.email = ?1")
	int countWishlistByAccount(String idAccount);
}
