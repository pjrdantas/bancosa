package br.com.meuBanco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by F112861 on 03/01/2018.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parâmetro informado no formato incorreto")
public class ApiFormatoParametroInvalidoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6745634916948373881L;

	public ApiFormatoParametroInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiFormatoParametroInvalidoException(String message) {
        super(message);
    }

    public ApiFormatoParametroInvalidoException() {
        super();
    }
}
