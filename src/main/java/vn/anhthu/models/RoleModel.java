package vn.anhthu.models;

import java.io.Serializable;

public class RoleModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String roleid;
	private String rolename;
	public RoleModel() {
		super();
	}
	public RoleModel(String roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
