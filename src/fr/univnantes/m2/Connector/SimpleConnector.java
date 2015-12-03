package fr.univnantes.m2.Connector;

import fr.univnantes.m2.Configuration.Configuration;
import fr.univnantes.m2.Configuration.EventInConfiguration;

public class SimpleConnector extends Connector{

	public SimpleConnector(String n, Configuration c) {
		super(n ,c);
	}

	@Override
	public void transmit(Object data) {
		System.out.println(name+" transmit : "+data);
		setChanged();
		notifyObservers(new EventInConfiguration(roleTo, glue(data)));
	}

	public Object glue(Object data){
		return data;
	}
}
