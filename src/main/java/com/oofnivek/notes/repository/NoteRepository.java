package com.oofnivek.notes.repository;

import com.oofnivek.notes.model.Note;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> findByName(String name);

  @Transactional
  @Modifying
  @Query(value = "delete from Note n where n.name = ?1")
  void deleteByName(String name);
}
