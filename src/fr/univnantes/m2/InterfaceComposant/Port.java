package fr.univnantes.m2.InterfaceComposant;

import fr.univnantes.m2.Configuration.Composant;

public abstract class Port extends InterfaceComposant{

	public Port(String n, Composant c) {
		super(n,c);
		addObserver(c);
	}

}
