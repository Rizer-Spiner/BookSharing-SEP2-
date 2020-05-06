
package model.domain;

import java.io.Serializable;

/**
 * The Book class implements Serializable.
 * @Author group 4
 */
public class Book implements Serializable {


    private String title;
    private String author;
    private String year;
    private Student owner;
    private boolean isAvailable;
    private Student borrower;


    /**
     * The constructor to create a book object passing the parameters such as title,
     * author, year and the owner which is a student. The availability of the book is set
     * to true and the borrower is to null from the beginning which mean the book
     * is available in the system and no one has requested it.
     * @param title as the title of the book
     * @param author as the author of the book
     * @param year as the published year of the book
     * @param owner as the owner of the book which is a student object
     */
    public Book(String title, String author, String year, Student owner) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.owner = owner;
        this.isAvailable = true;
        this.borrower = null;

    }

    /**
     * another constructor for creating the book this one passes the availability and
     * set the borrower to null.
     * @param title
     * @param author
     * @param year
     * @param owner
     * @param available
     */
   public Book(String title, String author, String year, Student owner, boolean available) {

      this.title = title;
      this.author = author;
      this.year = year;
      this.owner = owner;
      this.isAvailable = available;
      this.borrower = null;

   }

    /**
     * This constructor creates a book with only three parameters such as title, author and year
     * the owner and the borrower are set to null and the availability to true.
     * @param title
     * @param author
     * @param year
     */
    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.owner = null;
        this.isAvailable = true;
        this.borrower = null;
    }

    /**
     * The method simply return the title of the book.
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * The method sets the title of the book the same as the parameter which is passed to it.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The type of the method is String and returns the author of the book.
     * @return author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * The method simply sets the author of the book to the passing parameter.
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * The method simply returns the year of the book having the type String.
     * doing java doc is nonsense for all these setters and getters but I have to do it.
     * @return year on which the book is published
     */
    public String getYear() {
        return year;
    }

    /**
     * This method sets the year od the book.
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * The method returns the owner of the book which is a type Student.
     * @return owner of the book
     */
    public Student getOwner() {
        return owner;
    }

    /**
     * This method sets the owner of the book.
     * @param owner
     */
    public void setOwner(Student owner) {
        this.owner = owner;
    }

    /**
     * The method return a boolean value for the book being available or not available.
     * @return isAvailable
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * This method sets teh availability of the book as true or false.
     * @param isAvailable
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * the method returns the borrower of the book having the type Student.
     * @return borrower  of the book if a user borrowed the book.
     */
    public Student getBorrower()
   {
      return borrower;
   }

    /**
     * This method sets the borrower of the book.
     * @param borrower
     */
   public void setBorrower(Student borrower)
   {
      this.borrower = borrower;
   }

    /**
     * The method simply returns string value of the book.
     * @return string value of all the fields of the book
     */
   public String toString() {
        return "title=" + title + ", author=" + author
                + ", year=" + year + ", isAvailable: " +isAvailable +", owner: "+this.owner.getUsername();
    }

   /**
    * Returns a String representation of the Book object in specific format
    * for display in Library
    * @return String
    */
    public String toStringForLibrary() {
        return "title= " + title + ", author= " + author
                + ", year= " + year + "owner= " + owner.getUsername();
    }

   /**
    * Returns true is the title, author and year of book are identical with the Object passed as argument.
    * Returns false if the latter.
    * @param obj
    * @return boolean
    */
    public boolean equals(Object obj) {
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        return this.title.equals(other.title) && this.author.equals(other.author)
                && this.year.equals(other.year);
    }

}
