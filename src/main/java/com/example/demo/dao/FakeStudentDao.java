package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeStudentDao implements StudentDao{
    private static List<Student> Database = new ArrayList<Student>();

    @Override
    public List<Student> selectAllStudents() {
        return Database;
    }

    @Override
    public Optional<Student> selectStudentbyId(UUID id) {
        for(Student s: Database){
            if(s.getId().equals(id)){
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public int deleteStudentById(UUID id) {
        Optional<Student> optionalStudent = selectStudentbyId(id);
        if(optionalStudent.isEmpty()){
            return -1;
        }

        Database.remove(optionalStudent.get());
        return 1;
    }



    @Override
    public int updateStudent(Student student) {
        UUID id = student.getId();

        int indexToUpdate = -1;
        for(int i = 0; i < Database.size(); ++i){
            if(student.getId().equals(id)){
                indexToUpdate = i;
                break;
            }
        }
        if(indexToUpdate < 0){
            return -1;
        }
        Database.set(indexToUpdate, student);
        return -1;
    }

    @Override
    public int insertStudent(Student student) {
        Database.add(new Student(UUID.randomUUID(), student.getName()));
        return 0;
    }
}
