package com.management.gym.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.management.gym.model.Contacts;
import com.management.gym.model.Measurement;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MeasurementDTO;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;


public class StudentBuilder {
	
	public static Student createStudent() {
		return Student.builder()
				.id(2L)
				.name("Sanji Perna Preta")
				.birthDate(LocalDate.now().minusYears(10))
				.contacts(createContacts())
				.measurements(createMeasurement())
				.sexo("Feminino")
				.build();
	}
	
	public static StudentDTO createStudentDTO() {
		return StudentDTO.builder()
				.id(2L)
				.birthDate(LocalDate.now().minusYears(10))
				.contacts(createContacts())
				.name("monkey D. Luffy")
				.status(true).build();
	}
	
	public static StudentMeasurementDTO createStudentMeasurementDTO() {
		return StudentMeasurementDTO.builder()
				.id(2L)
				.name("Shanks O Ruivo")
				.Measurements(createMeasurementDTO()).build();
	}

	protected static List<Contacts> createContacts() {
		List<Contacts> contatos = new ArrayList<>();
		contatos.add(Contacts.builder()
				.id(1L)
				.email("chapeu-palha@onepeace.haki")
				//.socialNetwork("@chapeuDePalha-Oficial")
				.numberPhone("75988806416").build());
		return contatos;
	}
	
	public static List<Measurement> createMeasurement() {
		List<Measurement> measurement = new ArrayList<>();
		measurement.add(Measurement.builder()
				.id(1L)
				.biceps(90.0)
				.breastplate(190)
				.height(2.70)
				.shoulder(180)
				.triceps(100)
				.waist(90)
				.weight(180)
				.build());
		return measurement;
	}
	
	public static List<MeasurementDTO> createMeasurementDTO() {
		List<MeasurementDTO> measurementDTO = new ArrayList<>();
		measurementDTO.add(MeasurementDTO.builder()
				.id(2L)
				.height(200)
				.weight(300)
				.biceps(200)
				.triceps(200)
				.shoulder(100)
				.waist(100).build());
		return measurementDTO;
	}

}
