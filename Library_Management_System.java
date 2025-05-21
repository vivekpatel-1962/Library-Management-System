import java.util.Scanner;

class book {
    public int sNo;
    public String bookName;
    public String authorName;
    public int bookQty;
    public int bookQtyCopy;

    Scanner sc = new Scanner(System.in);

    public book() {
        System.out.println("Enter Serial No of Book:");
        this.sNo = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Book Name:");
        this.bookName = sc.nextLine();
        System.out.println("Enter Author Name:");
        this.authorName = sc.nextLine();
        System.out.println("Enter Quantity of Books:");
        this.bookQty = sc.nextInt();
        bookQtyCopy = this.bookQty;
    }
}

class books {
    book theBooks[] = new book[50];
    public static int count;
    Scanner sc = new Scanner(System.in);

    public int compareBookObjects(book b1, book b2) {
        if (b1.bookName.equalsIgnoreCase(b2.bookName)) {
            System.out.println("Book of this Name Already Exists.");
            return 0;
        }
        if (b1.sNo == b2.sNo) {
            System.out.println("Book of this Serial No Already Exists.");
            return 0;
        }
        return 1;
    }

    public void addBook(book b) {
        for (int i = 0; i < count; i++) {
            if (this.compareBookObjects(b, this.theBooks[i]) == 0)
                return;
        }

        if (count < 50) {
            theBooks[count] = b;
            count++;
        } else {
            System.out.println("No Space to Add More Books.");
        }
    }

    public void searchBySno() {
        System.out.println("\t\t\t\tSEARCH BY SERIAL NUMBER\n");

        int sNo;
        System.out.println("Enter Serial No of Book:");
        sNo = sc.nextInt();

        int flag = 0;
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println(theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" +
                        theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
                flag++;
                return;
            }
        }
        if (flag == 0)
            System.out.println("No Book for Serial No " + sNo + " Found.");
    }

    public void searchByAuthorName() {
        System.out.println("\t\t\t\tSEARCH BY AUTHOR'S NAME");
        System.out.println("Enter Author Name:");
        String authorName = sc.nextLine();
        int flag = 0;
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (authorName.equalsIgnoreCase(theBooks[i].authorName)) {
                System.out.println(theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" +
                        theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
                flag++;
            }
        }
        if (flag == 0)
            System.out.println("No Books of " + authorName + " Found.");
    }

