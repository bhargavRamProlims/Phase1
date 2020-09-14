package com.locked.service.impl;

import java.util.List;

import com.locked.dao.lockedMeDAO;
import com.locked.dao.lockedMeDAOImpl;
import com.locked.exception.lockedMeException;
import com.locked.model.lockedMe;
import com.locked.service.lockedMeService;

public class lockedMeServiceImpl implements lockedMeService{
	
	private lockedMeDAO dao = new lockedMeDAOImpl();
	
	private boolean isValidName(String name) {
		boolean b = false;
		if(name.matches("[a-zA-Z0-9]")) {
			b = true;
		}
		return b;
	}
	private boolean isValidID(int id) {
		boolean b = false;
		if(id>0 && id<1000) {
			b = true;
		}
		return b;
	}

	@Override
	public lockedMe createfile(lockedMe lockedme) throws lockedMeException {
		// TODO Auto-generated method stub
		if(isValidName(lockedme.getName())) {
			throw new lockedMeException("Entered name "+lockedme.getName()+" is invalid");
		}
		return dao.createfile(lockedme);
	}

	@Override
	public void deletefile(int id) throws lockedMeException {
		// TODO Auto-generated method stub
		if(!isValidID(id)) {
			throw new lockedMeException("Invalid ID :"+id);
		} else {
			dao.deletefile(id);
		}
		
	}

	@Override
	public List<lockedMe> sortfiles() throws lockedMeException {
		// TODO Auto-generated method stub
		return dao.sortfiles();
	}

	@Override
	public List<lockedMe> getAllFiles() throws lockedMeException {
		// TODO Auto-generated method stub
		return dao.getAllFiles();
	}

	@Override
	public lockedMe getfilesById(int id) throws lockedMeException {
		// TODO Auto-generated method stub
//		System.out.println(!isValidID(id));
		if(!isValidID(id)) {
			throw new lockedMeException("Ivalid ID :"+id);
		} else {
			return dao.getfilesById(id);
		}
	}

	@Override
	public List<lockedMe> getfilesByName(String fileName) throws lockedMeException {
		// TODO Auto-generated method stub
		if(isValidName(fileName)) {
			throw new lockedMeException("Invalid file name :"+fileName);
		} else {
			return dao.getfilesByName(fileName);
		}
	}

}
