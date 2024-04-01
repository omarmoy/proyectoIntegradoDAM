package com.dam2.proyectocliente.controlador

import androidx.lifecycle.ViewModel
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState


    fun actividadesDestacadas(): ArrayList<Actividad> {
        val listaFiltrada = _uiState.value.actividades.filter { it.destacado }
        return ArrayList(listaFiltrada)
    }
    fun actividadesRecientes(): ArrayList<Actividad> {
        val ordenadas = _uiState.value.actividades.sortedByDescending { it.fecha.localDateTime }
        val recientes = ArrayList<Actividad>()

        for (i in 0 until minOf(ordenadas.size, 5)) {
            recientes.add(ordenadas[i])
        }

        return recientes
    }

    fun selectActividad(a: Actividad) {
        _uiState.update { e -> e.copy(actividadSeleccionada = a) }
    }

    fun selectAnuncio(a: Anuncio) {
        _uiState.update { e -> e.copy(anuncioSeleccionado = a) }
    }


    /**
     * MENSAJES
     */
    fun selectContacto(c: Contacto) {
        _uiState.update { e -> e.copy(contactoSeleccionado = c) }
        if (c.mensajeNuevo) {
            marcarLeido()
        }
    }

    private fun marcarLeido() {
        _uiState.value.usuario.marcarMensajeLeido(_uiState.value.contactoSeleccionado)
    }

    fun setMensaje(mensaje: String) {
        _uiState.update { e -> e.copy(mensajeEnviar = mensaje) }
    }

    fun enviarMensaje() {

        val mensajeNuevo = Mensaje(
            _uiState.value.usuario.id, Fecha.ahora(), _uiState.value.mensajeEnviar, true
        )
        _uiState.value.usuario.addMensaje(_uiState.value.contactoSeleccionado, mensajeNuevo)
        _uiState.update { e -> e.copy(mensajeEnviar = "") }
        //TODO("enviar mensaje")

    }

    fun filtrarMensajesNoleidos() {
        if (_uiState.value.usuario.tieneMensajesSinLeer()) {
            _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = true) }
        }
    }

    fun quitarFiltroMensajesNoLeidos() {
        _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = false) }
    }


    /**
    PANEL NAVEGACIÓN
     */

    fun mostrarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = true) }
    }

    fun ocultarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = false) }
    }

    fun cambiarBotonNav(botonPulsado: Int) {
        val nuevoEstadoBotones = arrayListOf<Boolean>()
        for (i in 0 until _uiState.value.botoneraNav.size) {
            if (i == botonPulsado)
                nuevoEstadoBotones.add(true)
            else
                nuevoEstadoBotones.add(false)
        }
        _uiState.update { e -> e.copy(botoneraNav = nuevoEstadoBotones) }
    }

    /**
    FAVORITOS
     */
    fun addFavorito(actividad: Actividad) {
        if (!_uiState.value.usuario.actividadesFav.contains(actividad))
            _uiState.value.usuario.addActividadFav(actividad)
    }

    fun eliminarFavorito(actividad: Actividad) {
        _uiState.value.usuario.actividadesFav.remove(actividad)
    }


}