package model.domain;

import java.util.ArrayList;

/**
 * The Library class acts as a place holder for the list of books in the model.Contains an
 * instance variable from Book class having the type ArrayList.
 *
 * @Author Group4
 */
public class Library {

    private ArrayList<Book> books;

    /**
     * The purpose of the constructor is to initialize the instance variable
     * we are going to repeat it 5 more times.
     */
    public Library() {
        this.books = new ArrayList<Book>();
    }

    /**
     * The method loops through the list and returns
     * the string value of all the objects in the list.
     *
     * @return s as a string value of the lists.
     */
    public String viewAll() {
        String s = "";
        for (int i = 0; i < books.size(); i++) {

            s += "(" + i + ")" + books.get(i).toString() + '\n';
        }
        return s;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    /**
     * The method is simply add the book
     * which is passing as a parameter
     * to the list.
     *
     * @param book
     */
    public void addToLibrary(Book book) {
        books.add(book);
    }

    /**
     * This method is removing a book from the list by passing an object of the book
     * as a parameter.
     *
     * @param book
     */
    public void removeFromLibrary(Book book) {
        books.remove(book);
    }

    /**
     * This method is removing the book from the
     * list by passing an index having the type int as the parameter.
     *
     * @param index
     */
    public void removeFromLibrary(int index) {
        books.remove(index);
    }

    /**
     * The method simply return an integer value
     * representing the size of the list.
     *
     * @return size of the list
     */
    public int size() {
        return books.size();
    }

    /**
     * The method returns a book by the index
     * which is passed as a parameter.
     *
     * @param index
     * @return book on the asked index
     */
    public Book get(int index) {
        return books.get(index);
    }

    /**
     * Returns a String representation of the library
     * @return String
     */
    public String toString() {

        String s = "";
        for (int i = 0; i < books.size(); i++) {
            s += "\n " + (i + 1) + ") " + books.get(i);
        }
        return s;
    }

    /**
     * This method is setting the availability of the book
     * to false is the act of borrowing is happened.
     *
     * @param book
     */
    public void borrow(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (book.equals(books.get(i))) {
                books.get(i).setAvailable(false);
            }
        }
    }

    /**
     * This method is returning array list of books for the search result.
     * It loops through the list and uses the equal method if the book is found
     * it will be added to a new list and at the end the new
     * list will be returned as the result of the search.
     *
     * @param book
     * @return result with a type of arrayList of books
     */
    public ArrayList<Book> getSearchResult(Book book) {

        ArrayList<Book> result = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (book.equals(books.get(i))) {
                result.add(books.get(i));
            }
        }

        return result;
    }

    /**
     * this method search through the list with  passing two parameters as
     * for the search, the user can  search by a the title of the book
     * or the author of the book. it will loop through the list having these values
     * for a specific book if the book is found in the list it will be added to a new arrayList
     * of  books and the result will be return as the result of the search.
     *
     * @param category as the title of the book
     * @param input as the author of the book
     * @return result as an arrayList of books from the search
     */
    public ArrayList<Book> getSearchResult(String category, String input) {
        ArrayList<Book> result = new ArrayList<>();
        if (category.equals("TITLE")) {
            for (int i = 0; i < books.size(); i++) {
                if (input.equals(books.get(i).getTitle())) {
                    result.add(books.get(i));
                }
            }
        } else if (category.equals("AUTHOR")) {
            for (int i = 0; i < books.size(); i++) {
                if (input.equals(books.get(i).getAuthor())) {
                    result.add(books.get(i));
                }
            }
        }

        return result;
    }
}

