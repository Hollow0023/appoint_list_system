package com.example.exception;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleAllExceptions(Exception ex, Model model) {
	    model.addAttribute("errorMessage", "エラーが発生しました");

	    // スタックトレースをリストに変換
	    model.addAttribute("stackTrace", Arrays.asList(ex.getStackTrace()));
	    return "error"; // error.htmlを表示
	}


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "404 Not Found");
        model.addAttribute("stackTrace", ex.getStackTrace());
        return "error"; // error.htmlを表示
    }
}

