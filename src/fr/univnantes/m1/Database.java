package fr.univnantes.m1;

import java.util.Observable;

import fr.univnantes.m1.data.DataAuth;
import fr.univnantes.m1.data.DataAuthResponse;
import fr.univnantes.m1.data.DataQuery;
import fr.univnantes.m1.data.DataResponse;
import fr.univnantes.m2.Configuration.Composant;
import fr.univnantes.m2.Configuration.EventInConfiguration;
import fr.univnantes.m2.InterfaceComposant.PortInput;
import fr.univnantes.m2.InterfaceComposant.PortOutput;
import fr.univnantes.m2.InterfaceComposant.ServiceInput;
import fr.univnantes.m2.InterfaceComposant.ServiceOutput;

public class Database extends Composant {

	public Database() {
		super("Database");
		
		ServiceInput si1 = new ServiceInput("query_DSR", this);
		PortInput pi1 = new PortInput("query_DPR", this);
		si1.usePort(pi1);
		
		ServiceOutput so1 = new ServiceOutput("query_DSF", this);
		PortOutput po1 = new PortOutput("query_DPF", this);
		so1.usePort(po1);
		
		interfaceComposants.add(pi1);
		interfaceComposants.add(si1);
		interfaceComposants.add(so1);
		interfaceComposants.add(po1);
		
		ServiceInput si2 = new ServiceInput("security_managementSR", this);
		PortInput pi2 = new PortInput("security_managementPR", this);
		si2.usePort(pi2);
		
		ServiceOutput so2 = new ServiceOutput("security_managementSF", this);
		PortOutput po2 = new PortOutput("security_managementPF", this);
		so2.usePort(po2);
		
		interfaceComposants.add(pi2);
		interfaceComposants.add(si2);
		interfaceComposants.add(so2);
		interfaceComposants.add(po2);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println(this + " has been notified : "+arg);
		
		boolean treated = false;
		
		EventInConfiguration ev = (EventInConfiguration) arg;
		if (ev.getSrc() instanceof PortInput){
			PortInput p = (PortInput) ev.getSrc();
			treated=true;
			if ("query_DPR".equals(p.getName())){
				
				DataQuery data = (DataQuery) ev.getArg();
					
				DataResponse dr = new DataResponse(data.getIdSender(), new StringBuilder(data.getQuery()).reverse().toString());
				
				callService("query_DSF", dr);
					
			}
			else{
				
				DataAuth data = (DataAuth) ev.getArg();
				
				DataAuthResponse dr = new DataAuthResponse(data.getIdSender(), data.getIdSender().length()%2==0);
				
				callService("security_managementSF", dr);
			}
		}
		
		if (!treated){
			setChanged();
			notifyObservers(arg);
		}
		
	}

}
