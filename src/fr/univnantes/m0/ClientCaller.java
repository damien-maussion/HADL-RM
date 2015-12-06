package fr.univnantes.m0;

import fr.univnantes.m1.Client;
import fr.univnantes.m1.Server;
import fr.univnantes.m1.data.DataAuth;
import fr.univnantes.m1.data.DataQuery;
import fr.univnantes.m2.Configuration.Configuration;
import fr.univnantes.m2.Connector.SimpleConnector;
import fr.univnantes.m2.InterfaceComposant.PortInput;
import fr.univnantes.m2.InterfaceComposant.PortOutput;
import fr.univnantes.m2.Link.AttachmentOutputFrom;
import fr.univnantes.m2.Link.AttachmentToInput;
import fr.univnantes.m2.Link.BindingInput;
import fr.univnantes.m2.Link.BindingOutput;

public class ClientCaller {

	public static void main(String[] args) {
		
		Configuration cs = new Configuration("Client Server");
		
		Client cl =  new Client();
		cs.addComposant(cl);
		
		Server s = new Server();
		cs.addComposant(s);
		
		//Connectors
		SimpleConnector sc1 = new SimpleConnector("RPC_Response",cs);
		SimpleConnector sc2 = new SimpleConnector("RPC_Request",cs);
		
		cs.addConnector(sc1);
		cs.addConnector(sc2);
		
		//Attachments
		cs.addAttachment(new AttachmentToInput((PortInput) cl.getPortByName("ReceiveResponsePort"), sc1.getRoleTo()));
		cs.addAttachment(new AttachmentOutputFrom((PortOutput) s.getPortByName("SendResponsePort"), sc1.getRoleFrom()));
		
		cs.addAttachment(new AttachmentOutputFrom((PortOutput) cl.getPortByName("SendRequestPort"), sc2.getRoleFrom()));
		cs.addAttachment(new AttachmentToInput((PortInput) s.getPortByName("ReceiveRequestPort"), sc2.getRoleTo()));
		
		cs.addBinding(new BindingInput((PortInput) s.getPortByName("ExternalSocketPR"), (PortInput) s.getPortByName("ReceiveRequestPort")));
		cs.addBinding(new BindingOutput((PortOutput) s.getPortByName("ExternalSocketPF"), (PortOutput) s.getPortByName("SendResponsePort")));

		//call service 
		DataQuery data = new DataQuery("client", "test");
		System.out.println("\tCall service Client.SendRequestService( "+data+" ):\n");
		cl.callService("SendRequestService", data);
		
		DataAuth data2 = new DataAuth("client");
		System.out.println("\n\tCall service Client.SendRequestService( "+data2+" ):\n");
		cl.callService("SendRequestService", data2);
		
		DataAuth data3 = new DataAuth("client2");
		System.out.println("\n\tCall service Client.SendRequestService( "+data3+" ):\n");
		cl.callService("SendRequestService", data3);
	}

}
