package br.univille.projfabsoft2023.service;

import org.springframework.web.multipart.MultipartFile;

public interface SalvarArquivosService {
    String save(MultipartFile file);
}
