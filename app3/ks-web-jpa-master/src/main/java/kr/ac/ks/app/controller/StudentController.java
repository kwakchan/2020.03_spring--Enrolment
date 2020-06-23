package kr.ac.ks.app.controller;

import jdk.incubator.jpackage.internal.Log;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 추가
    @GetMapping("/students/new") // studentForm.html에서 '추가'버튼 누르면
    public String showStudentForm(Model model) { // showStudentForm 매서드 생성
        model.addAttribute("studentForm", new StudentForm()); // studentForm에서 받은 객체를 추가한다. model객체에
        return "students/studentForm"; // 값이 추가된 studentForm 객체를 반환한다
    }

    @PostMapping("/students/new") // studentForm.html에서 '추가'버튼 누르면
    public String createStudent(@Valid StudentForm studentForm, BindingResult result) { //  // createStudent 매서드 생성
        if (result.hasErrors()) { //@Valid BindingResult result: 유효성 체크 결과 전송된 데이터가 도메인 클래스에 지정한 검증 규칙에 어긋날 경우 controller으로 돌려 보냄
            return "students/studentForm";
        }

        Student student = new Student(); // student 객체 생성
        student.setName(studentForm.getName()); // studentForm.html에서 받은 이름을 student에 넣음
        student.setEmail(studentForm.getEmail()); // studentForm.html에서 받은 이메일을 student에 넣음
        studentRepository.save(student); // student객체를 레파지토리(DB)에 저장
        return "redirect:/students";
    }
    
    

    // 삭제
    @GetMapping("/students/delete/{id}") // studentList.html에서 '삭제'버튼 누르면
    public String delete(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
    
    // 수정
    @GetMapping("/students/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "students/update-student";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@Valid Student student ) {
        studentRepository.save(student);
        return "redirect:/students";
    }
    
    // 보기
    @GetMapping("/students") 
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students/studentList";
    }
}
