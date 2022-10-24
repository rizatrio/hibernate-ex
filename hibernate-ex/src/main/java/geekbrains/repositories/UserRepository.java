package geekbrains.repositories;

import geekbrains.entities.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(User user);

}
