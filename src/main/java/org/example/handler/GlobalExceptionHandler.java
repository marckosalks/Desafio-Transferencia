package org.example.handler;

import org.apache.coyote.Response;
import org.example.dto.TransferenciaError;
import org.example.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RegraNegocioException.class)
  public ResponseEntity<TransferenciaError> handleRegraNegocio(RegraNegocioException ex){
      TransferenciaError response =  TransferenciaError.builder()
              .message(ex.getMessage())
              .status(HttpStatus.BAD_REQUEST.value())
              .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<TransferenciaError> handleValidation(MethodArgumentNotValidException ex){
      String mensagem = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .findFirst()
              .map( field -> field.getDefaultMessage())
              .orElse("Erro de validação");

      TransferenciaError response = TransferenciaError.builder()
              .message(mensagem)
              .status(HttpStatus.BAD_REQUEST.value())
              .build();

      return ResponseEntity.badRequest().body(response);
  }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TransferenciaError> handleErrosAleatorios(Exception ex){
       TransferenciaError response =  TransferenciaError.builder()
                .message("Erro inesperado, já estamos resolvendo tente novamente mais tarde")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
