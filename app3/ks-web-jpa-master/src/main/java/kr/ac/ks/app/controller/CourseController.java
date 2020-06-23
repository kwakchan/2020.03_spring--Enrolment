package kr.ac.ks.app.controller;

import jdk.incubator.jpackage.internal.Log;
import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class CourseController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseController(StudentRepository studentRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    // 추가
    @GetMapping("/course")
    public String showCourseForm(Model model) {
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "courses/courseForm";
    }

    @PostMapping("/course")
    public String createCourse(@RequestParam("studentId") Long studentId,
                               @RequestParam("lessonId") Long lessonId
    ) {
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.setRegCnt(lesson.getRegCnt() + 1);
        Course course = Course.createCourse(student,lesson);
        Course savedCourse = courseRepository.save(course);
        return "redirect:/courses";
    }

    // 삭제
    @GetMapping("/courses/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Course course = courseRepository.findById(id).get();
//        courseRepository.delete(course);
        courseRepository.deleteById(id);
        Log.info("==받음" + id);
        return "redirect:/courses";
    }

    // 수정
    @GetMapping("/courses/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id).get();
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "/courses/update-course";
    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(
            @PathVariable("id") Long id
            ,@RequestParam("studentId") Long studentId
            ,@RequestParam("lessonId") Long lessonId
    ) {
        Course course = courseRepository.findById(id).get();
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        course.setStudent(student);
        course.setLesson(lesson);
        System.out.println("=="+id);
        System.out.println("=="+studentId);
        System.out.println("=="+lessonId);
        courseRepository.save(course);
        return "redirect:/courses";
    }

    // 보기
    @GetMapping("/courses")
    public String courseList(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses/courseList";
    }

}
