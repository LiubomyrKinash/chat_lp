package chat;
import lpi.server.rmi.IServer;
import java.util.*;
import java.util.List;
import java.io.*;
public class Commandident {
	private final Commands comP;
	private final Pars parser = new Pars();

	public Interpretator(IServer ob) {
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
                case "exit":
                    comP.exit();
                    break;
                default:
                    System.out.println("No command");
                    break;

}