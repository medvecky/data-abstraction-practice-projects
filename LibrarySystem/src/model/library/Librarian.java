package model.library;

import model.book.Book;

public class Librarian {

    private String name;
    private int age;
    private Library managingLibrary;
    private Book favBook;

//  REQUIRES: All params not null
//  MODIFIES: this
//  EFFECTS: Create new librarian object
    public Librarian(String name, int age, Library lib, Book bk) {
        this.name = name;
        this.age = age;
        this.managingLibrary = lib;
        this.favBook = bk;
    }

    // EFFECTS: returns name of Librarian
    public String getName() {
        return this.name;
    }
//    EFFECTS: returns age of librarian
    public int getAge() {
        return this.age;
    }

//    EFFECTS: returns managing library
    public Library getManagingLibrary() {
        return this.managingLibrary;
    }

//    EFFECTS: returns favorite book
    public Book getFavBook() {
        return this.favBook;
    }

    // REQUIRES: lib != null
    // MODIFIES: this
    // EFFECTS: changes this librarian's managing library to the one given.
    //          Look carefully at the implementation of library - does it
    //          also have an associated librarian field? Does it make a
    //          difference in the implementation of this method?
    public boolean changeLibrary(Library lib) {
       if(managingLibrary.equals(lib)) {
           return false;
       }

       managingLibrary = lib;
       return true;
    }


}
