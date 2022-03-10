package com.lockedme;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Exception;

public class MainApp {
	

	public static void main(String[] args) throws Exception{
		 Welcome wel = new Welcome();
		 wel.mostWelcome();
		 
		
		 int choice = -1;
		 int num = 0;
		 int id = 0;
		 String name = "";
		 int salary = 0;
		 Scanner sc = new Scanner(System.in);
		 Scanner sc1 = new Scanner(System.in);
		 File file = new File("User.txt");
		 ArrayList<User> al = new ArrayList<User>();
		 ObjectOutputStream oos = null;
		 ObjectInputStream ois = null;
		 ListIterator li = null;
		 boolean error = false;
		 
		 
		 
		
		 if(file.isFile()) {
			 
			 ois = new ObjectInputStream(new FileInputStream(file));
			 al = (ArrayList<User>)ois.readObject();
			 ois.close();
			 
		 }
		 
		 
		 //do {
		 while(choice!=0) { 
				
			 try {
			 System.out.println("1. INSERT USER INFO");
			 System.out.println("2. DISPLAY USER INFO");
			 System.out.println("3. SEARCH USER INFO");
			 System.out.println("4. DELETE USER INFO");
			 System.out.println("5. UPDATE USER INFO");
			 System.out.println("6. SORT BY USER ID - ON SCREEN");
			 System.out.println("7. SORT BY USER ID - IN FILE");
			 System.out.println("8. SORT BY USER NAME - ON SCREEN");
			 System.out.println("9. SORT BY USER NAME - IN FILE");
			 System.out.println("10. SORT BY USER SALARY (DESC.) - ON SCREEN");
			 System.out.println("11. SORT BY USER SALARY (ASC.) - IN FILE");
			 System.out.println("0. EXIT FROM APPLICATION");
			 System.out.println("ENTER YOUR CHOICE:- ");
			
			 choice = sc.nextInt();
			 }catch(Exception e) {
				 System.out.println("Please enter valid choice in number format from above list");

			break;	 	
			 }
			
			
			 switch(choice) {
			 
			 	case 1:
			 		
			 		System.out.println("Enter how many number of users you want to add:- ");
			 		
			 		 try {
			 			num = sc.nextInt();
						 }catch(Exception e) {
							 System.out.println("Please enter valid input in number");
						break;	 	
						 }
						
			 		
			 		for(int i=0; i<num; i++) {
			 			
				 		System.out.print("Enter User ID:- ");
				 		try {
				 			id = sc.nextInt();
							 }catch(Exception e) {
								 System.out.println("Please enter valid input in number  :"+e);
							break;	 	
							 }
				 		 
				 		
				 		System.out.print("Enter User Name:- ");
				 		 name = sc1.nextLine();
				 		Pattern p = Pattern.compile("[^a-zA-Z ]", Pattern.CASE_INSENSITIVE);
				 		Matcher m = p.matcher(name);
				 		boolean check = m.find();
				 		if(check)
				 		{
				 			System.out.println("Name input field contains number and special character are not allowed:");
				 		break;
				 		}
				 			
				 		
				 		System.out.print("Enter User Salary:- ");
				 		
				 		try {
				 		 salary = sc.nextInt();
								 }catch(Exception e) {
									 System.out.println("Please enter valid input in number");
								break;	 	
								 }
				 		al.add(new User(id, name, salary));
				 		
			 		
			        }	
			 		oos = new ObjectOutputStream(new FileOutputStream(file));
			 		oos.writeObject(al);
			 		oos.close();
			 	
			 		
			 		break;
			 		
			 	case 2:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 3:
			 		
			 		if(file.isFile()) {
			 		    ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
				 		boolean found = false;
				 		System.out.println("Enter UserID to Search:- ");
				 		
				 		try {
				 			id = sc.nextInt();
							 }catch(Exception e) {
								 System.out.println("Please enter valid input in number");
							System.exit(0);	 	
							 }
							
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext()) {
				 			User us = (User)li.next();
				 			if(us.id == id) {
				 				System.out.println(us);
				 				found = true;
				 			}
				 		}
				 		if(!found)
				 			System.out.println("Record Not Found");
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 4:
			 		
			 		if(file.isFile()) {
			 		    ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
				 		boolean found = false;
				 		System.out.print("Enter UserID to Delete:- ");
				 		try {
				 			id = sc.nextInt();
							 }catch(Exception e) {
								 System.out.println("Please enter valid input in number");
							break;	 	
							 }
							
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext()) {
				 			User us = (User)li.next();
				 			if(us.id == id) {
				 				li.remove();
				 				found = true;
				 			}
				 		}
				 		if(found) {
				 			
				 			oos = new ObjectOutputStream(new FileOutputStream(file));
				 			oos.writeObject(al);
				 			oos.close();
				 			System.out.println("Record Deleted Successfully");
				 		}
				 			
				 		else {
				 			
				 			System.out.println("Record Not Found");
				 		}
				 		
				 		System.out.println("-------------------------------------------");
			 		
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 5:
			 		
			 		if(file.isFile()) {
			 		    ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
				 		boolean found = false;
				 		System.out.print("Enter UserID to Update:- ");
				 		try {
				 			 id = sc.nextInt();
							 }catch(Exception e) {
								 System.out.println("Please enter valid input in number");
							break;	 	
							 }
							
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext()) {
				 			User us = (User)li.next();
				 			if(us.id == id) {
				 				System.out.print("Enter New User Name:- ");
				 				name = sc1.nextLine();
				 				
				 				System.out.print("Enter New User Salary:- ");
				 				salary = sc.nextInt();
				 				
				 				li.set(new User(id, name, salary));
				 				found = true;
				 			}
				 		}
				 		if(found) {
				 			
				 			oos = new ObjectOutputStream(new FileOutputStream(file));
				 			oos.writeObject(al);
				 			oos.close();
				 			System.out.println("Record Updated Successfully");
				 		}
				 			
				 		else {
				 			
				 			System.out.println("Record Not Found");
				 		}
				 		
				 		System.out.println("-------------------------------------------");
			 		
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 6:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us1.id - us2.id;
							}
						});
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 7:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us1.id - us2.id;
							}
						});
						
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 8:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us1.name.compareTo(us2.name);
							}
						});
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 9:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us1.name.compareTo(us2.name);
							}
						});
						
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 10:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us2.salary - us1.salary;
							}
						});
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 		
			 	case 11:
			 		
			 		if(file.isFile()) {
				 		ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<User>)ois.readObject();
						ois.close();
						
						Collections.sort(al, new Comparator<User>() {
							
							public int compare(User us1, User us2) {
								
								return us1.salary - us2.salary;
							}
						});
						
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						
				 		System.out.println("-------------------------------------------");
				 		li = al.listIterator();
				 		while(li.hasNext())
				 			System.out.println(li.next());
				 		System.out.println("-------------------------------------------");
			 		} else {
			 			
			 			 System.out.println("File Not Exists");
			 		}
			 		break;
			 	
			 	case 0:
			 	System.out.println("THANK YOU...WELCOME AGAIN!");
			 	break;
			 	
			 	default:
			 	System.out.println("Your choice is wrong");

			 }
		 
	}
		 
	}
}
	
	
