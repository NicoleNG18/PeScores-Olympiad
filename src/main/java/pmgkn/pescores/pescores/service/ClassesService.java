package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.ClassViewDto;
import pmgkn.pescores.pescores.domain.dto.view.StudentViewDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.repositories.ClassRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassesService {

    private final ClassRepository classRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public ClassesService(ClassRepository classRepository,
                          UserService userService,
                          ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Scheduled(cron = "0 0 0 15 9 *")
    public void updateClassesEveryYear() {
        for (ClassEntity classEntity : this.classRepository.findAll()) {

            if (classEntity.getClassNum() == 12) {

                this.deleteClass(classEntity.getId());

            } else if (classEntity.getClassNum() >= 5 && classEntity.getClassNum() <= 9) {

                StringBuilder stringBuilder = new StringBuilder(classEntity.getClassName());
                stringBuilder.deleteCharAt(0);
                stringBuilder.insert(0, String.valueOf(classEntity.getClassNum() + 1));

                classEntity.setClassNum(classEntity.getClassNum() + 1);
                classEntity.setClassName(stringBuilder.toString());

                this.classRepository.saveAndFlush(classEntity);

            } else if (classEntity.getClassNum() == 10 || classEntity.getClassNum() == 11) {

                StringBuilder stringBuilder = new StringBuilder(classEntity.getClassName());
                stringBuilder.deleteCharAt(0);
                stringBuilder.deleteCharAt(0);
                stringBuilder.insert(0, String.valueOf(classEntity.getClassNum() + 1));

                classEntity.setClassNum(classEntity.getClassNum() + 1);
                classEntity.setClassName(stringBuilder.toString());

                this.classRepository.saveAndFlush(classEntity);
            }
        }
    }

    public UUID saveClass(ClassAddBindingDto classAddBindingDto,
                          String name) {

        ClassEntity classToSave = mapToClassEntity(classAddBindingDto, name);

        this.classRepository.saveAndFlush(classToSave);

        return this.classRepository.getReferenceById(classToSave.getId()).getId();
    }

    private ClassEntity mapToClassEntity(ClassAddBindingDto classAddBindingDto,
                                         String name) {
        return this.modelMapper.map(classAddBindingDto, ClassEntity.class)
                .setTeacher(this.userService.getUserByEmail(name));
    }

    @Transactional
    public List<ClassViewDto> getAllClassesByUser(String name) {

        List<ClassEntity> classesByUser = this.userService.getClassesByUser(name);
//        classesByUser.sort((c1, c2) -> c1.getClassName().compareTo(c2.getClassName()));

        classesByUser.sort((b1, b2) -> {
            if (b1.getClassNum() >= b2.getClassNum()) {
                return 1;
            }
            return 0;
        });

        return classesByUser.stream().map(c -> this.modelMapper.map(c, ClassViewDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public ClassViewDto getClassById(UUID id) {
        ClassEntity referenceById = this.classRepository.getReferenceById(id);

        return new ClassViewDto()
                .setClassName(referenceById.getClassName())
                .setClassNum(referenceById.getClassNum())
                .setId(referenceById.getId())
                .setStudents(referenceById.getStudents());
    }

    @Transactional
    public List<StudentViewDto> getStudentsSorted(UUID id) {
        List<StudentEntity> students = this.classRepository.getReferenceById(id).getStudents();
        students.sort((s1, s2) -> s1.getStudentNumber().compareTo(s2.getStudentNumber()));

        return students.stream().map(s -> this.modelMapper.map(s, StudentViewDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public ClassEntity getClassEntityByNameAndTeacher(String className,
                                                      String teacher) {

        UserEntity teacherEntity = this.userService.getUserByEmail(teacher);

        return this.classRepository.findByClassNameAndTeacher(className, teacherEntity);
    }

    public void editClass(UUID id,
                          ClassAddBindingDto classEdit) {

        ClassEntity classEntity = this.classRepository.findClassById(id);

        classEntity
                .setClassName(classEdit.getClassName())
                .setClassNum(classEdit.getClassNum());

        this.classRepository.saveAndFlush(classEntity);
    }

    public void deleteClass(UUID id) {
        this.classRepository.deleteById(id);
    }

    public void addStudent(StudentEntity studentToSave,
                           String className,
                           String teacherName) {

        ClassEntity classEntity = this.getClassEntityByNameAndTeacher(className, teacherName);
        classEntity.addStudent(studentToSave);

        this.classRepository.saveAndFlush(classEntity);
    }

    public void removeStudentFromClass(StudentEntity referenceById) {

        ClassEntity studentClass = referenceById.getStudentClass();

        List<StudentEntity> students = studentClass.getStudents();
        students.remove(referenceById);

        studentClass.setStudents(students);

        this.classRepository.saveAndFlush(studentClass);
    }

}
