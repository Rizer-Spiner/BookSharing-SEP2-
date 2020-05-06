package test;

import model.domain.Book;
import model.domain.Student;

import java.util.ArrayList;

public class StudentList
{
    private ArrayList<Student> studentList;

    public StudentList() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if(student==null){

        }
       else{ studentList.add(student);}
    }

    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    public ArrayList<Student> getStudents() {
        return studentList;
    }



    public void borrow(Student student, Book book) {
        if((!(book.getOwner().equals(student)))&&book.isAvailable())
        {

            for (int i = 0; i < studentList.size(); i++)
            {
                if(student.equals(studentList.get(i)))
                {
                    book.setAvailable(false);
                    studentList.get(i).getBookList().addBook(book);

                }


                if(book.getOwner().equals(studentList.get(i)))
                {
                    for (int j = 0;
                         j < studentList.get(i).getBookList().size(); j++)
                    {
                        if (book.equals(
                              studentList.get(i).getBookList().getBookList()
                                    .get(j)))
                            studentList.get(i).getBookList().getBookList()
                                  .get(j).setAvailable(false);
                    }
                }
            }


        }


    }

    public int size() {
        return studentList.size();
    }

}
