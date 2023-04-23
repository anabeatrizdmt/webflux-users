package com.usersapi.webfluxusers.repository;

import com.usersapi.webfluxusers.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findById(String id);

    @Query("{ '_id': ?0 }, { 'status': 1 })")
    Mono<UserStatusProjection> findStatusById(String id);

    interface UserStatusProjection {
        User.Status getStatus();
    }
}
