package controllers;

import objects.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.FolderRepository;

import java.util.List;

@RestController
@RequestMapping("/folders")
@CrossOrigin(origins = "http://localhost:3000")
public class FolderController {

    @Autowired
    private FolderRepository folderRepository;

    @GetMapping()
    public @ResponseBody
    HttpEntity<List<Folder>> getAllFolders() {
        List<Folder> folderList = folderRepository.getAllFolders();

        if(!folderList.isEmpty()) {
            return new ResponseEntity<>(folderList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
