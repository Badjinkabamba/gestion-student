package org.formation.gestionstudent.repository;

import org.formation.gestionstudent.entities.Payment;
import org.formation.gestionstudent.entities.PaymentStatus;
import org.formation.gestionstudent.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
