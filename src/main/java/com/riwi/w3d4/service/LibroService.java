package com.riwi.w3d4.service;

import java.util.List;
import com.riwi.w3d4.model.Libro;

public interface LibroService {
    List<Libro> obtenerTodos();
    void guardar(Libro libro);
}
