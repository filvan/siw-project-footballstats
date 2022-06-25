package it.uniroma3.siw.footballstats.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.footballstats.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
