package chat;
import lpi.server.rmi.IServer;
import java.util.*;
import java.util.List;
import java.io.*;
public class Commandident {
	private final Commands comP;
	private final Pars parser = new Pars();

	public Commandident(IServer ob) {
        comP = new Commands(ob);        
    }

    public void interpretator(String inLine){

        String[] comandMas = parser.parsForComand(inLine);

        try {
            switch (comandMas[0]) {

                case "ping":
                    comP.ping();
                    break;

                case "echo":
                    comP.echo(comandMas);
                    break;

                case "login":
                    comP.login(comandMas);
                    break;

                case "list":
                    comP.list();
                    break;

                case "msg":
                    comP.msg(comandMas);
                    break;

                case "file":
                    comP.file(comandMas);
                    break;

                case "receivemsg":
                    comP.receiveMsg();
                    break;

                case "receivefile":
                    comP.receiveFile();
                    break;

                case "exit":
                    comP.exit();
                    break;

                default:
                    System.out.println("No this comand");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Interpretator problem");
            ex.printStackTrace();
        }
    }


	

}