public void showAllBooks() {
    System.out.println("\t\t\t\tSHOWING ALL BOOKS\n");
    System.out.printf("%-6s%-30s%-20s%-16s%-16s\n", "S.No", "Name", "Author", "Available Qty", "Total Qty");
    for (int i = 0; i < count; i++) {
        System.out.printf("%-6d%-30s%-20s%-16d%-16d\n", theBooks[i].sNo, theBooks[i].bookName, theBooks[i].authorName, theBooks[i].bookQtyCopy, theBooks[i].bookQty);
    }
}





    public void upgradeBookQty() {
        System.out.println("\t\t\t\tUPGRADE QUANTITY OF A BOOK\n");
        System.out.println("Enter Serial No of Book");
        int sNo = sc.nextInt();
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println("Enter No of Books to be Added:");
                int addingQty = sc.nextInt();
                theBooks[i].bookQty += addingQty;
                theBooks[i].bookQtyCopy += addingQty;
                flag++;
                return;
            }
        }
        if (flag == 0)
            System.out.println("No Book for Serial No " + sNo + " Found.");
    }

    public void dispMenu() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Enter 0 to Exit Application.");
        System.out.println("Enter 1 to Add new Book.");
        System.out.println("Enter 2 to Upgrade Quantity of a Book.");
        System.out.println("Enter 3 to Search a Book.");
        System.out.println("Enter 4 to Show All Books.");
        System.out.println("Enter 5 to Register Student.");
        System.out.println("Enter 6 to Show All Registered Students.");
        System.out.println("Enter 7 to Borrow a Book.");
        System.out.println("Enter 8 to Return a Book.");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public int isAvailable(int sNo) {
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                if (theBooks[i].bookQtyCopy > 0) {
                    System.out.println("Book is Available.");
                    return i;
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }
        System.out.println("No Book for Serial Number " + sNo +" Available in Library.");
        return -1;
    }

    public book borrowBook() {
        System.out.println("Enter Serial No of Book to be Borrowed:");
        int sNo = sc.nextInt();
        int bookIndex = isAvailable(sNo);
        if (bookIndex != -1) {
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }
        return null;
    }

    public void returnBook(book b) {
        for (int i = 0; i < count; i++) {
            if (b.sNo == theBooks[i].sNo) {
                theBooks[i].bookQtyCopy++;
                System.out.println("Book Returned Successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

class student {
    String studentName;
    String regNum;

    book borrowedBooks[] = new book[3];
    public int booksCount = 0;

    Scanner sc = new Scanner(System.in);

    public student() {
        System.out.println("Enter Student Name:");
        this.studentName = sc.nextLine();
        System.out.println("Enter Reg Number:");
        this.regNum = sc.nextLine();
    }

   public void showBorrowedBooks() {
    System.out.println("Books Borrowed by " + studentName + ":");
    if (booksCount > 0) {
        System.out.printf("%-5s%-25s%-25s\n", "S.No", "Name", "Author");
        for (int i = 0; i < booksCount; i++) {
            System.out.printf("%-5d%-25s%-25s\n", borrowedBooks[i].sNo, borrowedBooks[i].bookName, borrowedBooks[i].authorName);
        }
    } else {
        System.out.println("No books borrowed by this student.");
    }
}

}

class students {
    Scanner sc = new Scanner(System.in);
    student theStudents[] = new student[50];
    public static int count = 0;

    public void addStudent(student s) {
        for (int i = 0; i < count; i++) {
            if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
                System.out.println("Student of Reg Num " + s.regNum + " is Already Registered.");
                return;
            }
        }
        if (count <= 50) {
            theStudents[count] = s;
            count++;
        }
    }

    public void showAllStudents() {
        
        for (int i = 0; i < count; i++) {
            System.out.println("Student Name\t\tReg Number");
            System.out.println(theStudents[i].studentName + "\t\t\t" + theStudents[i].regNum);
            theStudents[i].showBorrowedBooks();
            System.out.println();
        }
    }

    public int isStudent() {
        System.out.println("Enter Reg Number:");
        String regNum = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (theStudents[i].regNum.equalsIgnoreCase(regNum)) {
                return i;
            }
        }
        System.out.println("Student is not Registered.");
        System.out.println("Get Registered First.");
        return -1;
    }

    public void borrowBook(books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            book b = book.borrowBook();
            if (b != null) {
                if (theStudents[studentIndex].booksCount < 3) {
                    theStudents[studentIndex].borrowedBooks[theStudents[studentIndex].booksCount] = b;
                    theStudents[studentIndex].booksCount++;
                    System.out.println("Book Borrowed Successfully!");
                } else {
                    System.out.println("Maximum books limit reached. You cannot borrow more than 3 books.");
                }
            } else {
                System.out.println("Book not found.");
            }
        }
    }

    public void returnBook(books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            if (theStudents[studentIndex].booksCount > 0) {
                System.out.println("Enter Serial No of Book to be Returned:");
                int sNo = sc.nextInt();
                for (int i = 0; i < theStudents[studentIndex].booksCount; i++) {
                    if (theStudents[studentIndex].borrowedBooks[i].sNo == sNo) {
                        book.returnBook(theStudents[studentIndex].borrowedBooks[i]);
                        theStudents[studentIndex].borrowedBooks[i] = null;
                        theStudents[studentIndex].booksCount--;
                        System.out.println("Book Returned Successfully!");
                        return;
                    }
                }
                System.out.println("Book not borrowed by this student.");
            } else {
                System.out.println("No books borrowed by this student.");
            }
        }
    }
}

class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Student Library!");
        System.out.println("Please Select From The Following Options:");
        books ob = new books();
        students obStudent = new students();
        int choice;

        do {
            ob.dispMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    book b = new book();
                    ob.addBook(b);
                    break;
                case 2:
                    ob.upgradeBookQty();
                    break;
                case 3:
                    ob.searchBySno();
                    break;
                case 4:
                    ob.showAllBooks();
                    break;
                case 5:
                    student s = new student();
                    obStudent.addStudent(s);
                    break;
                case 6:
                    obStudent.showAllStudents();
                    break;
                case 7:
                    obStudent.borrowBook(ob);
                    break;
                case 8:
                    obStudent.returnBook(ob);
                    break;
                case 0:
                    System.out.println("Thank you for using the Library!");
                    break;
                default:
                    System.out.println("CHOICE SHOULD BE BETWEEN 0 TO 8.");
                    break;
            }
        } while (choice != 0);
    }
}