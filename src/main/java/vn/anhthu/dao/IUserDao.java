package vn.anhthu.dao;

import java.sql.Date;
import java.util.List;

import vn.anhthu.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	boolean checkExistUsername(String username);

	UserModel login(String username, String password);

	boolean checkExistEmail(String email);
	
	boolean register(String username, String email, String password, String fullname, String image, String phone,
			int roleid, Date createdate);
	boolean update(UserModel user);
}
