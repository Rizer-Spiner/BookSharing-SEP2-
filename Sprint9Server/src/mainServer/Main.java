package mainServer;

import RMI.RemoteServer;
import model.domain.Book;
import model.domain.Student;
import model.mediator.RmiServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
          throws MalformedURLException, RemoteException, SQLException
    {
        RemoteServer server = new RmiServer();
        

        Student student1 = new Student("FreddieMerqury", "reakfree","11111111","email");
        Book book1 = new Book("Radioaga", "Queen gfcx", "1986", student1, true);
        server.registerNewUser(student1, book1,null );


    }
}
