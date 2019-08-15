package com.isgm.chatting.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isgm.chatting.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	User findByName(String name);
	
	@Query("Select u from User u where u.name = :name")
	List<User> findByOwnQuery(@Param("name") String name);
	
}
