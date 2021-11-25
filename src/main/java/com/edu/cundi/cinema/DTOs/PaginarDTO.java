package com.edu.cundi.cinema.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginarDTO {

    private int paginaActual;
    private long totalDeRegistros;
    private int tamanioDePagina;
    private int cantidadPaginas;
    private int totalDeRegistrosPorPagina;
    private Object data;

}
