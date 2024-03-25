import java.util.*;
public class MovieStore {
    private String name;
    private String location;
    private HashMap<Movie,Integer> movies = new HashMap<Movie,Integer>();
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public HashMap<Movie,Integer> getMovies(){
            return movies;
    }
    public void addMovie(Movie movie){
        if(movies.containsKey(movie)){
            movies.put(movie,movies.get(movie)+1);
        }
        else{
            movies.put(movie,1);
        }
    }
    public boolean rentMovie(Movie movie){
        if(movies.containsKey(movie)){
            movies.put(movie,movies.get(movie)-1);
            return true;
        }
        else{
            return false;
        }

    }
    public HashSet<Movie> getMoviesByStudio(String studioName){
        HashSet<Movie> movieset=new HashSet<Movie>();
        for(Movie a : movies.keySet()){
            if(a.getStudio().getName()==studioName){
                    movieset.add(a);
            }
        }
        return movieset;
    }
    public LinkedList<Movie> getMoviesWithoutCopyright(){
        LinkedList<Movie> mvlist=new LinkedList<Movie>();
        for(Movie a : movies.keySet()){
            // boolean result=a.copyrighted();
            if(!a.copyrighted()){
                    mvlist.add(a);
            }
        }
        return mvlist;
    }
    public MovieStore(String name,String location){
        this.name=name;
        this.location=location;
    }
}