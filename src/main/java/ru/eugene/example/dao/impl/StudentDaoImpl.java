package ru.eugene.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.eugene.example.dao.StudentDao;
import ru.eugene.example.model.Student;

import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String HQL = "select s from Student s";
        return session.createQuery(HQL, Student.class).getResultList();
    }

    @Transactional
    public void insert(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Student.class, id));
    }

    @Transactional
    public void update(int id, Student updateStudent) {
        Session session = sessionFactory.getCurrentSession();
        Student studentToBeUpdate = session.get(Student.class, id);

        studentToBeUpdate.setFirstname(updateStudent.getFirstname());
        studentToBeUpdate.setLastname(updateStudent.getLastname());
        studentToBeUpdate.setAge(updateStudent.getAge());
        studentToBeUpdate.setEmail(updateStudent.getEmail());

    }

    @Transactional(readOnly = true)
    public Student findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }
}
