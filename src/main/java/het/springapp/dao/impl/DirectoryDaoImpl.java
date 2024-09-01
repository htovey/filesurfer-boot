package het.springapp.dao.impl;

import het.springapp.dao.DirectoryDao;
import het.springapp.model.Directory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.util.List;

@Repository("directoryDao")
public class DirectoryDaoImpl implements DirectoryDao {

    @Autowired
    private EntityManager manager;

    private Session getSession() {
        return manager.unwrap(Session.class);
    }

    public Directory get(Integer id) {
        return null;
    }

    public void create(Directory directory) {
        getSession().persist(directory);
    }

    public void update(Directory directory) {
        //not implemented
    }

    public void delete(Integer id) {
        //not implemented
    }

    @Override
    public List<Directory> getDirectories() {
       Session session = getSession();
       Query query = session.getNamedQuery("Directory.getDirectories");
       List <Directory> directoryList = query.list();
       return directoryList;
    }
}
