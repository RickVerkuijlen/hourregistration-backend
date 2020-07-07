package controllers;

import objects.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.Interfaces.IClientRepository;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    @Autowired
    private IClientRepository clientRepository;

    public ClientController(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/{id}")
    public @ResponseBody HttpEntity<ClientDTO> getClientById(@PathVariable String id) {
        ClientDTO clientDTO = clientRepository.getById(Integer.parseInt(id));

        if(clientDTO != null) {
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
