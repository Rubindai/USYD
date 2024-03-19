public class Studio{
    
    private String name;
    private String location;
    private Movie[] movies;
    public String  getName(){
        return name;
    }
    public String getLocation(){
        return location;

    }
    public Movie[] getMovies(){
        return movies;
    }
    public Studio(String name, String location, Movie[] movies){
        this.name=name;
        this.location=location;
        this.movies=movies;
    }
}