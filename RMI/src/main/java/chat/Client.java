package chat;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.math.BigDecimal;
import lpi.server.rmi.IServer;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Client {
	public static boolean flug = true;

	public void start(int Port) {

		try (Scanner scanner = new Scanner(System.in)) {

			Registry registry = LocateRegistry.getRegistry(Port);
			IServer proxy = (IServer) registry.lookup(IServer.RMI_SERVER_NAME);
			System.out.println("Welcome to server");
			Interpretator inter = new Interpretator(proxy);

			while (flug) {
				String inLine = scanner.nextLine().trim();

				if (!inLine.equals("")) {
					inter.interpretator(inLine);
				}
			}

		} catch (RemoteException | NotBoundException ex) {
			System.out.println("Problem conections");
		}
	}

}
