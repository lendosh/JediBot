package com.JediMasterSpring.JediBot.services;

import com.JediMasterSpring.JediBot.model.DBUser;
import com.JediMasterSpring.JediBot.repo.UserRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepo userRepo;

    @Inject
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Transactional
    public DBUser create(int id, String name, int level) {
        final DBUser user = new DBUser(id, name, level);
        return userRepo.save(user);
    }

    @Transactional
    public void update(DBUser user) {
        userRepo.save(user);
    }

    @Transactional
    public void delete(int id) {
        final Optional<DBUser> user = userRepo.findById(id);
        if (!user.isPresent()) {
            return;
        }
        userRepo.delete(user.get());
    }

    @Transactional
    public DBUser findById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Transactional
    public DBUser findByName(String name) {
        return userRepo.findByName(name);
    }

    public List<DBUser> findAll() {
        return userRepo.findAll();
    }

    public boolean validate(int id, int level) {
        if (level == 0) {
            return true;
        }

        final DBUser user = findById(id);
        return (user != null) && (user.getLevel() >= level);
    }

}
