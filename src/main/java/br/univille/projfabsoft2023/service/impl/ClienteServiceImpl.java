package br.univille.projfabsoft2023.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.univille.projfabsoft2023.entity.Cliente;
import br.univille.projfabsoft2023.repository.ClienteRepository;
import br.univille.projfabsoft2023.service.ClienteService;

public class ClienteServiceImpl 
        implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> getAll() {
        return repository.findAll();
    }
    /*
     * git add .
     * git commit -m "muita coisa"
     * git push
     */

    
    
}
