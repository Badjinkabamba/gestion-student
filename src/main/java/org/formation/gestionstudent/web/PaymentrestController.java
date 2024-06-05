package org.formation.gestionstudent.web;

import org.formation.gestionstudent.entities.Payment;
import org.formation.gestionstudent.entities.PaymentStatus;
import org.formation.gestionstudent.entities.PaymentType;
import org.formation.gestionstudent.entities.Student;
import org.formation.gestionstudent.repository.PaymentRepository;
import org.formation.gestionstudent.repository.StudentRepository;
import org.formation.gestionstudent.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentrestController {

    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    private PaymentService paymentService;

    public PaymentrestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }
    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id ){
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students/{code}/payment")
    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }


    @GetMapping(path = "/students/{code}")
    public Student getStudentsByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }


    @GetMapping(path = "/studentsByProgramId")
    public List<Student> getStudentsByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id ){
        return paymentService.updatePaymentStatus(status,id);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount,
                               PaymentType type, String studentCode) throws IOException {

        return paymentService.savePayment(file,date,amount,type,studentCode);
    }

    @GetMapping(path = "paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable long paymentId) throws IOException {

        return paymentService.getPaymentFile(paymentId);
    }
}
