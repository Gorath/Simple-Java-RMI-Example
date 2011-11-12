package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerI extends Remote {
	public void receiveMessage(MessageInfo msg) throws RemoteException;
}
