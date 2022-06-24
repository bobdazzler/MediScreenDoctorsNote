package com.mediScreenDoctorsNote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mediScreenDoctorsNote.model.PatientNote;

public interface PatientsNoteRepository extends MongoRepository<PatientNote, String> {
@Query("{ 'id' : ?0 }")
List<PatientNote> findPatientNoteById(int id);
}
