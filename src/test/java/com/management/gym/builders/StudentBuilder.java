package com.management.gym.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.management.gym.model.Contacts;
import com.management.gym.model.Measurement;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MeasurementDTO;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;

import lombok.RequiredArgsConstructor;


public class StudentBuilder {
	
	public static Student createStudent() {
		return Student.builder()
				.id(UUID.fromString("b269ae57-31e2-44ec-bbb2-3b240a697337"))
				.name("Sanji Perna Preta")
				.birthDate(LocalDate.now().minusYears(10))
				.contacts(createContacts())
				.measurements(createMeasurement())
				.sexoEnum(1)
				.build();
	}
	
	public static StudentDTO createStudentDTO() {
		return StudentDTO.builder()
				.id(UUID.fromString("b269ae57-31e2-44ec-bbb2-3b240a697337"))
				.birthDate(LocalDate.now().minusYears(10))
				.contacts(createContacts())
				.name("monkey D. Luffy")
				.status(true).build();
	}
	
	public static StudentMeasurementDTO createStudentMeasurementDTO() {
		return StudentMeasurementDTO.builder()
				.id(UUID.fromString("b269ae57-31e2-44ec-bbb2-3b240a697337"))
				.name("Shanks O Ruivo")
				.Measurements(createMeasurementDTO()).build();
	}

	protected static List<Contacts> createContacts() {
		List<Contacts> contatos = new ArrayList<>();
		contatos.add(Contacts.builder()
				.id(UUID.randomUUID())
				.email("chapeu-palha@onepeace.haki")
				.instagram("@chapeuDePalha-Oficial")
				.numberPhone("75988806416").build());
		return contatos;
	}
	
	protected static List<Measurement> createMeasurement() {
		List<Measurement> measurement = new ArrayList<>();
		measurement.add(Measurement.builder()
				.id(UUID.randomUUID())
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
				.id(UUID.fromString("b269ae57-31e2-44ec-bbb2-3b240a697337"))
				.height(200)
				.weight(300)
				.biceps(200)
				.triceps(200)
				.shoulder(100)
				.waist(100).build());
		return measurementDTO;
	}

}
