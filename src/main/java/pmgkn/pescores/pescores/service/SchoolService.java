package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.SchoolAddBindingDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.SchoolEntity;
import pmgkn.pescores.pescores.repositories.SchoolRepository;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final ModelMapper modelMapper;

    public SchoolService(SchoolRepository schoolRepository,
                         ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
    }

    public void saveSchool(SchoolAddBindingDto schoolAddBindingDto) {

        SchoolEntity schoolToSave=mapToSchoolEntity(schoolAddBindingDto);

        this.schoolRepository.saveAndFlush(schoolToSave);
    }

    private SchoolEntity mapToSchoolEntity(SchoolAddBindingDto schoolAddBindingDto) {

        return this.modelMapper.map(schoolAddBindingDto,SchoolEntity.class);
    }

}
