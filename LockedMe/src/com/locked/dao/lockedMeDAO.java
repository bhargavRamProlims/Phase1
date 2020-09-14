package com.locked.dao;

import java.util.List;

import com.locked.exception.lockedMeException;
import com.locked.model.lockedMe;

public interface lockedMeDAO {
	
	public lockedMe createfile(lockedMe lockedme) throws lockedMeException;
	public void deletefile(int id) throws lockedMeException;
	public List<lockedMe> sortfiles() throws lockedMeException;
	
	public List<lockedMe> getAllFiles() throws lockedMeException;
	public lockedMe getfilesById(int id) throws lockedMeException;
	public List<lockedMe> getfilesByName(String fileName) throws lockedMeException;

}
