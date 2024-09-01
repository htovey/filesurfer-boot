package het.springapp.service;

import het.springapp.model.Directory;

import java.util.List;

public interface DirectoryService {
	public Directory get(String name);

	public String [] getDirectories(String parentDirectory);
}
