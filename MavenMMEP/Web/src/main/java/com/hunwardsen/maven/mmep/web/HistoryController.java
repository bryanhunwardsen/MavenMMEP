package com.hunwardsen.maven.mmep.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.hunwardsen.maven.mmep.model.Location;
import com.hunwardsen.maven.mmep.model.Weather;
import com.hunwardsen.maven.mmep.persist.LocationDAO;
import com.hunwardsen.maven.mmep.persist.WeatherDAO;

public class HistoryController implements Controller {

	private LocationDAO locationDAO;
	private WeatherDAO weatherDAO;

	/**
	 * http://localhost:8080/Web/history.x?zip=95035
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zip = request.getParameter("zip");
		Location location = locationDAO.findByZip(zip);
		List<Weather> weathers = weatherDAO.recentForLocation( location );
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put( "location", location );
		model.put( "weathers", weathers );
		
		return new ModelAndView("history", model);
	}

	public WeatherDAO getWeatherDAO() {
		return weatherDAO;
	}

	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}
}