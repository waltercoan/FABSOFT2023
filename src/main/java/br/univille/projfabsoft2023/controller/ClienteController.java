package br.univille.projfabsoft2023.controller;

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
import org.springframework.web.servlet.ModelAndView;

import br.univille.projfabsoft2023.entity.Cliente;
import br.univille.projfabsoft2023.service.CidadeService;
import br.univille.projfabsoft2023.service.ClienteService;

@Controller
@RequestMapping("/cliente")
@PreAuthorize("hasAuthority('APPROLE_user') or hasAuthority('APPROLE_admin')")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CidadeService cidadeService;

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
    public ModelAndView save(@Valid Cliente cliente,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("cliente/form","cliente",cliente);
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



}
