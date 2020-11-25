package com.example.demo.service;

import com.example.demo.domain.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository repository;

    @Autowired
    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Override
    public Note findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(Note note) {
        repository.save(note);
    }

    @Override
    public void update(Note note) {
        Note foundNote = repository.findById(note.getId()).get();

        foundNote.setTitle(note.getTitle());
        foundNote.setContent(note.getContent());

        repository.save(foundNote);
    }

    @Override
    public void delete(Long id) {
        Note foundNote = repository.findById(id).get();
        repository.delete(foundNote);
    }
}
