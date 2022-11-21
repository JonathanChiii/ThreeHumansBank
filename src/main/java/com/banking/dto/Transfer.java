package com.banking.dto;

import java.util.Set;

import com.banking.model.SecurityQuestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transfer {

	int fromAccNumber;
	int toAccNumber;
	int amount;
	String reason;
	String by;
}
