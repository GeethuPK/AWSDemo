package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@Data
@Getter
@Setter
public class ErrorResponse {
	
	private int statusCode;
	private String status;
	private String message;
	

}
