package com.jwtexample.userservice.crosscutting.errormanagement;

import java.time.format.DateTimeParseException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorManager {

	private long totalErrors = 0;

	public long getTotalErrors() {
		return totalErrors;
	}

	public HttpStatus getHttpStatusForException(Exception e) {

		logError(e);

		if (e instanceof ObjectNotFoundException) {
			return HttpStatus.NOT_FOUND;
		}

		if (e instanceof IllegalArgumentException || e instanceof DateTimeParseException || e instanceof ConstraintViolationException || e instanceof IncorrectResultSizeDataAccessException) {
			return HttpStatus.BAD_REQUEST;
		}
		
		if (e instanceof ResourceAlreadyExistsException) {
			return HttpStatus.CONFLICT;
		}

		return HttpStatus.INTERNAL_SERVER_ERROR;

	}

	public void logError(Exception e) {
		totalErrors++;
		log.error(e.getMessage(), e);
	}

	public void logError(Exception e, String message) {
		totalErrors++;
		log.error(message, e);
	}
}
