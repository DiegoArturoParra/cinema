package com.edu.cundi.cinema.DTOs;

import io.swagger.annotations.ApiModelProperty;

public class RespuestaDTO {
    @ApiModelProperty(notes = "Obtiene un objeto", required = true)
    private Object data;
    @ApiModelProperty(notes = "Obtiene un mensaje", required = true)
    private String mensaje;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
