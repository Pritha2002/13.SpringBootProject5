package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IMoviesDAO;
import com.example.demo.entity.Movies;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMoviesDAO dao;
	
	@Override
	public String add(Movies movies) throws Exception {
		return dao.addMovies(movies);
	}

	@Override
	public String delete(String movieName) throws Exception {
		return dao.deleteMovies(movieName);
	}

	@Override
	public String update(String movieName, double budget) throws Exception {
		return dao.updateMovies(movieName, budget);
	}

	@Override
	public List<Movies> select(String actorName, String actressName) throws Exception {
		return dao.selectMovies(actorName, actressName);
	}

}
