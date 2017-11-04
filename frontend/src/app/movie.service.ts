import { Injectable } from '@angular/core';
import { Movie } from './movie'
import { MOVIES } from './movie-mock';
import { Headers, Http, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { log } from "util";
import {environment} from "../environments/environment";

@Injectable()
export class MovieService {
  private moviesUrl = environment.movieUrl;

  constructor(private http: Http) { }

  getReleasedMovies(): Promise<Movie[]> {
    log("kommer jag hit");
    return this.http.get(this.moviesUrl)
             .toPromise()
             .then(response => response.json())
             .catch(this.handleError);
  }

  getUpcomingMovies(): Promise<Movie[]> {
    return Promise.resolve(MOVIES);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
