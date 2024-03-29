package com.dam2.proyectocliente.controlador

import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import com.dam2.proyectocliente.model.Rol
import com.example.proyectocliente.R
import java.time.LocalDateTime

object DatosPrueba {
    val actividades = arrayListOf<Actividad>(
        Actividad(
            id = 1, titulo = "Titulo Actividad 1", imagen = R.drawable.architecture, contenido = R.string.t1,
            anunciante = "Crash Bandicoot", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1 ),
        Actividad(
            id = 1, titulo = "Titulo Actividad 2", imagen = R.drawable.business, contenido = R.string.t2,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Un Titulo Actividad largo, pero bastante largo, largísimo, tela de largo", imagen = R.drawable.design, contenido = R.string.t4,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1) ,
        Actividad(
            id = 1, titulo = "Titulo Actividad 3", imagen = R.drawable.crafts, contenido = R.string.t3,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 5", imagen = R.drawable.culinary, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" , anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 6", imagen = R.drawable.drawing, contenido = R.string.t1,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" , anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 7", imagen = R.drawable.fashion, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 8", imagen = R.drawable.film, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 9", imagen = R.drawable.gaming, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1 ),
        Actividad(
            id = 1, titulo = "Titulo Actividad 10", imagen = R.drawable.lifestyle, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 11", imagen = R.drawable.music, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 12", imagen = R.drawable.photography, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 13", imagen = R.drawable.painting, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 1, titulo = "Titulo Actividad 14", imagen = R.drawable.tech, contenido = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1)
    )

    val categorias = arrayListOf(
        "todo","categoria1","categoria2","categoria3","categoria4","categoria5","categoria6","categoria7","categoria8",
    )

    val anuncios = arrayListOf<Anuncio>(
        Anuncio(id = 1, titulo  = "Busco peluquero", fotoAnunciante = R.drawable.cortex, contendio = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()),
        Anuncio(id = 2, titulo  = "Busco manzanas", fotoAnunciante = R.drawable.crash, contendio = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora())
    )
/*
    val usuario = Usuario(
        id = 1, nombre = "Crash", apellido1 = "Bandicoot", apellido2 = "PlayStation", mail = "correo@correo.es",
        rol = Rol.OFERTANTE, password = "crash", foto = R.drawable.crash, nif = "44112233M",
        actividadesFav = cargarActividades(),
        actividadesOfertadas = cargarActividadesOfertadas(),
        anuncios = cargarAnuncios(),
        contactos = cargarConversaciones()
        )
*/
    fun cargarActividadesOfertadas(): ArrayList<Actividad>{
        return arrayListOf<Actividad>(
            Actividad(
                id = 1, titulo = "Titulo Actividad 1", imagen = R.drawable.architecture, contenido = R.string.t1,
                anunciante = "Crash Bandicoot", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1 ))
    }

    fun cargarAnuncios(): ArrayList<Anuncio>{
        return arrayListOf(
            Anuncio(id = 2, titulo  = "Busco manzanas", fotoAnunciante = R.drawable.crash, contendio = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()),
            Anuncio(id = 3, titulo  = "Busco muchas manzanas", fotoAnunciante = R.drawable.crash, contendio = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()))
    }
    fun cargarActividades(): ArrayList<Actividad>{
        return arrayListOf(
            Actividad(
                id = 1, titulo = "Titulo Actividad 10", imagen = R.drawable.lifestyle, contenido = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
            Actividad(
                id = 1, titulo = "Titulo Actividad 11", imagen = R.drawable.music, contenido = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
            Actividad(
                id = 1, titulo = "Titulo Actividad 13", imagen = R.drawable.painting, contenido = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = "Sin categorizar",  duracion = 2, precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        )
    }

    fun cargarConversaciones(): ArrayList<Contacto>{
        return arrayListOf(
            Contacto(2, "Coco Bandicoot", R.drawable.coco, arrayListOf(
                Mensaje(2, Fecha(LocalDateTime.of(2023, 5, 15, 14, 30, 0)), "Hola", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 35, 0)), "Hola", true),
                Mensaje(2, Fecha(LocalDateTime.of(2023, 5, 15, 14, 36, 0)), "Que tal", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 37, 0)), "Muy Bien", true)
            )),
            Contacto(3, "Cortex N. Bandicoot", R.drawable.cortex, arrayListOf(
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 30, 0)), "Hola", true),
                Mensaje(3, Fecha(LocalDateTime.of(2023, 5, 15, 14, 35, 0)), "Adios", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 36, 0)), "Que tal", true),
                Mensaje(3, Fecha(LocalDateTime.of(2023, 5, 15, 14, 37, 0)), "Muy Mal", false)),
                true)

        )
    }
}