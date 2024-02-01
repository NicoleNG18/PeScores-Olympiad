package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import java.util.UUID;

@Repository
public interface StudentRepository  extends JpaRepository<StudentEntity, UUID> {
}
