package ecomm.com.example.ecomm.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import ecomm.com.example.ecomm.Modals.User;

public interface UserRepo extends MongoRepository<User, String> {

    User findUserByEmail(String email);
    User findUserByUsername(String username);
    Boolean existsByEmail(String email);
}
