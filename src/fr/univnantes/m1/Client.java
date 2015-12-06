package fr.univnantes.m1;

import java.util.Observable;

import fr.univnantes.m2.Configuration.Composant;
import fr.univnantes.m2.Configuration.EventInConfiguration;
import fr.univnantes.m2.InterfaceComposant.PortInput;
import fr.univnantes.m2.InterfaceComposant.PortOutput;
import fr.univnantes.m2.InterfaceComposant.ServiceInput;
import fr.univnantes.m2.InterfaceComposant.ServiceOutput;

public class Client extends Composant {

	public Client() {
		super("Client");
		ServiceInput si = new ServiceInput("ReceiveResponseService", this);
		PortInput pi = new PortInput("ReceiveResponsePort", this);
		
		si.usePort(pi);
		interfaceComposants.add(pi);
		interfaceComposants.add(si);
		
		ServiceOutput so = new ServiceOutput("SendRequestService", this);
		PortOutput po = new PortOutput("SendRequestPort", this);
		so.usePort(po);
		
		interfaceComposants.add(so);
		interfaceComposants.add(po);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println(this + " has been notified : "+arg);
		
		EventInConfiguration ev = (EventInConfiguration) arg;
		if (ev.getSrc() instanceof PortInput){
			System.out.println(this + " received : "+ev.getArg());
		}
		
		super.update(o, arg);
	}
	
}
