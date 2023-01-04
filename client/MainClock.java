package client;

import static common.AppConstants.formatter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import common.AppConstants;
import server.TimeServer;
import server.TimeServerImpl;

//Client Side

public class MainClock {

	public static void main(String[] args) {
		try {
			var times = new ArrayList<LocalTime>();

			LocalTime locTime = LocalTime.parse("09:00:00", AppConstants.formatter);
			times.add(locTime);
			System.out.println("Local time: " + formatter.format(locTime));

			// Connect machine 1
			Registry registry1 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_1, AppConstants.SERVER_PORT_1);
			TimeServer ts1 = (TimeServer) registry1.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection to machine 1 established successfuly.");
			LocalTime TimeServer1 = ts1.getTime();
			times.add(TimeServer1);
			System.out.println("Machine 1 time: " + formatter.format(TimeServer1));

			// Connect machine 2
			Registry registry2 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_2, AppConstants.SERVER_PORT_2);
			TimeServer ts2 = (TimeServer) registry2.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection to machine 2 established successfuly.");
			LocalTime TimeServer2 = ts2.getTime();
			times.add(TimeServer2);
			System.out.println("Machine 2 time: " + formatter.format(TimeServer2));

			// Connect machine 3
			Registry registry3 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_3, AppConstants.SERVER_PORT_3);
			TimeServer ts3 = (TimeServer) registry3.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection to machine 3 established successfuly.");
			LocalTime TimeServer3 = ts3.getTime();
			times.add(TimeServer3);
			System.out.println("Machine 3 time: " + formatter.format(TimeServer3));

			var nanoLocal = locTime.toNanoOfDay();
			var diffMachine1 = TimeServer1.toNanoOfDay() - nanoLocal;
			var diffMachine2 = TimeServer2.toNanoOfDay() - nanoLocal;
			var diffMachine3 = TimeServer3.toNanoOfDay() - nanoLocal;
			var avgDiff = (diffMachine1 + diffMachine2 + diffMachine3) / 3; 

			// Assign new time
			ts1.adjustTime(locTime, avgDiff);
			ts2.adjustTime(locTime, avgDiff);
			ts3.adjustTime(locTime, avgDiff);
			locTime = locTime.plusNanos(avgDiff);
			System.out.println("Time was adjusted successfuly");

			// Verifying the time on all machines
			System.out.println("Local time: " + formatter.format(locTime));
			System.out.println("Machine 1 time: " + formatter.format(ts1.getTime()));
			System.out.println("Machine 2 time: " + formatter.format(ts2.getTime()));
			System.out.println("Machine 3 time: " + formatter.format(ts3.getTime()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}