package repositories;

import context.MySQLFolderContext;
import objects.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FolderRepository {

    @Autowired
    private MySQLFolderContext folderContext;

    public List<Folder> getAllFolders() {
        return folderContext.getAllFolders();
    }
}
