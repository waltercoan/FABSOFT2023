package br.univille.projfabsoft2023.controller;

import java.io.File;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projfabsoft2023.entity.Cliente;
import br.univille.projfabsoft2023.service.CidadeService;
import br.univille.projfabsoft2023.service.ClienteService;
import br.univille.projfabsoft2023.service.SalvarArquivosService;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/cliente")
@PreAuthorize("hasAuthority('APPROLE_user') or hasAuthority('APPROLE_admin')")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private SalvarArquivosService salvarArquivoService;
    
    @GetMapping
    public ModelAndView index(){
        //List<Cliente> listaClientes = clienteService.getAll();
        var listaClientes = clienteService.getAll();
        return new ModelAndView("cliente/index",
                    "listaClientes",listaClientes);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        var cliente = new Cliente();
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("cliente",cliente);
        dados.put("listaCidades",listaCidades);
        return new ModelAndView("cliente/form", 
                                dados);
    }
    @PostMapping(params = "form")
    public ModelAndView save(@Valid Cliente cliente,BindingResult bindingResult,@RequestParam("file") MultipartFile file){

        if(bindingResult.hasErrors()){
            return new ModelAndView("cliente/form","cliente",cliente);
        }
        if(file.getSize() != 0){
            String caminho = salvarArquivoService.save(file);
            cliente.setFoto(caminho);
        }
        
        clienteService.save(cliente);
        return new ModelAndView("redirect:/cliente");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") 
                            Cliente cliente){
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("cliente",cliente);
        dados.put("listaCidades",listaCidades);
        return new ModelAndView("cliente/form",dados);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        clienteService.delete(id);
        return new ModelAndView("redirect:/cliente");
    }
    @GetMapping(value = "/image/{id}")
    public @ResponseBody byte[] getImage(@PathVariable("id") Cliente cliente){
        try{
            File file = new File(cliente.getFoto());
            byte[] bytes = new byte[(int) file.length()];
            try(DataInputStream dis = new DataInputStream(new FileInputStream(file));){
                dis.readFully(bytes);
            }
            return bytes;
        }catch (Exception e){
            return new byte[0];
        }
    }


}
