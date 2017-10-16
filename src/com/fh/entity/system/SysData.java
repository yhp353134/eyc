package com.fh.entity.system;

import java.io.Serializable;

public class SysData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int datakey;
	private String dataname;
	private int datagroup;
	private String groupname;
	public int getDatakey() {
		return datakey;
	}
	public void setDatakey(int datakey) {
		this.datakey = datakey;
	}
	public String getDataname() {
		return dataname;
	}
	public void setDataname(String dataname) {
		this.dataname = dataname;
	}
	public int getDatagroup() {
		return datagroup;
	}
	public void setDatagroup(int datagroup) {
		this.datagroup = datagroup;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	
	
	
	
}
