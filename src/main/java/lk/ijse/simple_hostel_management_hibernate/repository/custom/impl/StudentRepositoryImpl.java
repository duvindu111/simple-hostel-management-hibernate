package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private Session session;

    public StudentRepositoryImpl(){}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(Student entity) {
        session.persist(entity);
    }

    @Override
    public Student get(String s) {
        Student student =session.get(Student.class,s);
        return student;
    }

    @Override
    public void update(Student entity) {
        session.merge(entity);
    }

    @Override
    public void delete(Student student) {
        session.remove(student);
    }

    @Override
    public List<Student> getDetailsToTableView() {
        String hql = "SELECT C FROM Student AS C";
        Query query = session.createQuery(hql);
        List<Student> studentList = query.list();
        return studentList;
    }



}
