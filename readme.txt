Code details:
============
Made use of spring boot.
MovieReviewService(implementing MovieReviewInterface) is the service component that wraps http://www.omdbapi.com API's
MovieReviewController exposes folowing REST on top of MovieReviewService:
http://localhost:8080/v1/movie
http://localhost:8080/v1/movie/rating

Made use of Maven and fabricio plugin for build/package/creation of image

There are some exception handler classes used for some basic exception handling around 2 API's
Also made use of hateos to provide extra connected links along with desired response.

Commands to run:
==============
1) Download source code and unzip
2) Run following commands on parent dir
   mvn clean install docker:build  --> compiles, builds and creates image
   docker run  -p 8080:8080 movie-review:latest
3) Test below REST URL's via command line wget or browser


Test url's:
1)
http://localhost:8080/v1/movie?apiKey=fb0adedb&movieName=Sholay
response:
{"Title":"Sholay","Year":"1975","Rated":"NOT RATED","Released":"15 Aug 1975","Runtime":"162 min","Genre":"Action, Adventure, Comedy, Drama, Musical, Thriller","Director":"Ramesh Sippy","Writer":"Javed Akhtar, Salim Khan","Actors":"Sanjeev Kumar, Dharmendra, Amitabh Bachchan, Amjad Khan","Plot":"After his family is murdered by a notorious and ruthless bandit, a former police officer enlists the services of two outlaws to capture the bandit.","Language":"Hindi","Country":"India","Awards":"2 wins.","Poster":"https://m.media-amazon.com/images/M/MV5BYTJiOWNkMDktMDE1OC00MTM0LTk0ZDMtZGFjMjE1MmNmYzliXkEyXkFqcGdeQXVyNDgyODgxNjE@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"8.2/10"},{"Source":"Rotten Tomatoes","Value":"83%"}],"Metascore":"N/A","imdbRating":"8.2","imdbVotes":"44,911","imdbID":"tt0073707","Type":"movie","DVD":"13 Jul 1999","BoxOffice":"N/A","Production":"Adlabs Film","Website":"N/A","Response":"True","_links":{"movie-rating":{"href":"http://localhost:8080/v1/movie/rating?apiKey=fb0adedb&movieName=Sholay"}}}

The review response gives link to movie rating to quickly use that for movie rating specific API

2)
http://localhost:8080/v1/movie/rating?apiKey=fb0adedb&movieName=Sholay
response:
{"Source":"Rotten Tomatoes","Value":"83%","_links":{"movie-review":{"href":"http://localhost:8080/v1/movie?apiKey=fb0adedb&movieName=Sholay"}}}

The rating response gives link to movie review to quickly use that for full movie review API