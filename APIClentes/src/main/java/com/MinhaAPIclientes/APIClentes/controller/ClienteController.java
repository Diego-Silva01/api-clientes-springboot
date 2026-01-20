    package com.MinhaAPIclientes.APIClentes.controller;
import com.MinhaAPIclientes.APIClentes.DTO.ErroResponseDTO;
    import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
    import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
    import com.MinhaAPIclientes.APIClentes.DTO.PageClienteResponseDTO;
    import com.MinhaAPIclientes.APIClentes.Model.Cliente;
    import com.MinhaAPIclientes.APIClentes.service.ClienteService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import jakarta.validation.Valid;
    import org.springdoc.core.annotations.ParameterObject;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    // @RestController: su classe do Controller é anotada com @RestController, indicando que ela lida com requisições web, serializando as respostas para JSON.
    @RestController
    @RequestMapping("/clientes")
    public class ClienteController {
        private final ClienteService service;

        public ClienteController(ClienteService service) {
            this.service = service;
        }
    // DTO = Data Transfer Object
    //👉 Serve somente para transportar dados
    //👉 Não representa o banco
    //👉 Não tem @Entity
    //👉 É usado para entrada e/ou saída da API
        @Operation(
                summary = "Cadastrar cliente",
                description = "Criarum novo cliente so sistema com nome, email, telefone e endereço"
        )
        @ApiResponses({
                @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados invalidos"),
                @ApiResponse(responseCode = "409", description = "Email ou telefone ja cadastrado")
        })
        @PostMapping("")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteDTO clienteDTO) {
            ClienteResponseDTO clienteSalvo = service.salvar(clienteDTO);
            return ResponseEntity.status(201).body(clienteSalvo);
        }
        @Operation(
                summary = "Listar clientes paginados",
                description = "Retorna uma lista paginada de clientes"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "Lista de clientes retornada com sucesso",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PageClienteResponseDTO.class)
                        )
                )
        })
        @GetMapping
        public ResponseEntity<Page<ClienteResponseDTO>> buscarTodos(
                @ParameterObject Pageable pageable
        ) {
            return ResponseEntity.ok(service.buscarTodosPaginado(pageable, null));
        }


        @Operation(
        summary = "Procura cliente por ID",
        description = "Deve retorna o cliente pelo ID digitado"
)
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
        content = @Content(
                schema = @Schema(implementation = ErroResponseDTO.class)

        ))

})
        @GetMapping("/{id}")
        public ResponseEntity<ClienteResponseDTO> buscarporID(
                @Parameter(description = "id do cliente", example = "1")
                @PathVariable("id") Long id) {

            ClienteResponseDTO cliente = service.buscarPorID(id);
            return ResponseEntity.ok(cliente);


        }
        @Operation(summary = "Atualizar cliente")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "Cliente atualizado com sucesso",
                        content = @Content(
                                schema = @Schema(implementation = ClienteResponseDTO.class),
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                "id": 1,
                                                "nome": "Diego Silva,
                                                "email": "diego@gmail.com",
                                                "telefone": "123456789,
                                                "endereco": "Iuiu-Bahia"
                                                }
                                                """
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Erro de validação",
                        content = @Content(
                                schema = @Schema(implementation = ErroResponseDTO.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Cliente não encontrado",
                        content = @Content(
                                schema = @Schema(implementation = ErroResponseDTO.class),examples = @ExampleObject(
                                value = """
                                                {
                                                "id": 1,
                                                "nome": "Diego Silva,
                                                "email": "diego@gmail.com",
                                                "telefone": "123456789,
                                                "endereco": "Iuiu-Bahia"
                                                }
                                                """
                        )

                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "E-mail ou telefone já cadastrado",
                        content = @Content(
                                schema = @Schema(implementation = ErroResponseDTO.class),
                                examples = @ExampleObject(  value = """
            {
              "status": 404,
              "message": "Cliente não encontrado com id: 99",
              "path": "/clientes/99",
              "timestamp": "2026-01-20T10:30:00"
            }
            """)))

        })
        @PutMapping("/{id}")
        public ResponseEntity<ClienteResponseDTO> atualizarCliente(
                @PathVariable Long id,
                @RequestBody @Valid ClienteDTO dto
        ) {
            return ResponseEntity.ok(service.atualizarCliente(id, dto));
        }

        @Operation(summary = "Deletar cliente")
        @ApiResponses({
                @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
                @ApiResponse(
                        responseCode = "404",
                        description = "Cliente não encontrado",
                        content = @Content(
                                schema = @Schema(implementation = ErroResponseDTO.class)
                        )
                )
        })
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletar(@PathVariable Long id) {
            service.deletarUsuario(id);
        }


        @GetMapping("/buscar")
        public ResponseEntity<Page<ClienteResponseDTO>> buscarPorNome(@RequestParam @Valid String nome,
                                                           Pageable pageable){
            return ResponseEntity.ok(service.buscaPorNome(nome, pageable));
        }



    }




