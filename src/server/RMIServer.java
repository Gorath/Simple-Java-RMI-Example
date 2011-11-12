package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;

	public RMIServer() throws RemoteException {
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// On receipt of first message, initialize the receive buffer
		if (receivedMessages == null) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[msg.totalMessages];
		}
		
		// Log receipt of the message
		receivedMessages[msg.messageNum] = 1;
		
		// If this is the last expected message, then identify
		//    any missing messages
		if (msg.messageNum + 1 == totalMessages) {
			System.out.println("Messages being totaled....");
			
			int count = 0;
			for (int i = 0; i < totalMessages; i++) {
				if (receivedMessages[i] != 1) {
					count++;
				}
			}
			
			System.out.println("Total messages sent      : " + totalMessages);
			System.out.println("Total messages received  : " + (totalMessages - count));
			System.out.println("Total messages lost      : " + count);
			System.out.println("Efficiency               : " + ((totalMessages - count) / totalMessages) );
			System.out.println("Test finished.");
			System.exit(0);
		}
		

	}


	public static void main(String[] args) {
		
		// Initialize Security Manager
		if(System.getSecurityManager()==null){
            System.setSecurityManager(new RMISecurityManager());
        }
		
		// Bind to RMI registry 
		try {
			LocateRegistry.createRegistry( 1099 );
			Naming.rebind("RMIServer", new RMIServer());
		} catch (RemoteException e) {
			System.out.println("Error initializing registry or binding server.");
			System.exit(-1);
		} catch (MalformedURLException e) {
			System.out.println("Could not bind server to defined registry as the URL was malformed.");
			System.exit(-1);
		}
		System.out.println("Running...");
	}
	
}
