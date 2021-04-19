package ru.eugene.example.dao;

import ru.eugene.example.model.Student;

import java.util.List;


public interface StudentDao {

    public List<Student> findAll();

    public void insert(Student student);

    public void delete(int id);

    public void update(int id, Student student);

    public Student findById(int id);

}
