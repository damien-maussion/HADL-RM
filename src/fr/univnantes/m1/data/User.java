package fr.univnantes.m1.data;

public class User {

	private String name;
	private boolean allowed;
	
	public User(String n, boolean a){
		name=n;
		allowed=a;
	}
	
	public String toString(){
		return "{name:"+name + ", allowed:"+allowed+"}";
	}

	public String getName() {
		return name;
	}

	public boolean isAllowed() {
		return allowed;
	}
}
