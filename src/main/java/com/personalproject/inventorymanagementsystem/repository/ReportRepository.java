package com.personalproject.inventorymanagementsystem.repository;
import com.personalproject.inventorymanagementsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReportRepository extends JpaRepository<Report, Integer> {
}
