package com.zeineb.users.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeineb.users.entities.Role;
import com.zeineb.users.entities.User;
import com.zeineb.users.repos.RoleRepository;
import com.zeineb.users.repos.UserRepository;

@Transactional //lorsque j'ai moifie l'objet ici en quittant cette methode il va valider la transaction
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User saveUser(User user) {
		user.setPassword (bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}
	
	@Override
	public User addRoleToUser(String username, String rolename) {
		User usr = userRep.findByUsername(username);
		Role role = roleRep.findByRole(rolename);
		usr.getRoles().add(role);
		return usr;
	}
	
	@Override
	public Role addRole(Role role) {
		return roleRep.save(role);
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRep.findByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return userRep.findAll();
	}
}