package br.com.prestaserv.backend.controller;

import br.com.prestaserv.backend.exceptions.ClienteNotFoundException;
import br.com.prestaserv.backend.model.Cliente;
import br.com.prestaserv.backend.model.TotalClientes;
import br.com.prestaserv.backend.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository _repository) {
        repository = _repository;
    }

    @GetMapping
    public List<Cliente> Todos(@RequestParam(required = false) String cidade) {
        if (cidade != null)
            return repository.obterClientesPorCidade(cidade);

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente Um(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @GetMapping("/total")
    public TotalClientes ObterTotalDeClientes() {
        return new TotalClientes(repository.obterTotalDeClientes());
    }

    @PostMapping
    Cliente CriarCliente(@RequestBody Cliente novoCliente) {
        return repository.save(novoCliente);
    }

    @PutMapping("/{id}")
    Cliente AtualizarCliente(@RequestBody Cliente novoCliente, @PathVariable Long id) {

        return repository.findById(id)
                .map(cliente -> {
                    cliente.setCpf(novoCliente.getCpf());
                    cliente.setEndereco(novoCliente.getEndereco());
                    cliente.setNome(novoCliente.getNome());
                    return repository.save(cliente);
                })
                .orElseGet(() -> {
                    novoCliente.setClienteId(id);
                    return repository.save(novoCliente);
                });
    }

    @DeleteMapping("/{id}")
    void ExcluirCliente(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
