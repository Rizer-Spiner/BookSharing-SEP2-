Drop schema BookSharing CASCADE;
Create schema BookSharing;
set schema 'booksharing';

 CREATE DOMAIN RequestStatus AS VARCHAR(20) NOT NULL
 CHECK (
      VALUE IN ('waiting', 'accepted','denied')) ;

  CREATE DOMAIN category AS VARCHAR(30) CHECK (
  VALUE IN ('Academic', 'Art', 'Music', 'Biographies','Cooking','History','Romance','miscellaneous')
  );

  CREATE DOMAIN username VARCHAR(20)
	CHECK(value !~ '\s');

  CREATE DOMAIN password_ VARCHAR(20)
	CHECK(value !~ '\s');

  CREATE DOMAIN email_ AS VARCHAR
	CHECK(VALUE ~ '^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$');

CREATE DOMAIN borrowDate_ AS DATE CHECK (VALUE IN (CURRENT_DATE));

  CREATE TABLE Person
(
  username      username      NOT NULL  PRIMARY KEY ,
  password_      password_      NOT NULL ,
  isAdmin       boolean,
  isStudent     boolean
  );

CREATE TABLE Email
(
  username      username      NOT NULL ,
  email         VARCHAR(120)    NOT NULL ,
  PRIMARY KEY   (username, email),
  FOREIGN KEY   (username) REFERENCES Person(username)  ON DELETE CASCADE );

CREATE TABLE PhoneNo
(
  username      username      NOT NULL ,
  phoneNo       VARCHAR(20)       NOT NULL ,
  PRIMARY KEY   (username, phoneNo),
  FOREIGN KEY   (username) REFERENCES Person(username)  ON DELETE CASCADE );

  CREATE TABLE Book
(
  bookId                SERIAL			  PRIMARY KEY ,
  username              username	     NOT NULL REFERENCES Person(username) ON DELETE CASCADE,
  title                 varchar (120)    NOT NULL,
  year_                 varchar (8),
  availbility           boolean
  );
  CREATE TABLE Request
(
  username      username     	 NOT NULL ,
  bookId       INTEGER		      NOT NULL ,
  date_          DATE   NOT NULL,
  status        RequestStatus    NOT NULL,
  PRIMARY KEY   (date_, username,bookId),
  FOREIGN KEY   (username) REFERENCES Person(username) ON DELETE CASCADE,
  FOREIGN KEY   (bookId) REFERENCES Book(BookId)  ON DELETE CASCADE
  );
  CREATE TABLE Loan
(
  borrowerUsername      username	      NOT NULL ,
  bookId                INTEGER		      NOT NULL ,
  borrowDate            borrowDate_        NOT NULL,
   PRIMARY KEY          (borrowerUsername, bookId,borrowDate),
  FOREIGN KEY          (borrowerUsername) REFERENCES Person(username) ON DELETE CASCADE,
  FOREIGN KEY          (bookId) REFERENCES Book(BookId) ON DELETE CASCADE
  );

;
  CREATE TABLE Author
  (
  authorId              SERIAL		,
  bookId                INTEGER			     NOT NULL,
  fName                 varchar (120)   NOT NULL,
  lName                 varchar (120)   NOT NULL,
  PRIMARY KEY          (authorId,bookId),
  FOREIGN KEY          (bookId) REFERENCES Book(bookId) ON DELETE CASCADE
  );



  CREATE TABLE WRITTENBY
(
  bookId                INTEGER,
  authorId              INTEGER,
  PRIMARY KEY          (bookId, authorId),
  FOREIGN KEY          (authorId,bookId) REFERENCES Author(authorId,bookId)  ON DELETE CASCADE
  );
CREATE TABLE Category_
(
  catId               SERIAL      primary key ,
  catName             category          NOT NULL
);
 CREATE TABLE Contain
(
  catId                INTEGER		       ,
  bookId             INTEGER           ,
  PRIMARY KEY          (bookId, catId),
  FOREIGN KEY          (catId) REFERENCES Category_(catId) ON DELETE CASCADE,
  FOREIGN KEY          (bookId) REFERENCES Book(BookId) ON DELETE CASCADE
);

