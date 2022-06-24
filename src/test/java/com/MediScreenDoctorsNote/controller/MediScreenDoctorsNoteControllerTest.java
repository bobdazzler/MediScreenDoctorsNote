package com.MediScreenDoctorsNote.controller;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mediScreenDoctorsNote.controller.MediScreenDoctorsNoteController;
import com.mediScreenDoctorsNote.model.PatientNote;
import com.mediScreenDoctorsNote.service.PatientNoteService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MediScreenDoctorsNoteControllerTest {
	 @Mock
	    PatientNoteService patientNoteService;

	    @InjectMocks
	    MediScreenDoctorsNoteController mediScreenDoctorsController;

	    @Mock
	    Model model;

	    @Mock
	    BindingResult bindingResult;
	@Test
	public void testAddPatientsNotes() {
	        String s = mediScreenDoctorsController.addPatientsNotes(1,"pateint doing great");
	        Assert.assertEquals("Patient Note Saved",s);
	}

	@Test
	public void testListOfPatHistoryById() {
		 PatientNote patientNote = new PatientNote();
	        patientNote.setPatientId(1);
	        patientNote.setId("1234567890");
	        patientNote.setNote("Note for a patient who is doing well");
	        ModelAndView modelAndView = mediScreenDoctorsController.validate(patientNote,bindingResult,model);
	        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
	}

	@Test
	public void testShowPatientNoteForm() {
		ModelAndView modelAndView = mediScreenDoctorsController.showPatientNoteForm();
		Assert.assertEquals("addPatientNote", modelAndView.getViewName());
	}

	@Test
	public void testListOfAllPatHistory() {
		 ModelAndView modelAndView = mediScreenDoctorsController.listOfAllPatHistory(model);
	        Assert.assertEquals("allPatientHistoryList", modelAndView.getViewName());
	}

	@Test
	public void testValidate() {
		PatientNote patientNote = new PatientNote();
        patientNote.setPatientId(1);
        patientNote.setId("ghdke534");
        patientNote.setNote("Note");
        ModelAndView modelAndView = mediScreenDoctorsController.validate(patientNote,bindingResult,model);
        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
	}

	@Test
	public void testShowUpdateForm() {
		 PatientNote patientNote = new PatientNote();
	        patientNote.setPatientId(1);
	        patientNote.setNote("Test Note");
	        when(patientNoteService.getPatientNoteById(Mockito.anyString())).thenReturn(patientNote);
	        ModelAndView modelAndView = mediScreenDoctorsController.showUpdateForm("1234567890",model);
	        Assert.assertEquals("patHistoryupdate", modelAndView.getViewName());
	}

	@Test
	public void testUpdatePatientNote() {
		 PatientNote patientNote = new PatientNote();
	        patientNote.setPatientId(1);
	        patientNote.setId("retubdhbyhbd8");
	        patientNote.setNote("Test Note");
	        ModelAndView modelAndView = mediScreenDoctorsController.updatePatientNote("retubdhbyhbd8", patientNote, bindingResult, model);
	        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
	}

	@Test
	public void testDeletePatient() {
		 PatientNote patientNote = new PatientNote();
	        patientNote.setPatientId(1);
	        patientNote.setId("retubdhbyhbd8");
	        patientNote.setNote("Test Note");
		doNothing().when(patientNoteService).deletePatientNote(patientNote.getId());
		ModelAndView modelAndView = mediScreenDoctorsController.deletePatientNote(patientNote.getId(),  model);
		verify(patientNoteService,times(1)).deletePatientNote(patientNote.getId());
		Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
	}

}
