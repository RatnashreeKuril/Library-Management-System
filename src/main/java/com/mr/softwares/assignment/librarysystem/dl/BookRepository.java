package com.mr.softwares.assignment.librarysystem.dl;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;
import org.springframework.data.repository.*;
import java.util.*;
public interface BookRepository extends CrudRepository<Book,Long>
{
public Book findByIsbn(String isbn);
public List<Book> findByAuthor(Author author);
public List<Book> findByLibraryBranch(LibraryBranch libraryBranch);
}