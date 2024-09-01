package het.springapp.service.impl;

import het.springapp.model.Directory;
import het.springapp.service.DirectoryService;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

@Service("directoryService")

public class DirectoryServiceImpl implements DirectoryService {

    public Directory get(String name) {
        return null;
    }

	@Override
	public String [] getDirectories(String parentDirectory) {
		File file = new File(parentDirectory);
		String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

		return directories;
	}


}
