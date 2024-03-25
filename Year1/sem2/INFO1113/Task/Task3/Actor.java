public class Actor{
    private String name;
    private Movie[] movies;
    public String getName(){
        return name;
    }
    public Movie[] getMovies(){
        return movies;
    }
    public Actor(String name, Movie[] movies){
        this.name=name;
        this.movies=movies;
    }
}