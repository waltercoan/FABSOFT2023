package br.univille.projfabsoft2023.service;

import java.util.List;

import br.univille.projfabsoft2023.entity.Carro;

public interface CarroService {
    List<Carro> getAll();

    void save(Carro carro);

    void delete(long id);
}
