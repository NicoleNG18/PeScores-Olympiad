package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateDto;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.repositories.DenseBallRepository;
import pmgkn.pescores.pescores.repositories.StudentRepository;

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

    @Autowired
    public StudentsService(ModelMapper modelMapper,
                           StudentRepository studentRepository,
                           UserService userService,
                           ClassesService classesService,
                           DenseBallRepository denseBallRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.classesService = classesService;
        this.denseBallRepository = denseBallRepository;
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

        int count=0;

        if (studentUpdate.getDenseBall() != null) {
            studentEntity.setDenseBall(studentUpdate.getDenseBall());
            count++;
        }

        if (studentUpdate.getJump() != null) {
            studentEntity.setJump(studentUpdate.getJump());
            count++;
        }

        if (studentUpdate.gettTest() != null) {
            studentEntity.settTest(studentUpdate.gettTest());
            count++;
        }

        if (studentUpdate.getThirtyMeters() != null) {
            studentEntity.setThirtyMeters(studentUpdate.getThirtyMeters());
            count++;
        }

        if (studentUpdate.getTwoHundredMeters() != null) {
            studentEntity.setTwoHundredMeters(studentUpdate.getTwoHundredMeters());
            count++;
        }

        Integer classNumQuery=studentEntity.getStudentClass().getClassNum();
        String genderQuery=  studentEntity.getGender().name();
        BigDecimal resultQuery=studentUpdate.getDenseBall();

        DenseBallEntity denseBall = this.denseBallRepository.getDenseBallEntityByGrade(classNumQuery,genderQuery,resultQuery);


        Integer denseBallGrade =denseBall.getGrade();

                BigDecimal averageGrade = (studentEntity.gettTest()
                .add(studentEntity.getJump())
                .add(studentEntity.getThirtyMeters())
                .add(studentEntity.getTwoHundredMeters())
                .add(BigDecimal.valueOf(denseBallGrade)).divide(BigDecimal.valueOf(count)).round(new MathContext(2)));

                studentEntity.setAverageGrade(averageGrade);

                this.studentRepository.saveAndFlush(studentEntity);

       return studentEntity.getStudentClass().getId();
    }
}
