package fr.univnantes.m2.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.univnantes.m2.Connector.Connector;
import fr.univnantes.m2.Connector.RoleFrom;
import fr.univnantes.m2.Connector.RoleTo;
import fr.univnantes.m2.InterfaceComposant.Port;
import fr.univnantes.m2.InterfaceComposant.PortInput;
import fr.univnantes.m2.InterfaceComposant.PortOutput;
import fr.univnantes.m2.Link.Attachment;
import fr.univnantes.m2.Link.Binding;

public class Configuration extends Composant implements Observer{
	
	List<Composant> composants;
	List<Connector> connectors;
	List<Attachment> attachments;
	List<Binding> bindings;	
	
	public Configuration(String n) {
		super(n);
		composants = new ArrayList<>();
		connectors = new ArrayList<>();
		attachments = new ArrayList<>();
		bindings = new ArrayList<>();
	}

	public void addComposant(Composant c){
		composants.add(c);
		c.setConfiguration(this);
	}
	
	public void removeComposant(Composant c){
		composants.remove(c);
	}
	
	public void addConnector(Connector c){
		connectors.add(c);
	}
	
	public void removeConnector(Connector c){
		connectors.remove(c);
	}
	
	public void addAttachment(Attachment a){
		attachments.add(a);
	}
	
	public void addBinding(Binding b){
		//TODO verify b
		bindings.add(b);
	}
	
	@Override
	public Port getPortByName(String name){
		Port p = super.getPortByName(name);
		if (p!=null)
			return p;
		for (Composant c: composants){
			p = c.getPortByName(name);
			if (p!=null)
				return p;
		}
		return null;
	}
	
	public void update(Observable o, Object arg) {
		
		//System.out.println(this + " has been notified : "+arg);
		
		boolean treated = false;
		
		EventInConfiguration ev = (EventInConfiguration) arg;
		if (ev.getSrc() instanceof PortOutput){
			PortOutput p = (PortOutput) (ev.getSrc());
			for (Attachment att : attachments){
				if (att.getPort().equals(p)){
					treated = true;
					RoleFrom rf = (RoleFrom) att.getRole();
					rf.receive(ev.getArg());
				}	
			}
			for (Binding bind: bindings){
				if (p.equals(bind.getPortComposant())){
					treated = true;
					PortOutput pconf = (PortOutput) bind.getPortConfiguration();
					pconf.send(ev.getArg());
				}	
			}
		}
		else if (ev.getSrc() instanceof PortInput){
			PortInput p = (PortInput) (ev.getSrc());
			for (Binding bind: bindings){
				if (p.equals(bind.getPortConfiguration())){
					treated = true;
					PortInput pcomp = (PortInput) bind.getPortComposant();
					pcomp.receice(ev.getArg());
				}	
			}
		}
		else if (ev.getSrc() instanceof RoleTo){
			RoleTo r = (RoleTo) (ev.getSrc());
			for (Attachment a : attachments){
				if (a.getRole().equals(r)){
					treated = true;
					PortInput p = (PortInput) a.getPort();
					p.receice(ev.getArg());
				}	
			}
		}
		
		if (!treated){
			setChanged();
			notifyObservers(ev);
		}
		
	}
}
