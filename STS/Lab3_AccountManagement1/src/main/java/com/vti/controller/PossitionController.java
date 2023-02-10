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

import com.vti.dto.PositonDto;
import com.vti.entity.Position;
import com.vti.service.IPossitionService;

@RestController
@RequestMapping(value = "api/v1/positions")
@CrossOrigin("*")
public class PossitionController {
	@Autowired
	private IPossitionService possitionService;

	@GetMapping()
	public ResponseEntity<?> getListPosition() {

		List<Position> list = possitionService.getListPosition();
		// Chuyển đổi dữ liệu lấy được từ ĐB thành dữ liệu DTO
		List<PositonDto> listDtos = new ArrayList<>();
		for (Position position : list) {
			PositonDto positonDto = new PositonDto();
			positonDto.setName(position.getName().toString());
			positonDto.setId(position.getId());
			listDtos.add(positonDto);
		}
		return new ResponseEntity<>(listDtos, HttpStatus.OK);
	}
}
