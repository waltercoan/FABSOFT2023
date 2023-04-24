package br.univille.projfabsoft2023.service;

import java.util.List;
import br.univille.projfabsoft2023.entity.Cliente;

public interface ClienteService {
    List<Cliente> getAll();

    void save(Cliente cliente);


}
