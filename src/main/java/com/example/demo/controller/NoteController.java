package com.example.demo.controller;

import com.example.demo.domain.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoteController {

    private NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String displayNotes(Model model) {
        List<Note> foundNotes = service.findAll();

        if (foundNotes.size() == 0) {
            model.addAttribute("notes", null);
            return "index";
        }
        model.addAttribute("notes", foundNotes);
        return "index";
    }

    @GetMapping("/add")
    public String displayForm(Model model) {
        model.addAttribute("note", new Note());
        return "form";
    }

    @PostMapping("/add")
    public String addNote(@Validated Note note, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        service.save(note);
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String displayNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", service.findById(id));
        return "show";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("note", service.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editNote(@Validated Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("note", note);
            return "edit";
        }
        service.update(note);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
