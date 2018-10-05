package com.JediMasterSpring.JediBot.repo;

import com.JediMasterSpring.JediBot.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<DBUser, Integer> {

    @Query("select u from DBUser u Where u.name = ?1")
    DBUser findByName(String name);

}
