package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.ClassEditBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.ClassViewDto;
import pmgkn.pescores.pescores.domain.dto.view.StudentViewDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.SchoolEntity;
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

    private final SchoolService schoolService;

    @Autowired
    public ClassesService(ClassRepository classRepository,
                          UserService userService,
                          ModelMapper modelMapper,
                          SchoolService schoolService) {
        this.classRepository = classRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.schoolService = schoolService;
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

    public void saveClass(ClassAddBindingDto classAddBindingDto,
                          String teacherName) {

        SchoolEntity schoolByTeacher = this.userService.getSchoolByTeacher(teacherName);

        ClassEntity classToSave = mapToClassEntity(classAddBindingDto, teacherName, schoolByTeacher);

        this.classRepository.saveAndFlush(classToSave);

        this.userService.setClassToTeacher(classToSave, teacherName);
        this.schoolService.addClassToSchool(schoolByTeacher, classToSave);
    }

    private ClassEntity mapToClassEntity(ClassAddBindingDto classAddBindingDto,
                                         String teacherName,
                                         SchoolEntity school) {
        return this.modelMapper.map(classAddBindingDto, ClassEntity.class)
                .setTeacher(this.userService.getUserByEmail(teacherName))
                .setSchool(school);
    }

    @Transactional
    public List<ClassViewDto> getAllClassesByUser(String userEmail) {

        List<ClassEntity> classesByUser = this.userService.getClassesByUser(userEmail);

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
                .setTeacher(referenceById.getTeacher())
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

    @Transactional
    public ClassEntity getClassEntityByLoggedUserAndName(String loggedUser, String className){

       return this.userService.getUserByEmail(loggedUser).getSchool().getClasses().stream().filter(c->c.getClassName().equals(className)).findFirst().get();
    }

    public void editClass(UUID id,
                          ClassEditBindingDto classEdit) {

        ClassEntity classEntity = this.classRepository.findClassById(id);
        UserEntity currentTeacher = classEntity.getTeacher();
        currentTeacher.getClasses().remove(classEntity);
        this.userService.saveTeacher(currentTeacher);

        UserEntity newTeacher = this.userService.getUserByEmail(classEdit.getTeacher());
        this.userService.setClassToTeacher(classEntity,newTeacher.getEmail());

        classEntity
//                .setClassName(classEdit.getClassName())
//                .setClassNum(classEdit.getClassNum())
                .setTeacher(newTeacher);

        this.classRepository.saveAndFlush(classEntity);
    }

    public void deleteClass(UUID id) {

        ClassEntity classEntity = this.classRepository.findClassById(id);
        UserEntity currentTeacher = classEntity.getTeacher();
        currentTeacher.getClasses().remove(classEntity);
        this.userService.saveTeacher(currentTeacher);

        SchoolEntity school = classEntity.getSchool();
        school.getClasses().remove(classEntity);
        this.schoolService.saveSchoolToRepository(school);

        this.classRepository.deleteById(id);
    }

    public void addStudent(StudentEntity studentToSave,
                           String className,
                           String loggedUser) {

        ClassEntity classEntity = this.getClassEntityByLoggedUserAndName(loggedUser,className);
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

    @Transactional
    public List<ClassViewDto> getAllClassesInSchool(String userEmail) {

        List<ClassEntity> classesInSchool = this.userService.getSchoolClassesByAdmin(userEmail);

        classesInSchool.sort((b1, b2) -> {
            if (b1.getClassNum() >= b2.getClassNum()) {
                return 1;
            }
            return 0;
        });

        return classesInSchool.stream().map(c -> this.modelMapper.map(c, ClassViewDto.class)).collect(Collectors.toList());
    }

    public boolean checkIfClassNameRepeats(String name,
                                           String className) {
        for (ClassEntity classEntity : this.userService.getUserByEmail(name).getSchool().getClasses()) {
            if(classEntity.getClassName().equals(className)){
                return true;
            }
        }

        return false;
    }

}
