package com.mediScreenDoctorsNote.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mediScreenDoctorsNote.model.PatientNote;
import com.mediScreenDoctorsNote.service.PatientNoteService;

@RestController
public class MediScreenDoctorsNoteController {
	private Logger logger = LoggerFactory.getLogger(MediScreenDoctorsNoteController.class);
	@Autowired
	private PatientNoteService patientNoteService;

	@Autowired
	public MediScreenDoctorsNoteController(PatientNoteService patientNoteService) {
		this.patientNoteService = patientNoteService;
	}
	/**
	 * 
	 * @param patId
	 * @param note
	 * @return save doctors note for a patient to DB
	 */
	@PostMapping("/patHistory/add")
	public String addPatientsNotes(@RequestParam int patId, @RequestParam String note) {
		PatientNote patientNote = new PatientNote();
		patientNote.setPatientId(patId);
		patientNote.setNote(note);
		patientNoteService.savePatientNote(patientNote);
		return "Patient Note Saved";
	}
	/**
	 * 
	 * @param id
	 * @param model
	 * @return returns a list of notes by a doctor in a UI format for a patient
	 */
	@RequestMapping("/patHistory/list/{id}")
	public ModelAndView listOfPatHistoryById(@PathVariable("id") Integer id, Model model) {
		List<PatientNote> patientNotes = patientNoteService.getListOfPatientNoteById(id);
		logger.info("patient note is : "+patientNotes);
		model.addAttribute("patientNotes", patientNotes);
		return new ModelAndView("patientNoteHistory");
	}
	/**
	 * 
	 * @param patientNote
	 * @return a form for saving a doctors note for a patient
	 */

	@GetMapping("/patHistory/add")
	public ModelAndView showPatientNoteForm(@ModelAttribute("patientNote") PatientNote patientNote ){
		return new ModelAndView("addPatientNote");
	}
	/**
	 * 
	 * @param model
	 * @return a list of all notes saved in DB
	 */
	@GetMapping("/patHistory/list")
	public ModelAndView listOfAllPatHistory( Model model) {
		model.addAttribute("generalPatientNotes", patientNoteService.getListOfAllPateintNote());
		return new ModelAndView("allPatientHistoryList");
	}
	/**
	 * 
	 * @param patientNote
	 * @param result
	 * @param model
	 * @return saves notes for a patient 
	 */
	@PostMapping("/patHistory/validate")
	public ModelAndView validate(@Valid @ModelAttribute("patientNote") PatientNote patientNote, BindingResult result,
			Model model) {

		if (!result.hasErrors()) {
			patientNoteService.savePatientNote(patientNote);
			return new ModelAndView("redirect:/patHistory/list");
		}

		return new ModelAndView("patHistory/add");
	}
	/**
	 * 
	 * @param id
	 * @param model
	 * @return a patient note to be modified
	 */
	@GetMapping("/patHistory/update/{id}")
	public ModelAndView showUpdateForm(@PathVariable("id") String id, Model model) {
		PatientNote patientNote = patientNoteService.getPatientNoteById(id);
		model.addAttribute("patientNote", patientNote);
		return new ModelAndView("patHistoryupdate");
	}
	/**
	 * 
	 * @param id
	 * @param patientNote
	 * @param result
	 * @param model
	 * @return a patient note list after modification to a note is saved  
	 */
	@PostMapping("/patHistory/update/{id}")
	public ModelAndView updatePatientNote(@PathVariable("id") String id, @Valid PatientNote patientNote,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return new ModelAndView("patHistory/update");
		}
		patientNoteService.savePatientNote(patientNote);
		return new ModelAndView("redirect:/patHistory/list");
	}
	/**
	 * 
	 * @param id
	 * @param model
	 * @return delete a note by id
	 */
	@GetMapping("/patHistory/delete/{id}")
	public ModelAndView deletePatientNote(@PathVariable("id") String id, Model model) {
		patientNoteService.deletePatientNote(id);
		logger.info(id + " successfully deleted");
		return new ModelAndView("redirect:/patHistory/list");
	}
	/**
	 * 
	 * @param id
	 * @return return a list of patient notes
	 */
	@GetMapping("/patHistory/list/{id}")
	public List<PatientNote> getPatientById(@PathVariable("id") Integer id){
		List<PatientNote> patientNotes = patientNoteService.getListOfPatientNoteById(id);
		return patientNotes;
	}
}
