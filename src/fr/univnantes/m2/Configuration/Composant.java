package fr.univnantes.m2.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.univnantes.m2.InterfaceComposant.InterfaceComposant;
import fr.univnantes.m2.InterfaceComposant.Port;
import fr.univnantes.m2.InterfaceComposant.ServiceOutput;

public class Composant extends Observable implements Observer{
	protected String name;
	
	protected List<Property> properties;
	protected List<TechnicalConstraint> constraints;
	
	protected List<InterfaceComposant> interfaceComposants;
	
	protected Configuration configuration;
	
	public List<InterfaceComposant> getInterfaceComposants() {
		return interfaceComposants;
	}

	public Composant(String n) {
		name=n;
		interfaceComposants = new ArrayList<>();
		properties = new ArrayList<>();
		constraints = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public List<TechnicalConstraint> getConstraints() {
		return constraints;
	}
	
	public void addProperty(Property p){
		properties.add(p);
	}
	
	public void addConstraint(TechnicalConstraint c){
		constraints.add(c);
	}
	
	public void addInterfaceComposant(InterfaceComposant i){
		interfaceComposants.add(i);
	}

	
	public void setConfiguration(Configuration c){
		if (configuration!=null)
			configuration.removeComposant(this);
		configuration=c;
		deleteObservers();
		addObserver(c);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println(this + " has been notified : "+arg);
		setChanged();
		notifyObservers(arg);
	}
	
	public Port getPortByName(String name){
		for (InterfaceComposant i : interfaceComposants){
			if (i.getName().equals(name))
				return (Port) i;
		}
		return null;
	}
	
	public void callService(String serviceName, Object data){
		for (InterfaceComposant i : interfaceComposants){
			if (i.getName().equals(serviceName)){
				((ServiceOutput) i).exec(data);
			}
		}
	}

	public String toString(){
		return name;
	}
}
