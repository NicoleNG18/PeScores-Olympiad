package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.repositories.ClassRepository;

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

    public void saveClass(ClassAddBindingDto classAddBindingDto,String name){

        ClassEntity classToSave= mapToClassEntity(classAddBindingDto,name);

        this.classRepository.saveAndFlush(classToSave);
    }

    private ClassEntity mapToClassEntity(ClassAddBindingDto classAddBindingDto,String name) {
        return this.modelMapper.map(classAddBindingDto,ClassEntity.class)
                .setTeacher(this.userService.getUserByEmail(name));
    }
}
