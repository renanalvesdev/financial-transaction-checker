package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanlabs.mvc.financialtransactionchecker.model.User;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findById(Long id){
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
}
