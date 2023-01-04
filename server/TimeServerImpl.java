package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import common.AppConstants;

// Implementation 

public class TimeServerImpl extends UnicastRemoteObject implements TimeServer {

	private LocalTime time;

	public TimeServerImpl(LocalTime time) throws RemoteException {
		this.time = time;
	}

	@Override
	public LocalTime getTime() throws RemoteException {
		return time;
	}

	@Override
	public void adjustTime(LocalTime clientTime, long diffNanos) throws RemoteException {
		long localTimeNanos = clientTime.toNanoOfDay();
		long thisNanos = this.getTime().toNanoOfDay();
		var newNanos = thisNanos - localTimeNanos;
		newNanos = newNanos * -1 + diffNanos + thisNanos;
		LocalTime newLocalTime = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("New time: " + AppConstants.formatter.format(newLocalTime));
		this.time = newLocalTime;
	}

}