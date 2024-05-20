package com.mr.softwares.assignment.librarysystem.dl;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecord,Long>
{

    @Query("SELECT br.book, COUNT(br) AS borrowCount FROM com.mr.softwares.assignment.librarysystem.dl.pojo.BorrowingRecord br GROUP BY br.book ORDER BY borrowCount DESC")
    List<Object[]> findMostBorrowedBooks();

    @Query("SELECT br FROM com.mr.softwares.assignment.librarysystem.dl.pojo.BorrowingRecord br WHERE upper(br.status) like 'OVERDUE'")
    List<BorrowingRecord> findOverdueBooks();
}