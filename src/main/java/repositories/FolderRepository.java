package repositories;

import context.MySQLFolderContext;
import objects.FolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FolderRepository {

    @Autowired
    private MySQLFolderContext folderContext;

    public List<FolderDTO> getAllFolders() {
        return folderContext.getAllFolders();
    }
}
