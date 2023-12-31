package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;

import Client.User;

import java.rmi.RemoteException;

public class Server extends UnicastRemoteObject implements StudentInt {

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public void sort() throws RemoteException, SQLException {
        Sort sort = new Sort();
        sort.performSort();
    }

    @Override
    public void update(String search, String id, String name, int age, String address, String contact_number, String program,
            String college) throws RemoteException, SQLException {
        Update update = new Update();
        update.update(search, id, name, age, address, contact_number, program, college);
    }

    @Override
    public void delete(String id) throws RemoteException, SQLException {
        Delete deleteObj = new Delete();
        deleteObj.delete(id);
    }

    @Override
    public void display() throws RemoteException, SQLException {
        Display display = new Display();
        display.displayStudentData();
    }

    @Override
    public void parse() throws RemoteException {
        Parser parse = new Parser();
        parse.displayXML();
    }

    @Override
    public void search(String idNumber, User user) throws RemoteException, SQLException {
        Search search = new Search();
        search.searchRecord(idNumber, user);
    }

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Server server = new Server();

            Registry registry = LocateRegistry.createRegistry(9100);
            registry.bind("Server", server);

            System.out.println("Server started. Press enter to exit.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            registry.unbind("Server");
            UnicastRemoteObject.unexportObject(server, true);
            System.out.println("Server stopped.");
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
