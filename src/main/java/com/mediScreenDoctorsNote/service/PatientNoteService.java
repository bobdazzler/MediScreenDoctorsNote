package com.mediScreenDoctorsNote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediScreenDoctorsNote.model.PatientNote;
import com.mediScreenDoctorsNote.repository.PatientsNoteRepository;

@Service
public class PatientNoteService {
	@Autowired
	private PatientsNoteRepository patientsNoteRepository;

	/**
	 * this method returns a patient note by a mongoDb id
	 * 
	 * @param id
	 * @return doctors note for a patient
	 */
	public PatientNote getPatientNoteById(String id) {
		Optional<PatientNote> patientNote = patientsNoteRepository.findById(id);
		return patientNote.get();
	}

	/**
	 * saves a doctors note for a patient
	 * 
	 * @param patientNote
	 */
	public void savePatientNote(PatientNote patientNote) {
		patientsNoteRepository.save(patientNote);
	}

	/**
	 * delete a patient note
	 * 
	 * @param id
	 */
	public void deletePatientNote(String id) {
		patientsNoteRepository.deleteById(id);
	}

	/**
	 * 
	 * @return list of patientNote
	 */
	public List<PatientNote> getListOfAllPateintNote() {
		return patientsNoteRepository.findAll();
	}

	/**
	 * all notes of patient by Id
	 * 
	 * @param id
	 * @return PatientNote
	 */
	public List<PatientNote> getListOfPatientNoteById(int id) {
		return patientsNoteRepository.findAllByPatientId(id);
	}
}
