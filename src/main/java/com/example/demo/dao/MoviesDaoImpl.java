package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.ResultQuerySpec;
import org.springframework.jdbc.core.simple.JdbcClient.StatementSpec;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Movies;

@Repository
public class MoviesDaoImpl implements IMoviesDAO {
	
	@Autowired
	JdbcClient jdbcClient;

	@Override
	public String addMovies(Movies movies) throws Exception {
		String query="INSERT INTO Movies VALUES(? , ? , ? , ?)";
		StatementSpec statementSpec=jdbcClient.sql(query);
		statementSpec.param(1, movies.getMovieName());
		statementSpec.param(2, movies.getActorName());
		statementSpec.param(3, movies.getActressName());
		statementSpec.param(4, movies.getBudget());
		
		if(statementSpec.update()==0) {
			return "Failed to Add";
		}
		
		return "Movie Added Successfully";
	}

	@Override
	public String deleteMovies(String movieName) throws Exception {
		String query="DELETE FROM Movies where movieName=?";
		StatementSpec statementSpec=jdbcClient.sql(query);
		statementSpec.param(1,movieName);
		
		if(statementSpec.update()==0) {
			return "Failed to Delete";
		}
		return "Movie Deleted Successfully";
	}

	@Override
	public String updateMovies(String movieName, double budget) throws Exception {
		String query="UPDATE Movies SET budget=? WHERE movieName=?";
		StatementSpec statementSpec=jdbcClient.sql(query);
		statementSpec.param(1, budget);
		statementSpec.param(2, movieName);
		
		if(statementSpec.update()==0) {
			return "Failed to Update";
		}
		return "Movie Updated Successfully";
	}

	@Override
	public List<Movies> selectMovies(String actorName, String actressName) throws Exception {
		String query="SELECT * FROM Movies WHERE actorName=? and actressName=?";
		StatementSpec statementSpec=jdbcClient.sql(query);
		statementSpec.param(1, actorName);
		statementSpec.param(2, actressName);
		
		ResultQuerySpec rs = statementSpec.query();
		List<Map<String, Object>> listOfRows = rs.listOfRows();
		
		List<Movies> movie=new ArrayList<>();
		
		for(Map<String,Object> map : listOfRows) {
			
			Movies m=new Movies();
			m.setMovieName((String)map.get("movieName"));
			m.setActorName((String)map.get("actorName"));
			m.setActressName((String)map.get("actressName"));
			
			Object movieObj=map.get("budget");
			if(movieObj instanceof BigDecimal) {
				m.setBudget(((BigDecimal)movieObj).doubleValue());
			}
			else {
				m.setBudget(Double.valueOf((float) movieObj));
			}
			
			movie.add(m);
		}
		return movie;
	}

}
