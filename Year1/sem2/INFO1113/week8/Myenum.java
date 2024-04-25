public class Myenum {
    enum daysofWeek{
        Monday,Tuesday,Wed;
    }
    daysofWeek day;
    public Myenum(daysofWeek day){
        this.day=day;
    }
    public static void main(String[] args){
        System.out.println(daysofWeek.Monday);
    }
    
}
