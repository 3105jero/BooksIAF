package com.riwi.w3d4.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.riwi.w3d4.model.Libro;

@Service
public class LibroServiceImpl implements LibroService {

    private List<Libro> libros = new ArrayList<>(List.of(
            new Libro(1L, "Don Quijote", "Miguel de Cervantes", "978-0001", 1605),
            new Libro(2L, "El Principito", "Antoine de Saint-Exupéry", "978-0002", 1943),
            new Libro(3L, "Cien Años de Soledad", "Gabriel García Márquez", "978-0003", 1967),
            new Libro(4L, "1984", "George Orwell", "978-0004", 1949)));

    @Override
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @Override
    public void guardar(Libro libro) {
        libro.setId((long) (libros.size() + 1));
        libros.add(libro);
    }
}
