package com.dragon.study.spring.boot.mvc.service.impl;

import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;
import com.dragon.study.spring.boot.mvc.service.IClassroomService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dragon on 16/7/10.
 */
@Service
public class ClassroomServiceImpl implements IClassroomService {


  @Override
  public String getTeacherName(int grade, int classroomNumber) {
    return createClassroom(grade, classroomNumber).getTeacher();
  }

  @Override
  public List<PersonModel> getTopTenStudent(int grade, int classroomNumber) {
    return createClassroom(grade, classroomNumber).getPersonModels().stream()
        .sorted((p1, p2) -> p2.getScore() - p1.getScore()).limit(10).collect(Collectors.toList());
  }

  @Override
  public List<PersonModel> getBiggerThan60(int grade, int classroomNumber) {
    return createClassroom(grade, classroomNumber).getPersonModels().stream()
        .filter(p -> p.getScore() > 60).collect(Collectors.toList());
  }

  @Override
  public ClassroomModel getClassroom(int grade, int classroom) {
    return createClassroom(grade, classroom);
  }

  private ClassroomModel createClassroom(int grade, int classroomNumber) {
    ClassroomModel classroom = new ClassroomModel();
    classroom.setGrade(grade);
    classroom.setClassroomNumber(classroomNumber);
    classroom.setTeacher(UUID.randomUUID().toString());

    List<PersonModel> personModelList = IntStream.rangeClosed(1, 40).boxed().map(this::createPersonModel)
        .collect(Collectors.toList());
    classroom.setPersonModels(personModelList);
    return classroom;
  }

  private PersonModel createPersonModel(int num) {
    PersonModel person = new PersonModel();
    Random random = new Random();

    person.setNumber(num);
    person.setAge(10 + random.nextInt(10));
    person.setMale(random.nextBoolean());
    person.setScore(random.nextInt(100));
    person.setName(UUID.randomUUID().toString());
    return person;
  }
}
