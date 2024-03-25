public class Movie{
    private String name;
    private Studio studio;
    private int releaseYear;
    private Actor[] actors;
    public String getName(){
        return name;
    }
    public Studio getStudio(){
        return studio;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public Actor[] getActors(){
        return actors;
    }
    public String getStudioLocation(){
        return studio.getLocation();

    }
    public boolean copyrighted(){
        if(releaseYear+50<2024){
            return false;
        }
        else{
            return true;
        }
        
    }
   public Movie(String name,Studio studio,int year,Actor[] actors){
    this.name=name;
    this.studio=studio;
    releaseYear=year;
    this.actors=actors;
   }
}