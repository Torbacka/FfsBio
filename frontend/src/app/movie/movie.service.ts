import {Injectable} from '@angular/core';
import {Movie} from './movie'
import {Http} from '@angular/http';

import 'rxjs/add/operator/toPromise';
import {environment} from "../../environments/environment";

@Injectable()
export class MovieService {
  private releasedUrl = environment.releasedUrl;
  private upcomingUrl = environment.upcomingUrl;

  constructor(private http: Http) {
  }

  getReleasedMovies(): Promise<Movie[]> {
    return this.http.get(this.releasedUrl)
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  getUpcomingMovies(): Promise<Movie[]> {
    return this.http.get(this.upcomingUrl)
      .toPromise()
      .then(reponse => reponse.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
