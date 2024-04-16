package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateBindingDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.norms.*;
import pmgkn.pescores.pescores.repositories.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
public class StudentsService {

    private final ModelMapper modelMapper;

    private final StudentRepository studentRepository;

    private final UserService userService;

    private final ClassesService classesService;

    private final DenseBallRepository denseBallRepository;

    private final JumpRepository jumpRepository;

    private final TwoHundredMetersRepository twoHundredMetersRepository;

    private final ThirtyMetersRepository thirtyMetersRepository;

    private final TTestRepository tTestRepository;

    @Autowired
    public StudentsService(ModelMapper modelMapper,
                           StudentRepository studentRepository,
                           UserService userService,
                           ClassesService classesService,
                           DenseBallRepository denseBallRepository,
                           JumpRepository jumpRepository,
                           TwoHundredMetersRepository twoHundredMetersRepository,
                           ThirtyMetersRepository thirtyMetersRepository,
                           TTestRepository tTestRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.classesService = classesService;
        this.denseBallRepository = denseBallRepository;
        this.jumpRepository = jumpRepository;
        this.twoHundredMetersRepository = twoHundredMetersRepository;
        this.thirtyMetersRepository = thirtyMetersRepository;
        this.tTestRepository = tTestRepository;
    }

    public UUID saveStudent(String loggedUser,
                            StudentAddBindingDto studentAddBindingDto) {

        StudentEntity studentToSave = mapToStudentEntity(studentAddBindingDto, loggedUser);

        this.studentRepository.saveAndFlush(studentToSave);

        this.classesService.addStudent(studentToSave, studentAddBindingDto.getStudentClass(), loggedUser);

        return this.classesService.getClassById(studentToSave.getStudentClass().getId()).getId();
    }

    private StudentEntity mapToStudentEntity(StudentAddBindingDto studentAddBindingDto,
                                             String loggedUser) {
        return this.modelMapper.map(studentAddBindingDto, StudentEntity.class)
                .setTeacher(this.userService.getUserByEmail(loggedUser))
                .setStudentClass(this.classesService.getClassEntityByLoggedUserAndName(loggedUser,studentAddBindingDto.getStudentClass()));
    }

    public UUID editStudent(StudentUpdateBindingDto studentUpdate,
                            UUID id) {

        StudentEntity studentEntity = this.studentRepository.findFirstById(id);

        Integer classNumQuery = studentEntity.getStudentClass().getClassNum();
        String genderQuery = studentEntity.getGender().name();

        setNorms(studentUpdate, studentEntity);

        int countNorms = getCount(studentEntity);

        int averageGradeSum = getAverageGradeSum(studentEntity, classNumQuery, genderQuery);

        studentEntity.setAverageGrade(BigDecimal.valueOf(averageGradeSum).
                divide(BigDecimal.valueOf(countNorms), 2, RoundingMode.HALF_UP));

        this.studentRepository.saveAndFlush(studentEntity);

        return studentEntity.getStudentClass().getId();
    }

    private int getAverageGradeSum(StudentEntity studentEntity,
                                   Integer classNumQuery,
                                   String genderQuery) {
        int averageGradeSum = 0;

        if (studentEntity.getJump().compareTo(BigDecimal.ZERO) != 0) {
            JumpEntity jump = this.jumpRepository.getJumpEntityByResult(classNumQuery, genderQuery, studentEntity.getJump());
            averageGradeSum += jump.getGrade();
        }
        if (studentEntity.getThirtyMeters().compareTo(BigDecimal.ZERO) != 0) {
            ThirtyMetersEntity thirtyMeters = this.thirtyMetersRepository.getThirtyMetersEntityByResult(classNumQuery, genderQuery, studentEntity.getThirtyMeters());
            averageGradeSum += thirtyMeters.getGrade();
        }
        if (studentEntity.getTwoHundredMeters().compareTo(BigDecimal.ZERO) != 0) {
            TwoHundredMetersEntity twoHundredMeters = this.twoHundredMetersRepository.getTwoHundredMetersEntityByResult(classNumQuery, genderQuery, studentEntity.getTwoHundredMeters());
            averageGradeSum += twoHundredMeters.getGrade();
        }
        if (studentEntity.gettTest().compareTo(BigDecimal.ZERO) != 0) {
            TTestEntity tTest = this.tTestRepository.getTTestEntityByResult(classNumQuery, genderQuery, studentEntity.gettTest());
            averageGradeSum += tTest.getGrade();
        }
        if (studentEntity.getDenseBall().compareTo(BigDecimal.ZERO) != 0) {
            DenseBallEntity denseBall = this.denseBallRepository.getDenseBallEntityByResult(classNumQuery, genderQuery, studentEntity.getDenseBall());
            averageGradeSum += denseBall.getGrade();
        }

        return averageGradeSum;
    }

    private static void setNorms(StudentUpdateBindingDto studentUpdate,
                                 StudentEntity studentEntity) {
        if (studentUpdate.getDenseBall() != null) {
            studentEntity.setDenseBall(studentUpdate.getDenseBall());
        }
        if (studentUpdate.getJump() != null) {
            studentEntity.setJump(studentUpdate.getJump());
        }
        if (studentUpdate.gettTest() != null) {
            studentEntity.settTest(studentUpdate.gettTest());
        }
        if (studentUpdate.getThirtyMeters() != null) {
            studentEntity.setThirtyMeters(studentUpdate.getThirtyMeters());
        }
        if (studentUpdate.getTwoHundredMeters() != null) {
            studentEntity.setTwoHundredMeters(studentUpdate.getTwoHundredMeters());
        }
    }

    private static int getCount(StudentEntity studentEntity) {
        int count = 0;

        if (studentEntity.getJump().compareTo(BigDecimal.ZERO) != 0) {
            count++;
        }
        if (studentEntity.getThirtyMeters().compareTo(BigDecimal.ZERO) != 0) {
            count++;
        }
        if (studentEntity.getTwoHundredMeters().compareTo(BigDecimal.ZERO) != 0) {
            count++;
        }
        if (studentEntity.gettTest().compareTo(BigDecimal.ZERO) != 0) {
            count++;
        }
        if (studentEntity.getDenseBall().compareTo(BigDecimal.ZERO) != 0) {
            count++;
        }

        return count;
    }

    public boolean checkIfClassNumRepeats(String studentClass,
                                          String loggedUser,
                                          Integer studentNumber) {

        ClassEntity classEntityByNameAndTeacher=this.classesService.getClassEntityByLoggedUserAndName(loggedUser,studentClass);

        List<Integer> collect = classEntityByNameAndTeacher.
                getStudents()
                .stream()
                .map(StudentEntity::getStudentNumber)
                .filter(s -> s.equals(studentNumber))
                .toList();

        return collect.size() != 0;
    }

    @Transactional
    public void deleteStudent(UUID id) {
        StudentEntity referenceById = this.studentRepository.findFirstById(id);

        this.classesService.removeStudentFromClass(referenceById);

        referenceById.setStudentClass(null);
        this.studentRepository.saveAndFlush(referenceById);

        this.studentRepository.deleteById(id);
    }
}
