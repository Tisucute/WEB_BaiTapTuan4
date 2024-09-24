package vn.anhthu.services;

import vn.anhthu.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	UserModel findByUsername(String username);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean register(String email, String password, String username, String fullname, String phone);
	boolean checkExistEmail(String email);
	void insert(UserModel user);
	boolean update(UserModel user);
	void updatePassword(String username, String password);
}
