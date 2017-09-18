import { Component, OnInit } from '@angular/core';
import { MovieService } from "./movie.service";
import { Movie } from "./movie";
import { log } from "util";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MovieService]
})
export class AppComponent implements OnInit {
  constructor(private movieService: MovieService) { }

  releasedMovies: Movie[];
  title = 'FfsBio';

  ngOnInit(): void {
    this.getReleasedMovies();

  }

  getReleasedMovies(): void {
    this.movieService.getReleasedMovies().then(movies => this.releasedMovies = movies);
  }
}
