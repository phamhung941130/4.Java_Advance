package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.PossitonDto;
import com.vti.entity.Position;
import com.vti.service.IPossitionService;

@RestController
@RequestMapping(value = "api/v1/possitions")
@CrossOrigin("*")
public class PossitionController {

	@Autowired
	private IPossitionService possitionService;

	@GetMapping()
	public ResponseEntity<?> getAllPossitons() {
		List<Position> listPositionsDB = possitionService.getAllPossition();

		List<PossitonDto> listPositionDTO = new ArrayList<>();

		for (Position possition : listPositionsDB) {
			PossitonDto possitionDto = new PossitonDto();
			possitionDto.setId(possition.getId());
			possitionDto.setName(possition.getName().toString());

			listPositionDTO.add(possitionDto);
		}

		return new ResponseEntity<>(listPositionDTO, HttpStatus.OK);
	}

}
