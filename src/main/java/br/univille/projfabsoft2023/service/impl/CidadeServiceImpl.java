package br.univille.projfabsoft2023.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projfabsoft2023.entity.Cidade;
import br.univille.projfabsoft2023.repository.CidadeRepository;
import br.univille.projfabsoft2023.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{

    @Autowired
    private CidadeRepository cidadeRepository;
    @Override
    public List<Cidade> getAll() {
        return cidadeRepository.findAll();
    }
    
}
