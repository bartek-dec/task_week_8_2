package com.example.demo.service;

import com.example.demo.domain.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();

    Note findById(Long id);

    void save(Note note);

    void update(Note note);

    void delete(Long id);
}
