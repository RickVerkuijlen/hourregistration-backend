package controllers;

import objects.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.Interfaces.IClientRepository;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    @Autowired
    private IClientRepository clientRepository;

    public ClientController(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("")
    public @ResponseBody HttpEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.getAllClients();

        if(!clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody HttpEntity<Client> getClientById(@PathVariable String id) {
        Client client = clientRepository.getById(Integer.parseInt(id));

        if(client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> updateClient(@RequestBody Client client) {
        Boolean clientUpdateSuccess = clientRepository.updateClient(client);

        if (clientUpdateSuccess) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Integer> createClient(@RequestBody Client client) {
        int clientUpdateSuccess = clientRepository.createClient(client);

        if (clientUpdateSuccess != 0) {
            return new ResponseEntity<>(clientUpdateSuccess, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
