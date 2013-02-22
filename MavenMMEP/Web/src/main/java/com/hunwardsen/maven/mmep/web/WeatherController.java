package com.hunwardsen.maven.mmep.web;

import com.hunwardsen.maven.mmep.model.Weather;
import com.hunwardsen.maven.mmep.persist.WeatherDAO;
import com.hunwardsen.maven.mmep.weather.WeatherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WeatherController implements Controller {

	private WeatherService weatherService;
	private WeatherDAO weatherDAO;

	/**
	 * http://localhost:8080/Web/weather.x?zip=95035
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zip = request.getParameter("zip");
		Weather weather = weatherService.retrieveForecast(zip);
		weatherDAO.save(weather);
		return new ModelAndView("weather", "weather", weather);
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public WeatherDAO getWeatherDAO() {
		return weatherDAO;
	}

	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}
}