package com.oofnivek.notes.service;

import com.oofnivek.notes.model.Note;
import com.oofnivek.notes.repository.NoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
  @Autowired private NoteRepository repository;

  public Note upsertNote(String name, Note note) {
    return repository.findByName(name).stream()
        .findFirst()
        .map(
            n -> {
              n.setName(name);
              n.setDesc(note.getDesc());
              return repository.save(n);
            })
        .orElseGet(
            () -> {
              note.setName(name);
              return repository.save(note);
            });
  }

  public void deleteNote(String name) {
    repository.deleteByName(name);
  }

  public List<Note> getNotes(String name) {
    List<Note> output;
    if (name == null) {
      output = repository.findAll();
    } else {
      output = repository.findByName(name);
    }
    return output;
  }
}
