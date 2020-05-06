package model.domain;

import java.util.ArrayList;

/**
 * StudentList class represents a list of all students who
 * registered in the system with books, having an arrayList of students as
 * an instance variable.
 *
 * @Author Group 4
 */
public class StudentList
{
   private ArrayList<Student> studentList;

   /**
    * The constructor initializing the instance variable by
    * creating a new arrayList.
    */
   public StudentList()
   {
      this.studentList = new ArrayList<>();
   }

   /**
    * adding a student to the list if the
    * object of student is not null.
    *
    * @param student
    */
   public void addStudent(Student student)
   {
      if (student == null)
      {

      }
      else
      {
         studentList.add(student);
      }

   }

   /**
    * removing a student from the list.
    *
    * @param student
    */
   public void removeStudent(Student student)
   {
      studentList.remove(student);
   }

   /**
    * @return studentList as an arrayList od students
    */
   public ArrayList<Student> getStudents()
   {
      return studentList;
   }

   /**
    * creating a borrow on a book for a student as a borrower.
    * if the book is found, it will set the book's availability to FALSE
    * the book will be added to the borrower's bookList and it should also
    * be unavailable in the lender's bookList
    *
    * @param student represents the borrower
    * @param book    represent the book
    */
   public void borrow(Student student, Book book)
   {

      for (int i = 0; i < studentList.size(); i++)
      {
         if (student.equals(studentList.get(i)))
         {
            book.setAvailable(false);
            studentList.get(i).getBookList().addBook(book);

         }
         if (book.getOwner().equals(studentList.get(i)))
         {
            for (int j = 0; j < studentList.get(i).getBookList().size(); j++)
            {
               if (book.equals(
                     studentList.get(i).getBookList().getBookList().get(j)))
               {
                  studentList.get(i).getBookList().getBookList().get(j)
                        .setAvailable(false);

               }

            }
         }
      }

   }

   /**
    * @return size of the studentList
    */
   public int size()
   {
      return studentList.size();
   }

   /**
    * Returns a String representation used in database
    * @return String
    */
   public String toStringForDB()
   {
      String s = "";
      for (int i = 0; i < studentList.size(); i++)
      {
         s += studentList.get(i).toString();
      }
      return s;
   }

}
