package com.MinhaAPIclientes.APIClentes.DTO;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Estrutura de resposta paginada de clientes")
public class PageClienteResponseDTO {

    @Schema(example = "10")
    public int totalPages;

    @Schema(example = "100")
    public long totalElements;

    @Schema(example = "10")
    public int size;

    @Schema(example = "0")
    public int number;

    @Schema(example = "true")
    public boolean first;

    @Schema(example = "false")
    public boolean last;

    @Schema(description = "Lista de clientes")
    public List<ClienteResponseDTO> content;
}
