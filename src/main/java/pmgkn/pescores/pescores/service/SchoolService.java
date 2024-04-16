package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.binding.SchoolAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.SchoolViewDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.SchoolEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.repositories.ClassRepository;
import pmgkn.pescores.pescores.repositories.SchoolRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;

    private final ClassRepository classRepository;


    public SchoolService(SchoolRepository schoolRepository,
                         ModelMapper modelMapper,
                         ClassRepository classRepository) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
        this.classRepository = classRepository;
    }

    public void saveSchool(SchoolAddBindingDto schoolAddBindingDto) {

        SchoolEntity schoolToSave = mapToSchoolEntity(schoolAddBindingDto);

        this.schoolRepository.saveAndFlush(schoolToSave);
    }

    private SchoolEntity mapToSchoolEntity(SchoolAddBindingDto schoolAddBindingDto) {

        return this.modelMapper.map(schoolAddBindingDto, SchoolEntity.class);
    }

    public List<SchoolViewDto> getAllSchools() {
        return this.schoolRepository.findAll().stream().map(s -> this.modelMapper.map(s, SchoolViewDto.class)).collect(Collectors.toList());
    }

    public String addTeacher(UserEntity userToSave,
                             String school) {

        SchoolEntity schoolEntity = this.getSchoolByName(school);
        schoolEntity.addTeacher(userToSave);

        this.schoolRepository.saveAndFlush(schoolEntity);

        return schoolEntity.getName();
    }

    public SchoolEntity getSchoolByName(String school) {
        return this.schoolRepository.findBySchoolName(school).orElse(null);
    }

    public SchoolEntity getSchoolById(UUID id) {
        return this.schoolRepository.getReferenceById(id);
    }

    public void addClassToSchool(SchoolEntity schoolByTeacher,
                                 ClassEntity classToSave) {
        schoolByTeacher.getClasses().add(classToSave);
        this.schoolRepository.saveAndFlush(schoolByTeacher);
    }

    public void saveSchoolToRepository(SchoolEntity school) {
        this.schoolRepository.saveAndFlush(school);
    }


    public void deleteSchool(UUID id) {

//        SchoolEntity schoolEntity = this.schoolRepository.findById(id).get();
//        for (ClassEntity classEntity :schoolEntity.getClasses()){
//            this.classRepository.deleteById(id);
//        }

        this.schoolRepository.deleteById(id);
    }

    public SchoolEntity findSchoolByName(String s) {
        return this.schoolRepository.findBySchoolName(s).get();
    }
}
