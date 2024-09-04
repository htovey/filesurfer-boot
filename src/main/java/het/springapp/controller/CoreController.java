package het.springapp.controller;

import het.springapp.model.Directory;
import het.springapp.model.Note;
import het.springapp.service.DirectoryService;
import het.springapp.service.NoteService;
import het.springapp.service.PersonService;

import java.security.Principal;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CoreController {

	private NoteService noteService;
	private PersonService personService;
	private DirectoryService directoryService;
	private boolean firstLoad = false;

		
	public final Log log = LogFactory.getLog(CoreController.class);
	@Autowired
	public CoreController(NoteService noteService, PersonService personService, DirectoryService directoryService) {
		this.noteService = noteService;
		this.personService = personService;
		this.directoryService = directoryService;
	}
	
	@RequestMapping(value = "/notes", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Map<String, String>>notes(HttpServletRequest request) {
		String userId = getUserId(request);
		log.info("getting notes for "+userId);
		List<Note> noteListFromDb = noteService.findNotesByPerson(userId);
		
		List<Map<String,String>> noteList = new ArrayList<Map<String,String>>();
		
		for (Note note : noteListFromDb) {
			Map<String, String> noteMap = new HashMap<String, String>();
			noteMap.put("id", String.valueOf(note.getNoteId()));
			noteMap.put("directory", note.getDirectory());
			noteMap.put("noteText", note.getNoteText());
			noteMap.put("lastUpdated", note.getSaveDate().toString());
			noteList.add(noteMap);
		}
		
		return noteList;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces="text/html", consumes="application/json")
    public @ResponseBody String note(@RequestBody Note note, HttpServletRequest request) {
		String userId = getUserId(request);
		//new note
		log.info("attempting to create "+note.getDirectory()+" note for: "+userId);
		noteService.create(note, userId);
		return "success";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody Note note, HttpServletRequest request) {
		String userId = getUserId(request);

		log.info("**************attempting to update note for: "+userId);
		noteService.update(note, userId);
		log.info("******************  DONE UPDATING **************");
		return "success";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestBody String [] noteArray) {
		for (String noteId : noteArray) {
			log.info("deleting note "+noteId);
			noteService.delete(Integer.valueOf(noteId));
		}
		return "success";
	}
	
	private String getUserId(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userId = principal.getName();
		return userId;
	}

	@RequestMapping(value = "/directories", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>>directoryList(HttpServletRequest request) {
		log.info("getting directories");
		String [] listFromDb = directoryService.getDirectories("name");

		List<Map<String, String>> directoryList = new ArrayList<Map<String, String>>();

		for (String directory : listFromDb) {
//			Map<String, String> directoryMap = new HashMap<String, String>();
//			directoryMap.put("id", String.valueOf(directory.getId()));
//			directoryMap.put("label", directory.getLabel());
//			directoryMap.put("value", directory.getValue());
//			directoryList.add(directoryMap);
		}

		return directoryList;
	}

	@RequestMapping(value = "/directoryListing", method = RequestMethod.GET)
	public @ResponseBody List<String>directoryListing(@RequestParam String folderName, HttpServletRequest request) {
		log.info("getting directories for "+folderName);

		String [] directoryList = directoryService.getDirectories(folderName);

		return Arrays.asList(directoryList);
	}

}