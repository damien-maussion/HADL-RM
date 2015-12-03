package fr.univnantes.m2.InterfaceComposant;

import fr.univnantes.m2.Configuration.Composant;
import fr.univnantes.m2.Configuration.EventInConfiguration;

public class PortInput extends Port {

	public PortInput(String n, Composant c) {
		super(n, c);
	}
	
	public void receice(Object o){
		System.out.println(name+ " receive : "+o );
		EventInConfiguration ev = new EventInConfiguration(this, o);
		setChanged();
		notifyObservers(ev);
	}
}
