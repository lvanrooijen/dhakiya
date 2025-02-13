package com.lvr.Dhakiya_backend.entities.quizcollection;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCollectionRepository extends JpaRepository<QuizCollection, Long> {
  public List<QuizCollection> findAllByEnvironment(Environment environment);
}
