package com.lockedme;

import java.io.*;
import java.io.Serializable;

public class User implements Serializable {
	
int id;
String name;
int salary;

User(int id, String name, int salary){
	this.id = id;
	this.name = name;
	this.salary = salary;
}

public String toString() {
	
	return id+" "+name+" "+salary;
}

}

	


