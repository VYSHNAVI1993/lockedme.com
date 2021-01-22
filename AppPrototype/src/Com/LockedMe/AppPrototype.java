package Com.LockedMe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AppPrototype {
	
	static Scanner sc = new Scanner(System.in);
	
	public static boolean welcome() {
		
		System.out.println("**************************************************\n");
		System.out.println("**************************************************\n");
		System.out.println("\tWELCOME TO LOCKEDME.COM\n");
		System.out.println("**************************************************\n");
		System.out.println("**************************************************\n");
		System.out.println("***************DEVELOPER DETAILS***************");
		System.out.println("----------Name: GHANTA VYSHNAVI----------\n");
		System.out.println("----------Company: Lockers Pvt Ltd----------\n");
		System.out.println("----------Phone Number: 1234567890----------\n");
		System.out.println("----------Email: vyshnavireddyghanta@gmail.com----------\n");
		System.out.println("This Application is useful for:\n\n#Sorting files in the directory and its subdirectory \n#Adding file to the directory \n#Deleting file from the directory \n#Searching a file in the directory\n");
		System.out.println("\n\n\n Press YES to proceed -> ->");
		
		String str = sc.next();
		while(true){
		if (str.equalsIgnoreCase("YES"))		
		{
			return true;
		}
		else{
			System.out.println("Invalid Entry !! Please press YES to continue");
			str = sc.next();
		}
	}
}



public static void choices() {
	while(true) {
		System.out.println("\n\n ***** Enter your choice ***** \n\n");
		System.out.println("Press 1: List Current file names in ascending order");
		System.out.println("Press 2: List User interfaces");
		System.out.println("Press 3: Close Application \n");
		
		int n = sc.nextInt();
		switch(n) {
		
		case 1:
		List<String>list = new ArrayList<>();
		list = listFiles();
		System.out.println("\n ***** List of files in rootfolder  and its subfolder *****\n");
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		break;
					
		case 2:
		userInterfaces();
		break;
		
		case 3:
		System.out.println("***** Application Closed *****");
		System.exit(0);
		break;
		
		default:
			System.out.println("Wrong Choice !! Please select the listed choices. ");
		
		}
	}
}

public static ArrayList<String>listFiles() {
	System.out.println("Enter Root directory path");
	String location = sc.next();
	File file = null;
	try {
		file = new File(location);
	}
	catch(NullPointerException e) {
		System.out.println("Please enter correct Root directory path");
		e.printStackTrace();
	}
	File[] fs = file.listFiles();
	ArrayList<String>list = new ArrayList<>();
	list = read(fs, list);
	Collections.sort(list);
	return list;
}


public static ArrayList<String>read(File file[], ArrayList<String>list) {
	
	for(File eachfile: file) {
		list.add(eachfile.getName());
		if(eachfile.isDirectory()) {
			File fs[] = eachfile.listFiles();
			read(fs, list);		
			
		}
	}
	return list;
}


public static void userInterfaces() {
	
	System.out.println("\n\n ***** Enter your choice ***** \n\n");
	System.out.println("Press 1: Add file to existing directory list");
	System.out.println("Press 2: Delete user specified file  from existing directory list");
	System.out.println("Press 3: Search user specified file  from main directory ");
	System.out.println("Press 4: Navigate back to main context\n");
	
	int n =sc.nextInt();
	
	switch(n) {
	
	case 1:
		addFile();
		break;
		
	case 2:
		deleteFile();
		break;
		
	case 3:
		searchFile();
		break;
		
	case 4:
		choices();
		break;
		
	default:
		System.out.println(" Entered Wrong Choice !!");
	
	}
}

public static void addFile() {
	
	System.out.println(" Enter existing directory path with new file name\n Example: F:\\temp\\vysh.txt \n");
	Path path = Paths.get(sc.next());
	List<String>list = new ArrayList<>();
	try {
		Files.write(path, list, StandardOpenOption.CREATE_NEW );
	}
	catch(IOException e) {
		System.out.println("\n FileExists !");
		e.printStackTrace();
	}
	
	System.out.println("\n File Created !");
	
 }



public static void deleteFile() {
	
	System.out.println(" Enter file to be deleted with absolute path \n");
	Path path = Paths.get(sc.next());
	
	try {
		Files.deleteIfExists(path);
		System.out.println("\n File Deleted !");
	}
	
	catch(DirectoryNotEmptyException e) {
		e.printStackTrace();
	}
	
	catch(FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
	
	catch(IOException e) {
		e.printStackTrace();
	}
	
 }


public static void searchFile() {

	System.out.println(" Enter file to be searched with extension Ex:test.txt");
	
	{
		String string = sc.next();
		ArrayList<String>list = new ArrayList<>();
		list = listFiles();
        
		if(list.contains(string)) {
			System.out.println("\n FileExists !");	
		}
		else
			System.out.println("\n File do not Exists !");

		
	}

}

public static void main(String[] args) {
	boolean check = welcome();
	if(check) {
		choices();
		
	}		
  }
}



	



