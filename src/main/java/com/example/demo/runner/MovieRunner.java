package com.example.demo.runner;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.controller.MovieController;
import com.example.demo.entity.Movies;

@Component
public class MovieRunner implements CommandLineRunner {
	
	@Autowired
	private MovieController controller;

	@Override
	public void run(String... args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		
		String movieName="";
		String actorName="";
		String actressName="";
		double budget=0.0;
		
		int i=1;
		
		while(i==1) {
			System.out.println("1. INSERT Movie");
			System.out.println("2. DELETE Movie");
			System.out.println("3. UPDATE Movie");
			System.out.println("4. SELECT Movie");
			System.out.println("5. EXIT");
			
			System.out.println("Enter Your Choice :: ");
			int choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				sc.nextLine();
				
				System.out.println("Enter the Movie Name :: ");
				movieName=sc.nextLine();
				
				System.out.println("Enter the Actor Name :: ");
				actorName=sc.nextLine();
				
				System.out.println("Enter the Actress Name :: ");
				actressName=sc.nextLine();
				
				System.out.println("Enter the Budget :: ");
				budget=sc.nextDouble();
				
				Movies movie=new Movies();
				movie.setMovieName(movieName);
				movie.setActorName(actorName);
				movie.setActressName(actressName);
				movie.setBudget(budget);
				
				try {
					System.out.println(controller.addMovieDetails(movie));
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 2:
				sc.nextLine();
				System.out.println("Enter the Movie Name :: ");
				movieName=sc.nextLine();
				
				try {
					System.out.println(controller.deleteMovieDetails(movieName));
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 3:
				sc.nextLine();
				System.out.println("Enter the Movie Name :: ");
				movieName=sc.nextLine();
				
				System.out.println("Enter the New Budget :: ");
				budget=sc.nextDouble();
				
				try {
					System.out.println(controller.updateMovieDetails(movieName, budget));
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 4:
				sc.nextLine();
				System.out.println("Enter the Actor Name :: ");
				actorName=sc.nextLine();
				
				System.out.println("Enter the Actress Name :: ");
				actressName=sc.nextLine();
				
				try {
					List<Movies> selectMovieDetails = controller.selectMovieDetails(actorName, actressName);
					for(Movies m : selectMovieDetails) {
						System.out.println("Movie Name    :: "+ m.getMovieName());
						System.out.println("Actor Name    :: "+ m.getActorName());
						System.out.println("Actress Name  :: "+ m.getActressName());
						System.out.println("Budget        :: "+ m.getBudget());
						System.out.println("=========================================");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Thank You ....");
				i=0;
				break;
			default:
				System.out.println("Wrong Choice .....");
			}
		}
		
		if(sc!=null)
			sc.close();
	}

}
