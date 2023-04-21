package com.usersapi.webfluxusers.repository;

import com.usersapi.webfluxusers.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UserRepository {


    private static final List<User> users_db = new CopyOnWriteArrayList<>();

    public void save(User user) {
        users_db.add(user);
    }

    public Optional<User> findById(String id) {
        return users_db.stream()
                .filter(entity -> entity.id().equals(id))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(users_db);
    }
}
