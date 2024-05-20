package com.mr.softwares.assignment.librarysystem.dl;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;

import java.util.List;

import org.springframework.data.repository.*;
public interface FineRepository extends CrudRepository<Fine,Long>
{
public Fine findByBorrowingRecord(BorrowingRecord borrowingRecord);




}