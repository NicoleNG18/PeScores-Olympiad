package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.repositories.StudentRepository;

import java.util.UUID;

@Service
public class StudentsService {

    private final ModelMapper modelMapper;

    private final StudentRepository studentRepository;

    private final UserService userService;

    private final ClassesService classesService;
    @Autowired
    public StudentsService(ModelMapper modelMapper,
                           StudentRepository studentRepository,
                           UserService userService,
                           ClassesService classesService) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.classesService = classesService;
    }

    public UUID saveStudent(String name,
                            StudentAddBindingDto studentAddBindingDto
    ) {

        StudentEntity studentToSave = mapToStudentEntity(studentAddBindingDto, name);

        this.studentRepository.saveAndFlush(studentToSave);

        this.classesService.addStudent(studentToSave,studentAddBindingDto.getStudentClass(),name);

        return this.classesService.getClassById(studentToSave.getStudentClass().getId()).getId();
    }

    private StudentEntity mapToStudentEntity(StudentAddBindingDto studentAddBindingDto,
                                           String name) {
        return this.modelMapper.map(studentAddBindingDto, StudentEntity.class)
                .setTeacher(this.userService.getUserByEmail(name))
                .setStudentClass(this.classesService.getClassEntityByNameAndTeacher(studentAddBindingDto.getStudentClass(), name));
    }
}
