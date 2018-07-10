package br.com.meuBanco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by F112861 on 03/01/2018.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro interno no sistema.")
public class ApiBusinessException extends RuntimeException {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8311777100172428637L;

	public ApiBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBusinessException(String message) {
        super(message);
    }

    public ApiBusinessException() {
        super();
    }
}
