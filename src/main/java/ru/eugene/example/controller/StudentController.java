package ru.eugene.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eugene.example.dao.StudentDao;
import ru.eugene.example.model.Student;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    //--------------------Чтение всех студентов--------------------------------------------------
    @GetMapping
    public String findAll(Model model) {
        List<Student> studentList = studentDao.findAll();
        model.addAttribute("studentList", studentList);
        return "student/view";
    }

    //----------------------- Создание студента-------------------------
    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "student/new";
    }

    @PostMapping
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "student/new";

        studentDao.insert(student);
        return "redirect:/student";
    }

    //--------------------Чтение студена по Id--------------------------------------------------
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentDao.findById(id));
        return "student/viewById";
    }

    //--------------------Удаление студенат--------------------------------------------------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentDao.delete(id);
        return "redirect:/student";
    }

    //----------------------- Обновление студента-------------------------
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentDao.findById(id));
        return "student/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "student/edit";

        studentDao.update(id, student);
        return "redirect:/student";
    }
    //-------------------------------------------------------------------


}
