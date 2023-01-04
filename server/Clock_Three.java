package server;

import static common.AppConstants.formatter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import common.AppConstants;

// Machine 3

public class Clock_Three {

	public static void main(String[] args) {
		try {
			TimeServer ts3 = new TimeServerImpl(LocalTime.parse("09:15:00", formatter));
			Registry registry3 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_3);
			registry3.rebind(TimeServerImpl.class.getSimpleName(), ts3);
			System.out.println(String.format("Machine 3 started, port: %s", AppConstants.SERVER_PORT_3));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