-- -- writing Queries
--
-- INSERT INTO Person(username, password_, isAdmin, isStudent)
-- 	VALUES('280261','joje',true,true),
-- 	('281039','poune',true,true),
-- 	('28731','dorci',false,true),
-- 	('280971','zuzi',false,true),
-- 	('281452','roxi',false,true);
-- INSERT INTO Book (username,title,year_,availbility)
-- 	VALUES ('28731','JAVA1','2019',TRUE),
-- 			('280971','JAVA2','2018',TRUE),
-- 			('281452','JAVA3','2017',TRUE),
-- 			('28731','JAVA4','2019',TRUE);
--
-- INSERT INTO Author(bookId, fName, lName)
-- 	VALUES (1,'ALEX','GHOLAMI'),
-- 			(2,'ABOLFAZL', 'GOOGOOSH'),
-- 			(3,'NAGHI', 'KHANBABA'),
-- 			(4,'TAGHI', 'KHANBABA');
--
-- INSERT INTO Email(username,email)
-- 	VALUES('28731','ATI@VIA.DK');
-- INSERT INTO PhoneNo( username, phoneNo)
-- 	VALUES('28731','12345678');
--
-- INSERT INTO Loan (borrowerUsername, bookId, borrowDate, returnDate)
-- 	VALUES('280971',1,CURRENT_DATE, CURRENT_DATE+20);
--
--
-- 	select * from Book;
-- --1 view all books
-- SELECT title, year_, availbility, fName, lName
-- 	FROM Book, Author
-- 	where Book.bookId= Author.bookId;
--
-- --2 search by author  LAST name
-- SELECT title, year_, availbility
-- 	FROM Book, Author
-- 	WHERE  Book.bookId= Author.bookId
-- 		AND lName='KHANBABA';
--
-- --3 HAVING ONLY FIRST NAME
-- SELECT title, year_, availbility
-- 	FROM Book, Author
-- 	WHERE  Book.bookId= Author.bookId
-- 		AND fName='ALEX';
--
-- --4 HAVING BOTH FIRST NAME AND LAST NAME
-- SELECT title, year_, availbility
-- 	FROM Book, Author
-- 	WHERE  Book.bookId= Author.bookId
-- 		AND fName='NAGHI' AND lName='KHANBABA';
--
-- --5 search book by title
-- SELECT title, year_, availbility
-- 	FROM Book
-- 		WHERE title='JAVA1';
--
-- --6 search by book title and author name
-- SELECT title, year_, availbility
-- 	FROM Book, Author
-- 	WHERE Book.bookId = Author.bookId
-- 		And fName='ALEX' AND lName='GHOLAMI'
-- 		And title='JAVA1'
-- 				order by title;
--
-- --7 view all own books
--
-- SELECT title, year_, availbility
-- 	FROM Book, Person
-- 		WHERE Person.username= Book.username
-- 				AND Book.username='28731';
--
-- --8 GET CONTACT INFO
-- SELECT email,phoneNo
-- 	FROM Person, Email, PhoneNo,Book
-- 		WHERE Book.username=Person.username
-- 			AND Person.username= Email.username
-- 				And Person.username= PhoneNo.username
-- 				AND title='JAVA1';
--
-- --9 FIND THE BORROWER NAME
-- SELECT borrowerUsername
-- 	FROM Loan, Book
-- 		WHERE Loan.bookId= Book.bookId
-- 			AND title='JAVA1';
--
-- --CREATE TRIGGER FOR UPPERCASE THE NAME AND LAST NAME IN AUTHOR
-- CREATE OR REPLACE FUNCTION upper_name()
--   RETURNS TRIGGER AS
--   $BODY$
-- BEGIN
--     NEW.fName= UPPER(NEW.fName);
--     NEW.LName= UPPER(NEW.LName);
--
--     RETURN NEW;
-- END;
-- $BODY$ LANGUAGE PLPGSQL;
--
-- CREATE TRIGGER upper_name_trigger
--   BEFORE INSERT OR UPDATE ON Author
--   FOR EACH ROW
-- EXECUTE PROCEDURE upper_name();
-- UPDATE Author SET fName=fName;
--
-- -- trigger to set the date to current date for request
--
-- CREATE FUNCTION set_date()
-- RETURNS TRIGGER AS $$
-- BEGIN
-- NEW.date_ = CURRENT_DATE ;
-- RETURN NEW;
-- END;
-- $$ LANGUAGE PLPGSQL;
--
-- CREATE TRIGGER set_date_trigger
-- BEFORE INSERT ON Request
-- FOR EACH ROW
-- EXECUTE PROCEDURE set_date();







-- trigger to update loan

/*drop function update_loan() cascade ;
CREATE or REPLACE FUNCTION update_loan()
RETURNS TRIGGER AS $BODY$
BEGIN
IF status='accepted'THen 
join 
INSERT INTO Loan (borrowerUsername, bookId, borrowDate, returnDate)
    VALUES (borrowerUsername, bookId, '2019-05-23','2019-05-30');
RETURN NEW;
END IF;
END;
$BODY$ LANGUAGE PLPGSQL;


CREATE TRIGGER update_loan
After UPDATE ON Request
FOR EACH ROW
EXECUTE PROCEDURE update_loan();


update Request set status= 'accepted'where bookId=4;
insert into Request (username, bookId, date_, status) values ('28731',4,'2019-05-23','waiting');*/
select* from Person;
select* from Book;
select* from Author;
select *from Loan;
select * from Request;
delete from Request;
--
--
-- insert into Request (username, bookId, date_, status)
-- (select bookId,Person.username  from Book, Person where title= 'java1' and Person.username='28731');
-- drop table Person cascade ;

SELECT Book.bookId FROM Booksharing.Book WHERE Book.availbility= false AND title= "Where's my cheese?" AND username='BiteMe';
Insert  into Loan (borrowerUsername, bookId, borrowDate) VALUES ('Bite', 1, '2018-05-30');

Update Book SET availbility = true where bookId=1;