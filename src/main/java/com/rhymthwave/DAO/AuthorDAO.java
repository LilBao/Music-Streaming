package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhymthwave.entity.Author;

public interface AuthorDAO extends JpaRepository<Author, Long>{

	@Query("SELECT a FROM Author a WHERE a.account.email = :email")
	Author findByEmailAccount(@Param("email") String email);
	
	@Query(value="SELECT * FROM AUTHOR WHERE IDROLE = :idRole AND EMAIL = :email",nativeQuery = true)
	Author findAuthor(@Param("idRole") Integer idRole, @Param("email") String email);
}
