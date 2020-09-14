package com.locked.main;

import java.util.List;
import java.util.Scanner;

import com.locked.exception.lockedMeException;
import com.locked.model.lockedMe;
import com.locked.service.lockedMeService;
import com.locked.service.impl.lockedMeServiceImpl;

public class lockedMeMain {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to LockeMe APP");
		System.out.println("developer : Bhargav Ram");
		int ch = 0;
		lockedMeService service = new lockedMeServiceImpl();
		System.out.println("\nMenu");
		System.out.println("-----------------------------");
		System.out.println("1)Show all files");
		System.out.println("2)Create/Delete/Sort files");
		System.out.println("3)exit");
		ch = Integer.parseInt(sc.nextLine());
		switch (ch) {
		case 1:
			List<lockedMe> lockedMeList;
			try {
				lockedMeList = service.getAllFiles();
				if(lockedMeList!=null && lockedMeList.size()>0) {
					System.out.println("we found "+lockedMeList.size()+" file(s)");
					for(lockedMe l:lockedMeList) {
						System.out.println(l.getId()+")"+" "+l.getName());
					}
				} else {
					System.out.println("No files are available, please create files!");
				}
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			menu();

			break;
		case 2:
			subMenu();
			break;

		case 3:
			System.out.println("Thank you for using the LockedMe App...");
			System.out.println("Exiting...");
			break;
		default:
			System.out.println("entered choice is invalid.");
			System.out.println("Please choose again");
			menu();
			break;
		}


	}
	public static void menu() {
		int ch = 0;
		System.out.println("\nMenu");
		System.out.println("-----------------------------");
		System.out.println("1)Show all files");
		System.out.println("2)Create/Delete/Sort files");
		System.out.println("3)exit");

		lockedMeService service = new lockedMeServiceImpl();
		ch = Integer.parseInt(sc.nextLine());
		switch (ch) {
		case 1:
			List<lockedMe> lockedMeList;
			try {
				lockedMeList = service.getAllFiles();
				if(lockedMeList!=null && lockedMeList.size()>0) {
					System.out.println("we found "+lockedMeList.size()+" file(s)");
					for(lockedMe l:lockedMeList) {
						System.out.println(l.getId()+")"+" "+l.getName());
					}
				} else {
					System.out.println("no files are available, please create files!");
				}
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			menu();

			break;
		case 2:
			subMenu();
			break;

		case 3:
			System.out.println("Thank you for using the LockedMe App!");
			System.out.println("Exiting...");
			break;
		default:
			System.out.println("entered choice is invalid.");
			System.out.println("Please choose again");
			menu();
			break;
		}
	}
	public static void subMenu() {
		int ch = 0;
		System.out.println("\nFile - Menu");
		System.out.println("-----------------------------");
		System.out.println("1)Create file");
		System.out.println("2)Delete file");
		System.out.println("3)Sort the files");
		System.out.println("4)view all files");
		System.out.println("5)search file through Name");
		System.out.println("6)search file through ID");
		System.out.println("7)Go back to previous Menu");
		ch = Integer.parseInt(sc.nextLine());

		lockedMeService service = new lockedMeServiceImpl();
		switch (ch) {
		case 1:
			System.out.println("Enter the file name below to create the file ");
			lockedMe lf = new lockedMe();
			lf.setName(sc.nextLine());
			try {
				lf = service.createfile(lf);
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 2:
			System.out.println("Showing files:");
			List<lockedMe> lockedMeList;
			try {
				lockedMeList = service.getAllFiles();
				if(lockedMeList!=null && lockedMeList.size()>0) {
					for(lockedMe l:lockedMeList) {
						System.out.println(l.getId()+")"+" "+l.getName());
					}
					System.out.println("please enter the file ID you want to delete");
					int id = Integer.parseInt(sc.nextLine());
					System.out.println("\nDo you want to delete the file :"+lockedMeList.get(id-1).getName());
					System.out.println("enter the id again to confirm the deletion");
					int i = Integer.parseInt(sc.nextLine());
					if(id==i) {
						service.deletefile(id);
						System.out.println(lockedMeList.get(id-1).getName()+" is successfully deleted");
					} else {
						System.out.println("deletion is Aborted...");
					}
				} else {
					System.out.println("No files are currently available.");
					System.out.println("Please create a file!");
				}
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 3:
			try {
				lockedMeList = service.getAllFiles();
				if(lockedMeList!=null && lockedMeList.size()>0) {
					System.out.println("Sorting the files...");
					service.sortfiles();
					System.out.println("\nFiles are now sorted\n");
					for(lockedMe l:lockedMeList) {
						System.out.println(l.getId()+")"+" "+l.getName());
					}

				} else {
					System.out.println("No files are available, please create files!");
				}			
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 4:
			try {
				lockedMeList = service.getAllFiles();
				if(lockedMeList!=null && lockedMeList.size()>0) {
					System.out.println("we found "+lockedMeList.size()+" file(s)");
					for(lockedMe l:lockedMeList) {
						System.out.println(l.getId()+")"+" "+l.getName());
					}
				} else {
					System.out.println("no files are available, please create files!");
				}
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 5:
			System.out.println("Please enter the file name to search");
			String s = sc.nextLine();
			try {
				List<lockedMe> lockedMeName = service.getfilesByName(s);
				System.out.println("\nthe Search is successfull");
				for(lockedMe l:lockedMeName) {
					System.out.println("the file name is "+l.getName()+" with ID :"+l.getId()+"\n");
				}
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 6:
			System.out.println("Please enter the ID to search");
			int id = Integer.parseInt(sc.nextLine());
			try {
				lockedMe lockedMeid = service.getfilesById(id);
				if(lockedMeid.getId()==id) {
					System.out.println("Search is successfull");
					System.out.println("the file name is "+lockedMeid.getName());
				} else {
					System.out.println("Search failed...");
				}
				System.out.println();
			} catch (lockedMeException e) {
				System.out.println(e);
			}
			subMenu();
			break;
		case 7:
			System.out.println("\nreturning to the Main menu...");
			menu();
			break;

		default:

			System.out.println("entered choice is invalid.");
			System.out.println("Please choose again");
			subMenu();
			break;
		}

	}

}
