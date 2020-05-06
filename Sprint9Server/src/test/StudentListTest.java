package test;

import model.domain.Book;
import model.domain.Student;

import static org.junit.Assert.assertEquals;

public class StudentListTest
{

    private StudentList list;
    @org.junit.Before
    public void setUp() throws Exception {
        list= new StudentList();
    }

    @org.junit.Test
    public void addStudent() {
        Student s1= new Student("ati","joj","22114","vv@ff.com");
        Student s2= new Student("mnb","23m","2132","mmv@ff.com");
        Student s3= new Student("omvc","1sx2","85214","ygcx@ff.com");
        list.addStudent(s1);
        list.addStudent(null);
        list.addStudent(s2);
        list.addStudent(s3);
        assertEquals(4,list.size());

    }

    @org.junit.Test
    public void removeStudent() {
        Student s1= new Student("ati","joj","22114","vv@ff.com");
        Student s2= new Student("mnb","23m","2132","mmv@ff.com");
        Student s3= new Student("omvc","1sx2","85214","ygcx@ff.com");
        list.addStudent(s1);
        list.addStudent(null);
        list.addStudent(s2);
        list.addStudent(s3);
        list.removeStudent(s1);
        list.removeStudent(null);
        assertEquals(2,list.size());

    }

    @org.junit.Test
    public void getStudents() {
        Student s1= new Student("ati","joj","22114","vv@ff.com");
        Student s2= new Student("mnb","23m","2132","mmv@ff.com");
        Student s3= new Student("omvc","1sx2","85214","ygcx@ff.com");
        list.addStudent(s1);
        list.addStudent(null);
        list.addStudent(s2);
        list.addStudent(s3);
        assertEquals(s2,list.getStudents().get(2));
        System.out.println(list.getStudents());
    }

    @org.junit.Test
    public void borrow() {
        Book b1= new Book("j2","aa","2010");
        Book b2= new Book("5j","mv","2018");
        Book b3= new Book("cm","oo","2015");
        Book b4= new Book("lb","lk","2000");

        Student s1= new Student("ati","joj","22114","vv@ff.com");
        Student s2= new Student("mnb","23m","2132","mmv@ff.com");
        Student s3= new Student("omvc","1sx2","85214","ygcx@ff.com");
       s1.registerNewBook(b1);
       s1.registerNewBook(b2);
       s2.registerNewBook(b3);
       s3.registerNewBook(b4);
        list.addStudent(s1);
        list.addStudent(s2);
        list.addStudent(s3);
        list.borrow(s1,b3);
        assertEquals(false,s1.getBookList().getBookList().get(2).isAvailable());
        assertEquals(false,s2.getBookList().getBookList().get(0).isAvailable());

        System.out.println(s1.getBookList());

        System.out.println(s2.getBookList());
    }

    @org.junit.Test
    public void size() {
        Student s1= new Student("ati","joj","22114","vv@ff.com");
        Student s2= new Student("mnb","23m","2132","mmv@ff.com");
        Student s3= new Student("omvc","1sx2","85214","ygcx@ff.com");
        list.addStudent(s1);
        list.addStudent(null);
        list.addStudent(s2);
        list.addStudent(s3);
        assertEquals(4,list.size());
    }
}