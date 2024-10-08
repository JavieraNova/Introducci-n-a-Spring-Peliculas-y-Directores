package com.javieranova.controladores;

//Importamos lo que necesite

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class ControladorPeliculas {
	private static HashMap<String, String> listaPeliculas = new HashMap<>();

    // Constructor para llenar el HashMap con las películas
    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }
    
    //Implementar métodos
    //obtenerTodasLasPeliculas()
    @GetMapping("/peliculas")
    public String obtenerTodasLasPeliculas() {
        // El HashMap se convierte en un string con todas las películas
        return listaPeliculas.keySet().stream()
                .collect(Collectors.joining(", "));
    }
    
    //obtenerPeliculaPorNombre()
    @GetMapping("/peliculas/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        if (listaPeliculas.containsKey(nombre)) {
            return nombre + " fue dirigida por " + listaPeliculas.get(nombre);
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }
    
    //obtenerPeliculasPorDirector()
    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        String peliculas = ""; //cadena vacía

        for (String pelicula : listaPeliculas.keySet()) {
            if (listaPeliculas.get(pelicula).equals(nombre)) {
                if (!peliculas.isEmpty()) {
                    peliculas += ", ";//si hay película añademos la ,
                }
                peliculas += pelicula;
            }
        }

        if (peliculas.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista.";
        } else {
            return "Películas dirigidas por " + nombre + ": " + peliculas;
        }
    }
}
