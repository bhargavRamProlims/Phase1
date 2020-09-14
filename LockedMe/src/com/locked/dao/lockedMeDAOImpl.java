package com.locked.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.locked.exception.lockedMeException;
import com.locked.model.lockedMe;

public class lockedMeDAOImpl implements lockedMeDAO{

	private static Map<Integer , lockedMe> lockedMeMap = new HashMap<>();
	private static int count;

	@Override
	public lockedMe createfile(lockedMe lockedme) throws lockedMeException {
		String s = "var\\"+lockedme.getName()+".txt";
		File lockedMeFile = new File(s);
		try {
			if(lockedMeFile.createNewFile()) {
				System.out.println("New file is successfully created.");
				lockedme.setId(++count);
				lockedMeMap.put(lockedme.getId(), lockedme);
			} else {
				System.out.println("the file already exists...");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return lockedme;
	}

	@Override
	public void deletefile(int id) throws lockedMeException {
		// TODO Auto-generated method stub
		if(lockedMeMap.containsKey(id)) {
			String s = "var\\"+lockedMeMap.get(id).getName();
			File lockedMeFile = new File(s);
			lockedMeFile.delete();
		} else {
			throw new lockedMeException("Entered Id "+id+" doesn't exist");
		}

	}

	@Override
	public List<lockedMe> sortfiles() throws lockedMeException {
		// TODO Auto-generated method stub
		List<lockedMe> lockedMeList = lockedMeMap.values().stream()
				.collect(Collectors.toList());
		Collections.sort(lockedMeList,(f1,f2) -> f1.getName().compareTo(f2.getName()));

		if(lockedMeList.size()==0) {
			throw new lockedMeException("there are no files in the directory. plese create one");
		}

		return lockedMeList;
	}

	@Override
	public List<lockedMe> getAllFiles() throws lockedMeException {
		// TODO Auto-generated method stub
		List<String> fileName = new ArrayList<>();
		File f = new File("var");
		for(String l:f.list()) {
			fileName.add(l);
		}

		if(fileName.size()==0 && lockedMeMap.size()==0) {
			throw new lockedMeException("No files as of now. please create one!");
		} else {
			lockedMeMap.clear();
			count = 0;
			for(String i:fileName) {
				lockedMe lockedme1 = new lockedMe();
				lockedme1.setName(i);
				lockedme1.setId(++count);
				lockedMeMap.put(lockedme1.getId(), lockedme1);

			}
		}
		return new ArrayList<>(lockedMeMap.values());
	}

	@Override
	public lockedMe getfilesById(int id) throws lockedMeException {
		// TODO Auto-generated method stub
		getAllFiles();
		if(lockedMeMap.containsKey(id)) {
			return lockedMeMap.get(id);
		} else {
			throw new lockedMeException("entered id "+id+" doesn't exist");
		}
	}

	@Override
	public List<lockedMe> getfilesByName(String fileName) throws lockedMeException {
		// TODO Auto-generated method stub
		String f1 = new String(fileName+".txt");
		getAllFiles();

		List<lockedMe> lockedMeList = lockedMeMap.values().stream()
				.filter(s -> s.getName().equals(f1)).collect(Collectors.toList());
		if(lockedMeList.size()==0) {
			throw new lockedMeException("No file is found with the given id");
		}
		return lockedMeList;
	}

}
