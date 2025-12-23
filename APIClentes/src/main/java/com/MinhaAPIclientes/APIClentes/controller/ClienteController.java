package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
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
    public Cliente salvar(@RequestBody ClienteDTO clienteDTO) {

        return service.salvar(clienteDTO);

    }

    @GetMapping("")
    public List<Cliente> buscarTodos() {
        return service.buscarTodos();

    }

    @GetMapping("/{id}")
    public Cliente buscarporID(@PathVariable("id") Long id) {
        Cliente cliente = service.buscarPorID(id);
        return cliente;


    }
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDTO DTO ) {
        Cliente clientedoBanco = service.buscarPorID(id);
        clientedoBanco.setNome(DTO.getNome());
        clientedoBanco.setEmail(DTO.getEmail());
        return service.atualizarCliente(id, clientedoBanco);



    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable("id") Long id){
        service.deletarUsuario(id);
        System.out.println("Deletado");



    }
}




