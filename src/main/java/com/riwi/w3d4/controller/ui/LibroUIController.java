package com.riwi.w3d4.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.riwi.w3d4.model.Libro;
import com.riwi.w3d4.service.LibroService;

@Controller
@RequestMapping("/admin/libros")
public class LibroUIController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodos());
        model.addAttribute("tituloPantalla", "Catálogo de Libros");
        return "libros/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("tituloPantalla", "Registrar Nuevo Libro");
        return "libros/formulario";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") Libro libro) {
        libroService.guardar(libro);
        return "redirect:/admin/libros";
    }
}
