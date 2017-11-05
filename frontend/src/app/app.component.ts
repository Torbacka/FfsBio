import { Component, OnInit } from '@angular/core';
import { MovieService } from "./movie/movie.service";
import { Movie } from "./movie/movie";
import {NavBarComponent} from "./nav-bar/nav-bar.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MovieService]
})
export class AppComponent implements OnInit {
  constructor(private movieService: MovieService) { }

  releasedMovies: Movie[];
  upcomingMovies: Movie[];

  ngOnInit(): void {
    this.getReleasedMovies();
    this.getUpcomingMovies();
  }

  getReleasedMovies(): void {
    this.movieService.getReleasedMovies().then(movies => this.releasedMovies = movies);
  }

  getUpcomingMovies(): void {
    this.movieService.getUpcomingMovies().then(movies => this.upcomingMovies = movies)
  }
}
