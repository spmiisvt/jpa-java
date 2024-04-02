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
			Student anna = new Student("Anna", "Fortran", "anna2003@yandex.ru", 20);

			System.out.println("Добавили студентов James и Anna");
			studentRepository.saveAll(List.of(james, anna));

			System.out.println("Количество студентов: ");
			System.out.println(studentRepository.count());

			studentRepository.findById(2L).ifPresentOrElse(
					System.out::println,
					() -> System.out.println("Student with ID 2 not found"));

			studentRepository.findById(3L).ifPresentOrElse(
					System.out::println,
					() -> System.out.println("Student with ID 2 not found"));

			System.out.println("Получаем всех студентов");
			List<Student> students = studentRepository.findAll();
			students.forEach(System.out::println);

			System.out.println("Удаляем студента James");
			studentRepository.deleteById(1L);

			System.out.println("Количество студентов: ");
			System.out.println(studentRepository.count());
		};
	}
}
