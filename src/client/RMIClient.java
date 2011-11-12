package client;

import common.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import common.MessageInfo;

public class RMIClient {

	public static void main(String[] args) {

		RMIServerI iRMIServer = null;

		// Check arguments for Server host and number of messages
		if (args.length < 2){
			System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
			System.exit(-1);
		}

		String urlServer = new String("rmi://" + args[0] + "/RMIServer");
		int numMessages = Integer.parseInt(args[1]);

		// Initialize Security Manager
		if(System.getSecurityManager()==null){
            System.setSecurityManager(new RMISecurityManager());
        }
		
		// Bind to RMIServer
		 try {
			 iRMIServer = (RMIServerI) Naming.lookup(urlServer);
			 // Attempt to send messages the specified number of times
			 for(int i = 0; i < numMessages; i++) {
				MessageInfo msg = new MessageInfo(numMessages,i);
				iRMIServer.receiveMessage(msg);
			 }
		} catch (MalformedURLException e) {
			System.out.println("Errpr: Malformed hostname.");
		} catch (RemoteException e) {
			System.out.println("Error: Remote Exception.");
		} catch (NotBoundException e) {
			System.out.println("Error: Not Bound Exception.");
		}
		
	}
}
