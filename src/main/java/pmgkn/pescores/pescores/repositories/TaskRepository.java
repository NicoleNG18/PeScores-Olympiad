package pmgkn.pescores.pescores.repositories;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;

import java.util.UUID;

@Repository
public interface TaskRepository  extends JpaRepository<TaskEntity, UUID> {

    TaskEntity findByOwnerAndDescription(UserEntity owner,String description);



}
