package br.com.alura.mvc.mudi.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.mvc.mudi.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Integer> {
	
	Optional<User> findByUsername(String username);

}