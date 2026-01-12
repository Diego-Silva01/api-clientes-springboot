    package com.MinhaAPIclientes.APIClentes.controller;

    import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
    import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
    import com.MinhaAPIclientes.APIClentes.Model.Cliente;
    import com.MinhaAPIclientes.APIClentes.service.ClienteService;
    import jakarta.validation.Valid;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
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
        @PostMapping("")
        public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteDTO clienteDTO) {
            ClienteResponseDTO clienteSalvo = service.salvar(clienteDTO);
            return ResponseEntity.status(201).body(clienteSalvo);
        }
        @GetMapping
        public ResponseEntity<Page<ClienteResponseDTO>> buscarTodos(Pageable pageable,
                                                                    @RequestParam(required = false) String nome) {
            return ResponseEntity.ok(service.buscarTodosPaginado(pageable, nome));
        }


        @GetMapping("/{id}")
        public ResponseEntity<ClienteResponseDTO> buscarporID(@PathVariable("id") Long id) {

            ClienteResponseDTO cliente = service.buscarPorID(id);
            return ResponseEntity.ok(cliente);


        }
        @PutMapping("/{id}")
        public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable("id") Long id,@Valid @RequestBody ClienteDTO DTO ) {
            ClienteResponseDTO clienteAtualizado = service.atualizarCliente(id, DTO);
            return ResponseEntity.ok(clienteAtualizado);

        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            service.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/buscar")
        public ResponseEntity<Page<ClienteResponseDTO>> buscarPorNome(@RequestParam @Valid String nome,
                                                           Pageable pageable){
            return ResponseEntity.ok(service.buscaPorNome(nome, pageable));
        }



    }




