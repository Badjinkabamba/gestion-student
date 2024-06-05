package org.formation.gestionstudent;

import org.formation.gestionstudent.entities.Payment;
import org.formation.gestionstudent.entities.PaymentStatus;
import org.formation.gestionstudent.entities.PaymentType;
import org.formation.gestionstudent.entities.Student;
import org.formation.gestionstudent.repository.PaymentRepository;
import org.formation.gestionstudent.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GestionStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionStudentApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .code("111111")
                    .firsName("Ibrahima")
                    .lastName("Ndiaye")
                    .programId("SDIA")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .code("111122")
                    .firsName("Amina")
                    .lastName("Ly")
                    .programId("SDIA")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .code("111133")
                    .firsName("Saliou")
                    .lastName("Gueye")
                    .programId("GLSID")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .code("111144")
                    .firsName("Baye Amadou")
                    .lastName("Diop")
                    .programId("BDCC")
                    .build());

            PaymentType[] paymentTypes= PaymentType.values();
            Random random= new Random();
            studentRepository.findAll().forEach(student -> {
                for (int i=0; i<10; i++){
                    int index= random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int)(Math.random() * 20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(student)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
    }

}
