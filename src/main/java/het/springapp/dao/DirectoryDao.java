package het.springapp.dao;

import het.springapp.model.Directory;

import java.util.List;

public interface DirectoryDao {
    public Directory get(Integer directoryId);

    public void create(Directory directory);

    public void update(Directory directory);

    public void delete(Integer directoryId);

    public List<Directory> getDirectories();
}
