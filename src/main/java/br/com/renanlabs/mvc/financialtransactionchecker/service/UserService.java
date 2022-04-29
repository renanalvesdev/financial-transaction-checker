package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.renanlabs.mvc.financialtransactionchecker.model.User;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User save(User user) {
		user.setPassword(generateBCryptPassword());
		return userRepository.save(user);
	}
	
	public User findById(Long id){
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	
	public String generateBCryptPassword() {
		    // It will generate 6 digit random Number.
		    // from 0 to 999999
		    Random rnd = new Random();
		    int number = rnd.nextInt(999999);

		    // this will convert any number sequence into 6 character.
		    String generatedPassword = String.format("%06d", number);
		    System.out.println("generated password: " + generatedPassword);
		    return  BCrypt.hashpw(generatedPassword, BCrypt.gensalt(6));
	}
	
}
