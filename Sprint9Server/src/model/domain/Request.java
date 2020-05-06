package model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * The Request class implements Serializable.The Request class represents a
 * request on a book which is sent by a user to
 * the owner of the book in order to be borrowed.
 * There are instance variables type book
 * student which represents both the lender and the borrower.The request has a
 * status as being accepted, denied or waiting. The request also has a date on which
 * the request is sent for a book.
 *
 * @Author Group 4
 */
public class Request implements Serializable {
    private Book book;
    private Student borrower;
    private Student lender;
    private String status;
//    can be "waiting", "accepted" or "declined"

    private LocalDate date;

    /** The constructor is initializing the instance variables
     * it takes two parameters as borrower and a book.the status of the request
     * is always sets to waiting for the time the request is sent. the date always
     * represents a current date as the request cannot be sent in past or in future.
     * @param borrower
     * @param book
     */
    public Request(Student borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
        this.lender = book.getOwner();
        this.status = "waiting";
        this.date = LocalDate.now();
    }

    /**
     * The constructor that takes borrower, book, status and date as parameters.
     * @param borrower
     * @param book
     * @param status
     * @param date
     */
    public Request(Student borrower, Book book, String status, LocalDate date) {
        this.borrower = borrower;
        this.book = book;
        this.lender = book.getOwner();
        this.status = status;
        this.date = date;
    }

    /**
     * Contructor. Creates a Request. Used for testing.
     * @param borrower
     * @param book
     * @param date
     */
    public Request(Student borrower, Book book, LocalDate date) {
        this.borrower = borrower;
        this.book = book;
        this.lender = book.getOwner();
        this.status = "waiting";
        this.date = date;
    }

    /**
     * this method returns the book object which is related to the request.
     * @return book which is requested by a another user
     */
    public Book getBook() {
        return book;
    }

    /**
     * This method is setting the book the same as it gets as the parameter.
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * This method is returning the borrower
     *
     * @return borrower being a student who requested a book
     */
    public Student getBorrower() {
        return borrower;
    }

    /**
     * This method is setting the borrower
     * to the value it gets as a parameter.
     * @param borrower
     */
    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

    /**
     * The method simply returns the owner of the book
     * @return lender as the owner of the book
     */
    public Student getLender() {
        return lender;
    }

    /**
     * The method is setting the lender or the owner of the book.
     * @param lender
     */
    public void setLender(Student lender) {
        this.lender = lender;
    }

    /**
     * The method returns the status of the request
     * @return status as being waiting, accepted or denied
     */
    public String getStatus() {
        return status;
    }

    /**
     * The method set the status by taking it as the parameter
     * in order to change the status of the request
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The method returns the date of the request having the type
     * LocalDate.
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * The method setting the sate of the request
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof Request)) return false;

        Request other = (Request) obj;
        return this.borrower.equals(other.borrower) && this.lender.equals(other.lender) && this.book.equals(other.book);

    }

    /**
     * Returns a String representation of the request.
     * @return
     */
    public String toString() {
        return this.book.toString() + " was requested by " + borrower.getUsername() + " from " + lender.getUsername();
    }

    /**
     * this method is used for the database in order to get the
     * date in the right format of having the year,month and day with a
     *  dash in between. it takes the values from the LocalDate which the
     *  date of the request when it is sent.
     * @return
     */
    public String getDateForDB() {
        String s = "";
        s += date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
        return s;
    }
}
