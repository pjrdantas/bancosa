package br.com.meuBanco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by F112861 on 03/01/2018.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parâmetro informado incorreto")
public class ApiBadRequestException extends RuntimeException {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3411935885840133474L;

	public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(Throwable cause) {
        super(cause);
    }

    public ApiBadRequestException() {
        super();
    }
}
