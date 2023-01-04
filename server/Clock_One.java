package server;

import static common.AppConstants.formatter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import common.AppConstants;

// Machine 1
			
public class Clock_One {

	public static void main(String[] args) {
		try {
			TimeServer ts1 = new TimeServerImpl(LocalTime.parse("09:05:00", formatter));
			Registry registry1 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_1);
			registry1.rebind(TimeServerImpl.class.getSimpleName(), ts1);
			System.out.println(String.format("Machine 1 started, port: %s", AppConstants.SERVER_PORT_1));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
