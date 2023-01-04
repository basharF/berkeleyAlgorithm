package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface TimeServer extends Remote {

	LocalTime getTime() throws RemoteException;

	void adjustTime(LocalTime clientTime, long nanos) throws RemoteException;
}