package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateDto;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.norms.*;
import pmgkn.pescores.pescores.repositories.*;

import java.math.BigDecimal;
import java.math.MathContext;
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

    public UUID saveStudent(String name,
                            StudentAddBindingDto studentAddBindingDto
    ) {

        StudentEntity studentToSave = mapToStudentEntity(studentAddBindingDto, name);

        this.studentRepository.saveAndFlush(studentToSave);

        this.classesService.addStudent(studentToSave, studentAddBindingDto.getStudentClass(), name);

        return this.classesService.getClassById(studentToSave.getStudentClass().getId()).getId();
    }

    private StudentEntity mapToStudentEntity(StudentAddBindingDto studentAddBindingDto,
                                             String name) {
        return this.modelMapper.map(studentAddBindingDto, StudentEntity.class)
                .setTeacher(this.userService.getUserByEmail(name))
                .setStudentClass(this.classesService.getClassEntityByNameAndTeacher(studentAddBindingDto.getStudentClass(), name));
    }

    public UUID editStudent(StudentUpdateDto studentUpdate,
                            UUID id) {

        StudentEntity studentEntity = this.studentRepository.findFirstById(id);

        Integer classNumQuery = studentEntity.getStudentClass().getClassNum();
        String genderQuery = studentEntity.getGender().name();

        setNorms(studentUpdate, studentEntity);

        int countNorms = getCount(studentEntity);

        DenseBallEntity denseBall = this.denseBallRepository.getDenseBallEntityByResult(classNumQuery, genderQuery, studentEntity.getDenseBall());
        JumpEntity jump = this.jumpRepository.getJumpEntityByResult(classNumQuery, genderQuery, studentEntity.getJump());
        TTestEntity tTest = this.tTestRepository.getTTestEntityByResult(classNumQuery, genderQuery, studentEntity.gettTest());
        ThirtyMetersEntity thirtyMeters = this.thirtyMetersRepository.getThirtyMetersEntityByResult(classNumQuery, genderQuery, studentEntity.getThirtyMeters());
        TwoHundredMetersEntity twoHundredMeters = this.twoHundredMetersRepository.getTwoHundredMetersEntityByResult(classNumQuery, genderQuery, studentEntity.getTwoHundredMeters());

        int averageGradeSum = denseBall.getGrade() + jump.getGrade() + tTest.getGrade() + thirtyMeters.getGrade() + twoHundredMeters.getGrade();

        studentEntity.setAverageGrade(BigDecimal.valueOf(averageGradeSum).divide(BigDecimal.valueOf(countNorms)).round(new MathContext(2)));

        this.studentRepository.saveAndFlush(studentEntity);

        return studentEntity.getStudentClass().getId();
    }

    private static void setNorms(StudentUpdateDto studentUpdate,
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
}
