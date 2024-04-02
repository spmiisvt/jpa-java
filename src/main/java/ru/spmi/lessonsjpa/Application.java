package ru.spmi.lessonsjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student james = new Student("James", "Bond", "james007@gmail.com", 21);
			Student james2 = new Student("James", "Bender", "james008@gmail.com", 25);
			Student anna = new Student("Anna", "Fortran", "anna2003@yandex.ru", 20);
			studentRepository.saveAll(List.of(james, anna, james2));

			studentRepository.findStudentByEmail("james007@gmail.com").ifPresentOrElse(
					System.out::println,
					() -> System.out.println("Student with email = james007@gmail.com not found"));

			studentRepository.selectStudentsWhereFirstNameAndAgeGreaterOrEqual("James", 22)
					.forEach(System.out::println);

			studentRepository.selectStudentsWhereFirstNameAndAgeGreaterOrEqualNative("James", 22)
					.forEach(System.out::println);

			System.out.println("Deleting James 2");
			System.out.println(studentRepository.deleteStudentById(3L));
			System.out.println(studentRepository.count());

		};
	}
}
