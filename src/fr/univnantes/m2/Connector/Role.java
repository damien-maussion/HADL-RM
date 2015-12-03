package fr.univnantes.m2.Connector;

import java.util.Observable;

public abstract class Role extends Observable{
	protected String name;

	public Role(String n){
		name=n;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString(){
		return name;
	}
}
