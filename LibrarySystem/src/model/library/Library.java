package model.library;

import model.book.Book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Library {

    private Librarian manager;
    private String name;
    private List<Library> branches;
    private List<Book> referenceBooks;
    private List<Book> fictionBooks;
    private List<Book> nonfictionBooks;
    private List<Book> textBooks;
    private List<Book> cookBooks;

    public Library(String nm, Librarian manager) {
       branches = new ArrayList<>();
       referenceBooks = new ArrayList<>();
       fictionBooks = new ArrayList<>();
       nonfictionBooks = new ArrayList<>();
       textBooks = new ArrayList<>();
       cookBooks = new ArrayList<>();
       this.name = nm;
       this.manager = manager;
    }

    // getters
    public String getName() {
       return this.name;
    }

    public Librarian getManager() {
       return  this.manager;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: stores the given Book bk into the appropriate container within this class
    public void storeBook(Book bk) {
       switch (bk.getType()) {
           case COOKING:
               cookBooks.add(bk);
               break;
           case FICTION:
               fictionBooks.add(bk);
               break;
           case TEXTBOOK:
               textBooks.add(bk);
                break;
           case REFERENCE:
               referenceBooks.add(bk);
               break;
           case NONFICTION:
               nonfictionBooks.add(bk);
       }
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is in the catalogue,
    //          regardless of its loan status, else return false
    public boolean inCatalogue(Book bk) {
        switch (bk.getType()) {
            case COOKING:
                return cookBooks.contains(bk);
            case FICTION:
                return fictionBooks.contains(bk);
            case TEXTBOOK:
                return textBooks.contains(bk);
            case REFERENCE:
                return referenceBooks.contains(bk);
            case NONFICTION:
                return nonfictionBooks.contains(bk);
            default:
                return false;
        }
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is available to loan
    //          Note: What requirements should a book meet to be available?
    public boolean canLoan(Book bk) {
        return !bk.onLoan();
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is available in the catalogue of this library's
    //          other branches; else, return false
    public boolean isInDiffBranch(Book bk) {
        for (Library branch : branches) {
            if (branch.inCatalogue(bk)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: set bk as being checked out from this library if possible
    //          return true if successful, else false
    public boolean checkOutBook(Book bk) {
        if (bk.onLoan()) {
            return false;
        }
        bk.nowOnLoan();
        return true;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: set bk as being back in the library if it has been borrowed previously
    //          return true if successful, otherwise false
    public boolean returnBook(Book bk) {
        if(bk.onLoan()) {
            bk.notOnLoan();
            return true;
        }
        return false;
    }

    // REQUIRES: manager != null
    // MODIFIES: this
    // EFFECTS: sets this library's librarian to manager; return true if successful, else false
    public boolean hireLibrarian(Librarian manager) {
        if(this.manager.equals(manager)) {
            return false;
        }
        this.manager = manager;
        this.manager.changeLibrary(this);
        return true;
    }


    // Utility method, do not touch its implementation
    public void printCatalogue() {
        List<Book> totalCatalogue = new LinkedList<>();
        totalCatalogue.addAll(this.cookBooks);
        totalCatalogue.addAll(this.fictionBooks);
        totalCatalogue.addAll(this.nonfictionBooks);
        totalCatalogue.addAll(this.textBooks);
        totalCatalogue.addAll(this.referenceBooks);

        for (Book b : totalCatalogue) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }


}
