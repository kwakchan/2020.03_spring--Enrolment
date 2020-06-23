package kr.ac.ks.app;

import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(StudentRepository studentRepository,
									LessonRepository lessonRepository){
		return (args) -> {
			studentRepository.save(Student.builder().name("박새로이").email("new@park.com").build());
			studentRepository.save(Student.builder().name("조이서").email("joe@love.com").build());
			lessonRepository.save(Lesson.builder().name("웹프로그래밍응용").quota(15).build());
			lessonRepository.save(Lesson.builder().name("머신러닝").quota(15).build());
		};
	}
}
