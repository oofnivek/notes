package com.oofnivek.notes.controller;

import com.oofnivek.notes.exception.NoteNotFoundException;
import com.oofnivek.notes.model.Note;
import com.oofnivek.notes.service.NoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

  @Autowired private NoteService noteService;

  @GetMapping("/notes")
  ResponseEntity<Object> manyByName(@RequestParam(value = "name", required = false) String name) {
    List<Note> output = noteService.getNotes(name);
    if (output.stream().count() == 0) {
      throw new NoteNotFoundException(name);
    } else {
      return new ResponseEntity<>(output, HttpStatus.OK);
    }
  }

  @PutMapping("/notes")
  Note upsertNote(
      @RequestBody Note note, @RequestParam(value = "name", required = true) String name) {
    return noteService.upsertNote(name, note);
  }

  @DeleteMapping("/notes")
  ResponseEntity<Object> deleteNoteByName(
      @RequestParam(value = "name", required = true) String name) {
    noteService.deleteNote(name);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  //    public NoteController(NoteRepository repository) {
  //        this.repository = repository;
  //    }

  //    @GetMapping("/notes/{id}")
  //    Note oneById(@PathVariable Long id) {
  //        return repository.findById(id)
  //                .orElseThrow(() -> new NoteNotFoundException(id));
  //    }

  //    @PostMapping("/notes")
  //    Note newEmployee(@RequestBody Note newNote) {
  //        return repository.save(newNote);
  //    }

  //    @PutMapping("/notes/{id}")
  //    Note replaceNote(@RequestBody Note newNote, @PathVariable Long id) {
  //        return repository.findById(id)
  //                .map(note -> {
  //                    note.setName(newNote.getName());
  //                    note.setDesc(newNote.getDesc());
  //                    return repository.save(note);
  //                })
  //                .orElseGet(() -> {
  //                    newNote.setId(id);
  //                    return repository.save(newNote);
  //                });
  //    }

  //    @DeleteMapping("/notes/{id}")
  //    void deleteNoteById(@PathVariable Long id) {
  //        repository.deleteById(id);
  //    }
}
