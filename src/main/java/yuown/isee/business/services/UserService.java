package yuown.isee.business.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Employee;
import yuown.isee.entity.User;
import yuown.isee.jpa.repository.UserRepository;
import yuown.isee.model.UserModel;
import yuown.isee.model.UserRegisterModel;
import yuown.isee.security.YuownGrantedAuthority;
import yuown.isee.transformer.UserTransformer;

@Service
public class UserService {

	@Value("#{'${SUPER_USERS}'.split(',')}")
	private List<String> SUPER_USERS;

	@Value("#{'${SUPER_GROUPS}'.split(',')}")
	private List<String> SUPER_GROUPS;

	@Value("#{'${isee.valid.roles}'.split(',')}")
	private List<String> ALL_ROLES;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	protected UserRepository repoService() {
		return userRepository;
	}

	protected UserTransformer transformer() {
		return userTransformer;
	}

	public UserModel getByUsername(String name) {
		org.springframework.security.core.userdetails.User userFromDB = (org.springframework.security.core.userdetails.User) jdbcUserDetailsManager.loadUserByUsername(name);
		UserModel user = transformer().transformFromSecurityUser(userFromDB);
		User dbUser = userRepository.findByUsername(name);
		user.setId(dbUser.getId());
		user.setFullName(dbUser.getFullName());
		if (SUPER_USERS.contains(name)) {
			user.setAuthorities(transformer().transformAdminAuthorities(ALL_ROLES));
		}
		return user;
	}

	public UserModel findByUsername(String username) {
		return transformer().transformTo(userRepository.findByUsername(username));
	}

	public void createUser(UserModel fromClient) throws Exception {
		fromClient.setId(userRepository.findMaxId() + 1);
		userRepository.save(transformer().transformFrom(fromClient));
	}

	public void registerUser(UserRegisterModel fromClient) throws Exception {
		User u = new User();
		u.setFullName(fromClient.getFirstName() + fromClient.getLastName());
		u.setUsername(fromClient.getUsername());
		u.setPassword(fromClient.getPassword());
		Employee emp = new Employee();
		emp.setAddress(fromClient.getAddress());
		emp.setEnabled(true);
		emp.setFirstName(fromClient.getFirstName());
		emp.setLastName(fromClient.getLastName());
		emp.setMobile(fromClient.getMobile());
		u.setEmployee(emp);
		u = userRepository.save(u);
	}

	public void removeUser(UserModel fromClient) {
		if (null != fromClient) {
			jdbcUserDetailsManager.deleteUser(fromClient.getUsername());
		}
	}

	public List<UserModel> getAll() {
		List<User> allUsers = repoService().findAll();
		List<UserModel> all = new ArrayList<UserModel>();
		for (Iterator<User> iterator = allUsers.iterator(); iterator.hasNext();) {
			UserModel userModel = transformer().transformTo(iterator.next());
			if (!SUPER_USERS.contains(userModel.getUsername())) {
				all.add(userModel);
			}
		}
		return all;
	}

	public void enable(UserModel user) {
		if (null != user) {
			UserModel fromDB = findByUsername(user.getUsername());
			if (null != fromDB && user.isEnabled() != fromDB.isEnabled()) {
				fromDB.setEnabled(user.isEnabled());
				userRepository.save(transformer().transformFrom(fromDB));
			}
		}
	}

	public List<String> getAllAuthorities() {
		return ALL_ROLES;
	}

	public List<String> getGroups() {
		List<String> allGroups = jdbcUserDetailsManager.findAllGroups();
		allGroups.removeAll(SUPER_GROUPS);
		return allGroups;
	}

	public void createGroup(String groupName) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<String> allGroups = jdbcUserDetailsManager.findAllGroups();
		groupName = groupName.toUpperCase();
		if (!allGroups.contains(groupName)) {
			jdbcUserDetailsManager.createGroup(groupName, authorities);
		}
	}

	public void deleteGroup(String groupName) {
		jdbcUserDetailsManager.deleteGroup(groupName);
	}

	public List<String> findGroupAuthorities(String groupName) {
		List<String> authorities = new ArrayList<String>();
		List<GrantedAuthority> auths = jdbcUserDetailsManager.findGroupAuthorities(groupName);
		for (GrantedAuthority grantedAuthority : auths) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities;
	}

	public void addOrRemoveGroupAuthority(String groupName, List<String> authoritiesFromClient) {
		List<String> authoritiesFromClientCopy = new ArrayList<String>();
		for (String string : authoritiesFromClient) {
			authoritiesFromClientCopy.add(string);
		}
		List<String> current = findGroupAuthorities(groupName);
		authoritiesFromClient.removeAll(current);
		for (String authority : authoritiesFromClient) {
			jdbcUserDetailsManager.addGroupAuthority(groupName, new YuownGrantedAuthority(authority));
		}
		current.removeAll(authoritiesFromClientCopy);
		for (String authority : current) {
			jdbcUserDetailsManager.removeGroupAuthority(groupName, new YuownGrantedAuthority(authority));
		}
	}

	public List<String> findUsersIngroup(String groupName) {
		return jdbcUserDetailsManager.findUsersInGroup(groupName);
	}

	public void addUserToGroup(String username, String groupName) {
		UserModel dbUser = findByUsername(username);
		if (dbUser != null) {
			jdbcUserDetailsManager.addUserToGroup(username, groupName);
		}
	}

	public void removeUserFromGroup(String username, String groupName) {
		UserModel dbUser = findByUsername(username);
		if (dbUser != null) {
			jdbcUserDetailsManager.removeUserFromGroup(username, groupName);
		}
	}

	public void updateUser(UserModel model) {
		userRepository.save(transformer().transformFrom(model));
	}

	public void updateUser(UserModel model, UserModel fromHeader) {
		model.setEnabled(true);
		if (StringUtils.isNotEmpty(model.getFullName())) {
			model.setFullName(model.getFullName().toUpperCase());
		} else {
			model.setFullName(fromHeader.getFullName());
		}
		if (StringUtils.isNotEmpty(model.getPassword())) {
			model.setPassword(model.getPassword());
		} else {
			model.setPassword(fromHeader.getPassword());
		}
		updateUser(model);
	}

	public UserModel getById(int id) {
		User u = repoService().findById(id);
		UserModel uModel = transformer().transformTo(u);
		return uModel;
	}
}