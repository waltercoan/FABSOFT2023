package br.univille.projfabsoft2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projfabsoft2023.entity.Cliente;

@Repository
public interface ClienteRepository 
    extends JpaRepository<Cliente,Long>{
    
}
