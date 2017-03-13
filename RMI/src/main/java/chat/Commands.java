package chat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import lpi.server.rmi.IServer;

public class Commands {
	private final IServer iServ;

	public Commands(IServer iServ) {
        this.iServ = iServ;
    }

	public void ping() {
		try {
			iServ.ping();
			System.out.println("Ping ");
		} catch (Exception ex) {
			System.out.println("Connection problems");
		}
	}

	public void echo(String[] comandMas) {
		try {
			if (comandMas.length == 2) {
				System.out.println(iServ.echo(comandMas[1]));
			} else {
				System.out.println("Try again");
			}
		} catch (Exception ex) {
			System.out.println("Connection problems");
		}
	}
	private final Timer timer = new Timer();
    private static String myLogin = null;
    private static String sessionId = null;


	public void login(String[] comandMas) throws RemoteException {
		if (comandMas.length == 3) {

			if (!comandMas[1].equals(myLogin)) {

				if (sessionId != null) {
					iServ.exit(sessionId);
				}

				sessionId = iServ.login(comandMas[1], comandMas[2]);

				if (myLogin == null) {
					timer.schedule(receive, 0, 1500);
				}
				myLogin = comandMas[1];
			}
		} else {
			System.out.println("Try again");
		}
	}

	public void receiveMsg() throws RemoteException {
		if (sessionId != null) {
			IServer.Message mess = null;
			mess = iServ.receiveMessage(sessionId);
			if (mess != null) {
				System.out.println("Message from: " + mess.getSender() + " : " + mess.getMessage());
			} else if (flug) {
				System.out.println("No message");
			}
		} else {
			System.out.println("Not login");
		}
	}
	private boolean flug;

	public void exit() throws RemoteException {
		timer.cancel();
		iServ.exit(sessionId);
		System.out.println("Exit ");
		Client.flug = false;
	}
}
