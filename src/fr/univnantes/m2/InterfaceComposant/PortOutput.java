package fr.univnantes.m2.InterfaceComposant;

import fr.univnantes.m2.Configuration.Composant;
import fr.univnantes.m2.Configuration.EventInConfiguration;

public class PortOutput extends Port{

	public PortOutput(String n, Composant c) {
		super(n, c);
	}

	public void send(Object o){
		System.out.println(name+ " send : " +o);
		EventInConfiguration ev = new EventInConfiguration(this, o);
		setChanged();
		notifyObservers(ev);
	}
}
