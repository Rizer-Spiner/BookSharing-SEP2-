package model.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class BookList creates a list of books with the type ArrayList and
 * it implements Serializable. It has one instance variable of  ArrayList type of Book.
 *
 * @Author group 4
 */
public class BookList implements Serializable {
    private ArrayList<Book> list;

    /**
     * The purpose of the constructor is to initialize the instance
     * variable by creating an empty list.
     */
    public BookList() {

        this.list = new ArrayList<Book>();

    }

    /**
     * The method passes a book as a parameter and adds it to the list and
     * it will check so null cannot be added to the list.
     *
     * @param book
     */
    public void addBook(Book book) {
        if (book == null) {

        } else list.add(book);

    }

    /**
     * The method removes a specific book which is passed as a parameter
     * and it checks if the list is greater than 1 the act of removing can be done otherwise
     * the book cannot be removed from the list.
     *
     * @param book
     */
    public void removeBook(Book book) {
        if (list.size() > 1) {
            if (book.isAvailable()) {
                list.remove(book);
            }
        } else {

        }
    }

    /**
     * The method  is removing the book by passing the index of the book.
     * it checks if the book is available or not , if the book is not available
     * means that it is borrowed by someone so it will throw an exception and it cannot be deleted.
     *
     * @param index of the book in the list
     */
    public void removeBookByIndex(int index) {
        Book book = list.get(index);
        if (book.isAvailable()) {
            list.remove(book);
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * returns a String representation with all Book elements for display
     * @return String
     */
    public String viewList() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += "(" + (i + 1) + ")" + list.get(i).toString() + "\n";
        }

        return s;
    }

    /**
     * This method is returning ArrayList of books
     * @return list
     */
    public ArrayList<Book> getBookList() {
        return list;
    }

    /**
     * The method simply returns the size of the list.
     * @return size of the bookList
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a String representation of the BookList
     * @return String
     */
    public String toString() {

        String bs = "\n" + "Book List: ";
        for (int i = 0; i < list.size(); i++) {
            bs += "\n" + (i + 1) + ") " + list.get(i);
        }

        return bs;

    }
}

