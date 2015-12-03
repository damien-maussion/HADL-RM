package fr.univnantes.m1;

import java.util.Observable;

import fr.univnantes.m2.Configuration.Composant;
import fr.univnantes.m2.Configuration.EventInConfiguration;
import fr.univnantes.m2.InterfaceComposant.PortInput;
import fr.univnantes.m2.InterfaceComposant.PortOutput;
import fr.univnantes.m2.InterfaceComposant.ServiceInput;
import fr.univnantes.m2.InterfaceComposant.ServiceOutput;

public class ConnectionManager extends Composant{

	public ConnectionManager() {
		super("ConnectionManager");
		
		ServiceInput si1 = new ServiceInput("ExternalSocketSR", this);
		PortInput pi1 = new PortInput("ExternalSocketPR", this);
		si1.usePort(pi1);
		
		ServiceOutput so1 = new ServiceOutput("ExternalSocketSF", this);
		PortOutput po1 = new PortOutput("ExternalSocketPF", this);
		so1.usePort(po1);
		
		interfaceComposants.add(pi1);
		interfaceComposants.add(si1);
		interfaceComposants.add(so1);
		interfaceComposants.add(po1);
		
		ServiceInput si2 = new ServiceInput("security_checkSR", this);
		PortInput pi2 = new PortInput("security_checkPR", this);
		si2.usePort(pi2);
		
		ServiceOutput so2 = new ServiceOutput("security_checkSF", this);
		PortOutput po2 = new PortOutput("security_checkPF", this);
		so2.usePort(po2);
		
		interfaceComposants.add(pi2);
		interfaceComposants.add(si2);
		interfaceComposants.add(so2);
		interfaceComposants.add(po2);
		
		ServiceInput si3 = new ServiceInput("dbQuerySR", this);
		PortInput pi3 = new PortInput("dbQueryPR", this);
		si3.usePort(pi3);
		
		ServiceOutput so3 = new ServiceOutput("dbQuerySF", this);
		PortOutput po3 = new PortOutput("dbQueryPF", this);
		so3.usePort(po3);
		
		interfaceComposants.add(pi3);
		interfaceComposants.add(si3);
		interfaceComposants.add(so3);
		interfaceComposants.add(po3);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println(this + " has been notified : "+arg);
		
		boolean treated = false;
		
		EventInConfiguration ev = (EventInConfiguration) arg;
		if (ev.getSrc() instanceof PortInput){
			PortInput p = (PortInput) ev.getSrc();
			if ("ExternalSocketPR".equals(p.getName())){
				treated=true;
				callService("ExternalSocketSF", ev.getArg());
			}
		}
		
		if (!treated){
			setChanged();
			notifyObservers(arg);
		}
		
	}

}
