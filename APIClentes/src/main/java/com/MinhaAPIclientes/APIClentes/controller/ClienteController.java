package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Sua classe do Controller é anotada com @RestController, indicando que ela lida com requisições web, serializando as respostas para JSON.
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
    public ResponseEntity<Cliente> salvar(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSalvo = service.salvar(clienteDTO);
        return ResponseEntity.status(201).body(clienteSalvo);

    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> cliente = service.buscarTodos();
        return ResponseEntity.status(200).body(cliente);
        //👉 não retorna só dados, retorna dados + status HTTP correto

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarporID(@PathVariable("id") Long id) {

        Cliente cliente = service.buscarPorID(id);
        return ResponseEntity.ok(cliente);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDTO DTO ) {
        Cliente clienteAtualizado = service.atualizarCliente(id, DTO);
        return ResponseEntity.ok(clienteAtualizado);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}




