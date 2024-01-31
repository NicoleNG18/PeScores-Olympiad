package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.ClassViewDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
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

    public List<ClassViewDto> getAllClassesByUser(String name) {

        List<ClassEntity> classesByUser = this.userService.getClassesByUser(name);

        classesByUser.sort((b1, b2) -> {
            if (b1.getClassNum() >= b2.getClassNum()) {
                return 1;
            }
            return 0;
        });

        classesByUser.sort((c1, c2) -> c1.getClassName().compareTo(c2.getClassName()));

        return classesByUser.stream().map(c -> this.modelMapper.map(c, ClassViewDto.class)).collect(Collectors.toList());
    }

    public ClassViewDto getClassById(UUID id) {
        return this.modelMapper.map(this.classRepository.getReferenceById(id), ClassViewDto.class);
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
}
