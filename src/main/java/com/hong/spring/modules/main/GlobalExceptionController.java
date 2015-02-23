package com.hong.spring.modules.main;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(SQLException ex) {
		ModelAndView mav = new ModelAndView("/error/database_error");
		mav.addObject("exception", ex);
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView mav = new ModelAndView("/error/generic_error");
		mav.addObject("exception", ex);
		return mav;
	}

}
