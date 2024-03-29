package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public UUID getId() {
        return this.id;
    }

    public BaseEntity setId(UUID id) {
        this.id = id;
        return this;
    }
}

