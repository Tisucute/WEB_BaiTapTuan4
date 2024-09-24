package vn.anhthu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.anhthu.configs.DBConnectMySQL;
import vn.anhthu.dao.IUserDao;
import vn.anhthu.models.UserModel;
import vn.anhthu.dao.impl.UserDaoImpl;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public List<UserModel> findAll() {
		String sql ="select * from users";
		List<UserModel> list = new ArrayList<UserModel>(); //Tạo 1 List để truyền dữ liệu
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection(); //kết nối database 
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next() /*Next từng DÒNG tới cuối bảng*/) {
                list.add(new UserModel(
                		rs.getInt("id"), 
                		rs.getString("username"), 
                		rs.getString("password"), 
                		rs.getString("email"), 
                		rs.getString("phone"),
                		rs.getString("images"),
                		rs.getString("fullname"),
                		rs.getInt("roleid"),
                		rs.getDate("createDate")        		                		
                		)); //Add vào
                return list;
            }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserModel findById(int id) {
String sql = "SELECT * FROM users WHERE id = ? ";
		
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(id, username, password, images, fullname, email, phone, roleid, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			//conn = new DBConnectSQL().getConnection(); //kết nối database 
			ps = conn.prepareStatement(sql);//ném câu sql vào cho thực thi
			
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.getImages());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, user.getCreateDate());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 public static void main(String[] args) {
		 	
		 try {
			 IUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.findAll());	 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	        //IUserDao userDao = new UserDaoImpl();
	        //System.out.println(userDao.findByUserName("anhthu"));
	        //List<UserModel> list = userDao.findAll();
	       // for (UserModel user : list) {
	       //     System.out.println(user);
	       // }
	    }
	 //Test chương trình. Kích phải chuột chọn run as -> java application

	@Override
	public boolean checkExistUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";

        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUserName(username);
        if (user != null && password.equals(user.getPassword())) {
            System.out.println("LOGIN SUCCESS");
            return user;
        } else {
            System.out.println("LOGIN ERROR");
        }
        return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";

        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean register(String username, String email, String password, String fullname, String image, String phone,
			int roleid, Date createdate) {
		if (this.checkExistUsername(username) || this.checkExistEmail(email)) {
            System.out.println("REGISTER ERROR");
            return false;
        }

        this.insert(new UserModel(0, username, email, password, fullname, image, phone, roleid, createdate));
        System.out.println("REGISTER SUCCESS");
        return true;
	}

	@Override
	public boolean update(UserModel user) {
		String sql = "UPDATE users SET username = ?, password = ?, email = ?, roleid = ?, phone = ?, fullname = ? WHERE id = ?";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRoleid());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getFullname());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

}
