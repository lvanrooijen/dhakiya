package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteCollectionRepository extends JpaRepository<NoteCollection, Long> {
    public Optional<NoteCollection> findByEnvironment(Environment environment);
}
