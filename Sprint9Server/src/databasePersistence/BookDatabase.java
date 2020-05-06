package databasePersistence;

import com.mysql.fabric.xmlrpc.base.Array;
import model.domain.*;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.BinaryOperator;

public class BookDatabase implements BookPersistence {

    private MyDatabase db;

//    Fill out with your postgres password
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "08191";

    /**
     * The constructor makes the connection to the database by passing the driver,url,username and password that
     * are static variables, to the data source which is postgres.The constructor making the connection
     * using the MyDatabase java file.
     */
    public BookDatabase() {
        try {
            this.db = new MyDatabase(DRIVER, URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method simply creates a list of students from the database in order to be used in the model.
     * The purpose of this method is to load the users' information from database to the model in order
     * to be used in the model instead of fetching data from database whenever is requested. In this
     * case the database and the model are synchronized and whenever there is an update it happens
     * both in the database and in the model but for retrieving data the system uses the loaded data in the model
     * which is faster.
     *
     * @return list
     * @throws SQLException
     */
    @Override
    public StudentList loadPerson() throws SQLException {
        String person = "SELECT Person.username, Person.password_,PhoneNo.phoneNo,Email.email " +
                "FROM Booksharing.Person " +
                "inner join Booksharing.PhoneNo on Person.username = PhoneNo.username " +
                "inner join BookSharing.Email on Person.username = Email.username;";
        StudentList list = new StudentList();
        ArrayList<Object[]> personList = db.query(person);
        for (int i = 0; i < personList.size(); i++) {
            Object[] array = personList.get(i);
            Student student = new Student(String.valueOf(array[0]), String.valueOf(array[1]),
                    String.valueOf(array[2]), String.valueOf(array[3]));
            list.addStudent(student);
        }
        return list;
    }

    /**
     * The method passes a String type username as a parameter and returns a bookList for a specific student.
     * This method is loading student's bookList to the model. The result of the query is an ArrayList of object which
     * is converted to the type than can be used in the model. In this case an Object[] array is created in order
     * to holds the items of the arrayList and then the string value of these items will be stored in a Book object
     * and will be added to the bookList.
     *
     * @param username
     * @return bookList
     * @throws SQLException
     */
    @Override
    public BookList loadBook(String username) throws SQLException {
        String sqlBook = "SELECT title, fName,lName,year_,username, availbility FROM Booksharing.Book, Booksharing.Author" +
                " WHERE Book.bookId = Author.bookId AND Book.username = ?;";
        BookList list = new BookList();
        ArrayList<Object[]> books = db.query(sqlBook, username);
        for (int i = 0; i < books.size(); i++) {
            Object[] array = books.get(i);
            Book book = new Book(array[0] + "", array[1] + " " + array[2],
                    array[3] + "", loadOneStudent(array[4] + ""), (boolean) array[5]);
            list.addBook(book);
        }
        return list;

    }

    /**
     * This method is simply loads all the books in the database to the model.
     *
     * @return bookList
     * @throws SQLException
     */
    @Override
    public BookList loadAllBooks() throws SQLException {


        String sqlBook = "SELECT title, fName, lName, year_, username, availbility " +
                "FROM Booksharing.Book, Booksharing.Author " + " WHERE Book.bookId = Author.bookId ";
        ArrayList<Object[]> books = db.query(sqlBook);
        BookList list = new BookList();
        for (int i = 0; i < books.size(); i++) {
            Object[] array = books.get(i);
            Book book = new Book(array[0] + "", array[1] + " " + array[2], array[3] + "", loadOneStudent(array[4] + ""), (boolean) array[5]);
            list.addBook(book);

        }
        return list;
    }

    /**
     * This methid is used to load all the books which is borrowed by a specific user in order to keep track of
     * the borrowed books, it returns list of books which is borrowed by one person and contains information about the book
     * such as title, author, year, availability and the owner of the book.
     *
     * @param username
     * @return list
     */
    @Override
    public ArrayList<Book> loadBorrowedBooks(String username) {

        String sql = "SELECT bookId FROM Booksharing.Loan WHERE borrowerusername = ?";
        ArrayList<Object[]> sqlIds = null;
        try {
            sqlIds = db.query(sql, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Book> list = new ArrayList<>();
        int[] ids = new int[sqlIds.size()];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = (int) sqlIds.get(i)[0];
        }

        for (int j = 0; j < ids.length; j++) {
            try {
                list.add(loadOneBook(ids[j]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * This is a private method which loads only one student by passing a username as an argument and returns
     * one student.
     *
     * @param username
     * @return student
     * @throws SQLException
     */
    private Student loadOneStudent(String username) throws SQLException {
        String sql = "SELECT Person.username, Person.password_,PhoneNo.phoneNo,Email.email " +
                "FROM Booksharing.Person " +
                "INNER JOIN Booksharing.PhoneNo ON Person.username = PhoneNo.username " +
                "INNER JOIN BookSharing.Email ON Person.username = Email.username WHERE Person.username=?;";
        Student student = null;

        ArrayList<Object[]> user = db.query(sql, username);
        for (int i = 0; i < user.size(); i++) {
            Object[] array = user.get(i);
            student = new Student(array[0] + "", array[1] + "", array[2] + "", array[3] + "");
        }
        return student;

    }

    /**
     * This is a private method which creates an object of a specific book
     * and used in another method for loading to the model. The method passes an id which is a bookId in the database.
     * This id is used in order to fetch a specific book from the database and a book object is created in order to be used
     * in the model.
     *
     * @param id
     * @return book
     * @throws SQLException
     */
    private Book loadOneBook(int id) throws SQLException {
        String bookId = "SELECT title, fName,lName,year_,username, availbility FROM Booksharing.Book, Booksharing.Author" +
                " WHERE Book.bookId = Author.bookId and Book.bookId=?;";
        ArrayList<Object[]> oneId = db.query(bookId, id);
        Book book = null;
        for (int i = 0; i < oneId.size(); i++) {
            Object[] array = oneId.get(i);
            book = new Book(array[0] + "", array[1] + " " + array[2], array[3] + "", loadOneStudent(array[4] + ""), (boolean) array[5]);
        }
        return book;
    }

    /**
     * This method is used in order to convert the sql date from database in to java util date. This is method is
     * used in another method for loading the request to the model.
     *
     * @param sqlDate
     * @return javaDate
     */
    public Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
        Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    /**
     * This method is loading all the data from the Request table from the database into the model. It will
     * returns a list of requests.
     *
     * @return list
     * @throws SQLException
     */
    @Override
    public RequestList loadRequest() throws SQLException {
        String request = "SELECT Request.username, Request.bookId,status,date_ FROM Booksharing.Request ";
        ArrayList<Object[]> requests = db.query(request);
        RequestList list = new RequestList();

        for (int i = 0; i < requests.size(); i++) {
            Object[] array = requests.get(i);
            Student student = loadOneStudent((String) array[0]);
            Book book = loadOneBook((int) array[1]);
            Date date = convertFromSQLDateToJAVADate((java.sql.Date) array[3]);
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Request newRequest = new Request(student, book, array[2] + "", localDate);
            list.add(newRequest);
        }
        return list;
    }

    /**
     * This method is removing the data from the Loan table. It will find rows in the Book table
     * by checking the availability of the book, if it is FALSE it means the book is lent out to another student
     * but since it may returns multiple books it needs to have a check in order to get the right book
     * then it will check the title of the book and the username which is the owner of the book.
     * when the right book is found first it will change the availability of the book to TRUE which means
     * the book is returned to the owner an can be lend out again, then it will delete it from the Loan table.
     *
     * @param book
     * @throws SQLException
     */
    @Override
    public synchronized void removeFromLoan(Book book) throws SQLException {
        String sql = "SELECT bookId FROM Booksharing.Book " +
                "WHERE Book.availbility = FALSE AND title=? AND username=?;";
        String update = " UPDATE Booksharing.Book SET availbility=TRUE WHERE bookID=?;";
        String del = "DELETE FROM Booksharing.Loan WHERE bookId=?;";
        ArrayList<Object[]> id = db.query(sql, book.getTitle(), book.getOwner().getUsername());

        int bookId = Integer.parseInt(id.get(0)[0].toString());
        db.update(update, bookId);
        db.update(del, bookId);

    }

    /**
     * This method is simply register a new book by inserting the related information.
     * Since the information of the book will be stored in two tables, there are two queries for inserting into
     * Book and Author tables. For inserting the author's information this method is calling getAuthor() method
     * in order to split  first name and last name of the author.
     *
     * @param book
     * @throws SQLException
     */
    @Override
    public synchronized void registerBook(Book book) throws SQLException {
        String newBook = "INSERT INTO booksharing.Book(username,title,year_, availbility)" +
                "VALUES( ?,?,?,?);";
        String id = "SELECT bookId FROM Booksharing.Book WHERE title =? and username=?;";

        String author = "INSERT INTO booksharing.Author(bookId,fName,lName )VALUES (?,?,?);";
        int bookId = 0;

        db.update(newBook, book.getOwner().getUsername(), book.getTitle(), book.getYear(), true);
        ArrayList<Object[]> ids = db.query(id, book.getTitle(), book.getOwner().getUsername());
        bookId = Integer.parseInt(ids.get(0)[0].toString());

        db.update(author, bookId, getAuthor(book)[0], getAuthor(book)[1]);

    }

    /**
     * This method is removing a book from the database and it will check if the user has more than one book in their list
     * otherwise  the remove cannot be done. It will counts the number of books in the database for a specific user
     * and if it is greater than one the act of removing can be done.
     *
     * @param book
     * @throws SQLException
     */
    @Override
    public synchronized void removeBook(Book book) throws SQLException {

        String count = "SELECT COUNT(*) AS myRow FROM Booksharing.Book WHERE Book.username=?;";
        String sql = "DELETE FROM booksharing.Book where title=? and username=?;";
        int myCount = 0;

        ArrayList<Object[]> rows = db.query(count, book.getOwner().getUsername());
        myCount = Integer.parseInt(rows.get(0)[0].toString());
        if (myCount > 1) {
            db.update(sql, book.getTitle(), book.getOwner().getUsername());
        } else {


        }
    }

    /**
     * This method is simply adding a user with a book, as for the very first time when a user id registering
     * in the system needs to add a book. This method will insert the information of the user in three
     * tables such as Person, Email, PhoneNo, and the registerBook method will be calling afterwards
     * to add the book in the Book and Author tables.
     *
     * @param student
     * @param book
     * @throws SQLException
     */
    @Override
    public synchronized void registerPerson(Student student, Book book) throws SQLException {

        String person = "INSERT INTO booksharing.Person(username,password_,isAdmin,isStudent) VALUES (?,?,?,?);";
        String phone = "INSERT INTO booksharing.PhoneNo(username,phoneNo) VALUES (?,?);";
        String email = "INSERT INTO booksharing.Email(username,email) VALUES (?,?);";

        db.update(person, student.getUsername(), student.getPassword(), false, true);
        db.update(phone, student.getUsername(), student.getPhoneNumber());
        db.update(email, student.getUsername(), student.getEmail());
        registerBook(book);

    }

    /**
     * This method is returning the contact information of a specific user.
     * The username is passed as a parameter to the method.
     *
     * @param username
     * @return contact
     * @throws SQLException
     */
    @Override
    public String getContactInfo(String username) throws SQLException {
        String sql = "SELECT Email.email, PhoneNo.phoneNo, Person.username " +
                " FROM Booksharing.Person " +
                " INNER JOIN Booksharing.PhoneNo on Person.username = PhoneNo.username " +
                " INNER JOIN Booksharing.Email on Person.username = Email.username WHERE Person.username=?;";
        ArrayList<Object[]> contactInfo = db.query(sql, username);
        String contact = "";
        for (int i = 0; i < contactInfo.size(); i++) {
            Object[] array = contactInfo.get(i);
            contact += array[0] + " " + array[1] + "" + array[2];
        }
        return contact;
    }

    /**
     * This method returns a contact information of a user/owner of a book. This method passes
     * an object of student as the parameter and returns contact information with the type of String.
     *
     * @param username
     * @return contact
     * @throws SQLException
     */
    @Override
    public String getContactInfo(Student username) throws SQLException {
        String sql = "SELECT Email.email, PhoneNo.phoneNo, Person.username " +
                " FROM Booksharing.Person " +
                " INNER JOIN Booksharing.PhoneNo on Person.username = PhoneNo.username " +
                " INNER JOIN Booksharing.Email on Person.username = Email.username WHERE Person.username=?;";
        ArrayList<Object[]> contactInfo = db.query(sql, username.getUsername());
        String contact = "";
        for (int i = 0; i < contactInfo.size(); i++) {
            Object[] array = contactInfo.get(i);
            contact += array[0] + " " + array[1];
        }
        return contact;
    }

    /**
     * The method adds a request to the Request table. This action happens when a user send a request to borrow a book
     * from another user. This request will be stored in the Request table with the status "waiting" . if the owner of the book
     * is not online later this request will appear to them when they log in into the system.
     * If the owner accept the request then the status will changed to accepted and will be stored also in the Loan table
     * if the owner denies the request the status will change to denied and will be removed from the table when the borrower
     * gets the answer to their request.
     * @param request
     * @throws SQLException
     */
    @Override
    public synchronized void addRequest(Request request) throws SQLException {

        String title = request.getBook().getTitle();
        String owner = request.getBook().getOwner().getUsername();
        String idBook = "SELECT bookId FROM Booksharing.Book WHERE title=? AND username=?;";
        String requestSql = "INSERT INTO Booksharing.Request (username,bookId,date_, status)VALUES (?,?,?,?);";
        int bookId = 0;

        java.sql.Date sqlDate = java.sql.Date.valueOf(request.getDateForDB());
        ArrayList<Object[]> ids = db.query(idBook, title, owner);
        bookId = Integer.parseInt(ids.get(0)[0].toString());
        db.update(requestSql, request.getBorrower().getUsername(), bookId, sqlDate, "waiting");
    }

    /**
     * This method  simply removes the request from the Request table by checking the title and the borrower username.
     *
     * @param request
     * @throws SQLException
     */
    @Override
    public void removeRequest(Request request) throws SQLException {

        String title = request.getBook().getTitle();
        String owner = request.getBook().getOwner().getUsername();
        String borrower = request.getBorrower().getUsername();
        String idBook = "SELECT bookId FROM Booksharing.Book WHERE title=? AND username=?;";
        String sql = "DELETE FROM Booksharing.Request WHERE username=? AND bookId =?";
        int bookId = 0;

        ArrayList<Object[]> ids = db.query(idBook, title, owner);
        bookId = Integer.parseInt(ids.get(0)[0].toString());

        db.update(sql, borrower, bookId);
    }

    /**
     * This method deletes a specific user from the Person table and it will also delete
     * any other data that is related to the user from the database.
     * @param student
     * @throws SQLException
     */
    @Override
    public synchronized void removeStudent(Student student) throws SQLException {
        String sql = "DELETE FROM Booksharing.Person WHERE username=?;";
        db.update(sql, student.getUsername());


    }

    /**
     * This method removed the request from the Request table by checking the date, username and the status.
     * if the request is denied it will be removed from the database.
     * @param request
     * @throws SQLException
     */
    @Override
    public synchronized void removeDeniedRequest(Request request) throws SQLException {
        String sql = "DELETE FROM Booksharing.Request WHERE  status='denied' AND   username=? AND date_=?";

        java.sql.Date sqlDate = java.sql.Date.valueOf(request.getDateForDB());

        db.update(sql, request.getBorrower().getUsername(), sqlDate);


    }

    /**
     * This method is for updating the status of the request from waiting to denied.
     * if the owner of the book denies a request the status will be changed to denied and later can be deleted.
     * @param request
     * @throws SQLException
     */
    @Override
    public synchronized void updateStatusToDenied(Request request) throws SQLException {

        String bookId = "SELECT Request.bookId FROM Booksharing.Request INNER JOIN " +
                "Booksharing.Book ON Request.bookId= Book.bookId ; ";
        String sql = "UPDATE Booksharing.Request SET status=? WHERE username=? AND bookId=?;";

        ArrayList<Object[]> ids = db.query(bookId);
        int id = Integer.parseInt(ids.get(0)[0].toString());

        db.update(sql, "denied", request.getBorrower().getUsername(), id);
    }

    /**
     * this method will change the status of a request from waiting to accepted. which later can be used in order to
     * store the request in the Loan table which means the act of borrowing is happened.
     * First the specific request must be found by checking the username and the bookId and then
     * the status will be set to accepted.
     * @param request
     * @throws SQLException
     */
    @Override
    public synchronized void updateStatusToAccepted(Request request) throws SQLException {
        String bookId = "SELECT Request.bookId FROM Booksharing.Request INNER JOIN " +
                "Booksharing.Book ON Request.bookId= Book.bookId WHERE Request.username=?; ";
        String sql = "UPDATE Booksharing.Request SET status='accepted' WHERE username=? AND bookId=?;";

        ArrayList<Object[]> ids = db.query(bookId, request.getBorrower().getUsername());
        int id = Integer.parseInt(ids.get(0)[0].toString());
        db.update(sql, request.getBorrower().getUsername(), id);
    }

    /**
     * If the request is accepted by the owner of the book then it should
     * also be stored into Loan table.
     * the query works with  the composite primary keys in order to get the right request.
     * When the request is stored into the Loan table the borrow Date will be set as the current date
     * The book availability should set to false in the Book table so no one else can send a request on the book
     * as it is not available in the system.
     *
     * @param request
     * @throws SQLException
     */
    @Override
    public synchronized void moveRequestToLoan(Request request) throws SQLException {
        java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        String idBook = "SELECT Request.bookId FROM Booksharing.Request WHERE status='accepted';";
        String insertLoan = "INSERT INTO Booksharing.Loan (borrowerUsername, bookId,borrowDate)" +
                " VALUES(?,?,?);";

        String bookStatus = "UPDATE Booksharing.Book SET availbility=? WHERE Book.bookId=?;";
        int bookId = 0;
        ArrayList<Object[]> ids = db.query(idBook);
        bookId = Integer.parseInt(ids.get(0)[0].toString());
        db.update(insertLoan, request.getBorrower().getUsername(), bookId, currentDate);

        db.update(bookStatus, false, bookId);


    }

    /**
     * The method is simply get the value for the author and detects all the spaces in the string
     * and all the words up until the last space will be considered as the first name and the
     *
     * @param book
     * @return names
     */
    public String[] getAuthor(Book book) {
        String author1 = book.getAuthor();
        String fName = "";
        String lName = "";
        int index = 0;
        index = author1.lastIndexOf(" ");
        lName = author1.substring(index + 1);
        fName = author1.substring(0, index);
        String[] names = new String[2];
        names[0] = fName;
        names[1] = lName;
        return names;

    }

}
