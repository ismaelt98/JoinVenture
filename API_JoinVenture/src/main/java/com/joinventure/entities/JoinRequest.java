package com.joinventure.entities;

public class JoinRequest {
	private Long idUsuario; // Asegúrate de que coincida con el nombre exacto del campo en el JSON

    // Constructor, getters y setters (pueden ser generados automáticamente por IDE o manualmente)
    
    // Constructor
    public JoinRequest() {
    }

    // Constructor con parámetros
    public JoinRequest(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
