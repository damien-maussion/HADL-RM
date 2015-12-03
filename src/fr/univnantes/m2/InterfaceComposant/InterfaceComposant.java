package fr.univnantes.m2.InterfaceComposant;

import java.util.Observable;

import fr.univnantes.m2.Configuration.Composant;

public abstract class InterfaceComposant extends Observable{
	protected String name;

	public InterfaceComposant(String n,Composant c){
		name=n;
		//addObserver(c);
	}
	
	// TODO: add visibility
	
	public String getName() {
		return name;
	}
	
	public String toString(){
		return name;
	}
}
