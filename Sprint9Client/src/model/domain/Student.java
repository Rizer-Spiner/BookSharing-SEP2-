package model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Student class represents a user in the system it implements Serializable
 * having the instance variables for the user information such as username,password
 * phone number , email and a bookList.
 * @Author Group 4
 */

public class Student implements Serializable {

    private BookList bookList;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    /**
     * The constructor initializing all the instance variables and creating a
     * bookList for the student. this constructor for registration.
     * @param username
     * @param password
     * @param phoneNumber
     * @param email
     */

    public Student(String username, String password, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookList = new BookList();


    }
    
    /**
     * the constructor is initializing instance variables with
     * passing username and password.
     * @param userName
     * @param password
     */
    public Student(String userName, String password)
    {
        this.username = userName;
        this.password = password;
        this.phoneNumber = null;
        this.email = null;
        this.bookList = new BookList();
    }
    
    /**
     * The method returns the bookList of the student.
     * @return bookList
     */

    public BookList getBookList() {
        return bookList;
    }
    /**
     * Setting the bookList for the student
     * @param bookList
     */

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }
    /**
     *
     * @return username
     */

    public String getUsername() {
        return username;
    }
    /**
     * setting the username and cannot be null or empty.
     * @param username
     */

    public void setUsername(String username) {
        if(username==null) {

        }
        else
        {this.username = username;}

    }
    /**
     *
     * @return password
     */

    public String getPassword() {

        return password;
    }
    /**
     * setting the password which cannot be null
     * @param password
     */

    public void setPassword(String password) {

        if(password==null) {

        }
        else{this.password = password;}
    }
    /**
     * so boring
     * @return phoneNumber
     */

    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * setting the phoneNumber which cannot be null
     * @param phoneNumber
     */

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber==null) {

        }else{ this.phoneNumber = phoneNumber;}
    }
    /**
     *
     * @return email
     */

    public String getEmail() {
        return email;
    }
    /**
     * Setting the email and it cannot be empty
     * @param email
     */

    public void setEmail(String email) {
        if(email==null) {
        }else{this.email = email;}
    }
    /**
     *
     * @return contact information as phoneNumber and email for a username
     */

    public String getContactInfo() {
        return "Username: " + getUsername() + "\tEmail: " + getEmail() +
              "\tPhone: " + getPhoneNumber();
    }

   /**
     *
     * @return string value of student
     */
    @Override
    public String toString() {
        return "Student{" +
              "bookList=" + bookList +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", email='" + email + '\'' +
              '}';
    }
    /**
     * registration of a book for a student by having
     * the title, author and year and it will be added to
     * the student's bookList.
     * @param title
     * @param author
     * @param year
     */

    public void registerNewBook(String title, String author, String year) {
        Book book = new Book(title, author, year);

        bookList.addBook(book);

    }
    /**
     * Removing a book from the list by looping through the list.
     * @param book
     */

    public void removeBook(Book book)
    {
        for (int i=0; i<bookList.size(); i++)
        {
            if(book.equals(bookList.getBookList().get(i)))
            {
                bookList.removeBookByIndex(i);
            }
        }
    }
    /**
     * adding a book to the list
     * @param book
     */


    public void registerNewBook(Book book) {
        bookList.addBook(book);

    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Student)) return false;

        Student student = (Student) o;
        return this.username.equals(student.username)&&
              this.password.equals(student.password)&&
              Objects.equals(this.email, student.email)&&
              Objects.equals(this.phoneNumber, student.phoneNumber);
    }


    public boolean equalsForRegistration(Object o)
    {
        if (!(o instanceof Student))
            return false;
        Student student = (Student) o;

        return this.username.equals(student.getUsername()) && this.password
              .equals(student.getPassword()) && this.email
              .equals(student.getEmail()) && this.phoneNumber
              .equals(student.getPhoneNumber());
    }

    public boolean equalsForLogin(Object obj)
    {
        if (!(obj instanceof Student))
            return false;
        Student student = (Student) obj;

        return this.username.equals(student.getUsername()) && this.password
              .equals(student.getPassword());
    }
    /**
     * adding a book to the student's borrowed list
     * in order to keep track of all borrowed books.
     * @param loadBorrowedBooks
     */
    public void addToBookList(ArrayList<Book> loadBorrowedBooks)
   {
       for (int i= 0; i<loadBorrowedBooks.size(); i++)
       {
           bookList.addBook(loadBorrowedBooks.get(i));
       }
   }
}


