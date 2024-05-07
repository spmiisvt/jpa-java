package ru.spmi.lessonsjpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));

			student.addBook(
					new Book("Clean Code", LocalDateTime.now().minusDays(4)));

			student.addBook(
					new Book("Spring Data JPA", LocalDateTime.now()));

			student.addBook(
					new Book("System Design for students", LocalDateTime.now().minusYears(1)));

			StudentIdCard studentIdCard = new StudentIdCard(
					"123456789",
					student);

			student.setStudentIdCard(studentIdCard);

			student.enrollToCourse(new Course("Java beginner", "IT"));
			student.enrollToCourse(new Course("Spring Data JPA", "IT"));


			studentRepository.save(student);

			studentRepository.findById(1L).ifPresent(s -> {
				System.out.println("Fetch book lazy...");
				List<Book> books = student.getBooks();
				books.forEach(book -> {
					System.out.println(
							s.getFirstName() + " borrowed " + book.getBookName());
				});
			});


		};
	}


	private void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i < 20; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));
			studentRepository.save(student);
		}
	}
}
