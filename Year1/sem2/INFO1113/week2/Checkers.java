import java.util.Scanner;
public class Checkers {

    /**
     * Represents the game board as a 2D array.
     * 'B' for Black pieces, 'W' for White pieces, 'E' for Empty spaces, 'BK' for
     * Black Kings, and 'WK' for White Kings.
     */
    private static String[][] board = new String[8][8];
    static boolean player_one=true; //black
    static boolean player_two=false;
    



    /**
     * Initialises the board with pieces in their starting positions.
     */
    private static void initialiseBoard() {
        // Implement this method to fill the board array with pieces in their starting
        // positions.
        for(int k =0;k<8;k++){
            for (int g =0;g<8;g++){
                board[k][g]=" ";
            }
        }
        for (int i=0;i<3;i++){ //Set white
            int j;
            if (i%2 ==0){
                 j = 1;
            }
            else{
                 j =0;
            }
            for (;j<board[i].length;j+=2){
                board[i][j]="w";
            }
        }

        for (int i=5;i<8;i++){ //Set black
            int j;
            if (i%2 ==0){
                j = 1;
            }
            else{
                j =0;
            }
            for (;j<board[i].length;j+=2){
                board[i][j]="b";
            }
        }
    }

    /**
     * Displays the current state of the board to the console.
     */
    private static void displayBoard() {
        // Implement this method to print the board to the console.
          for(int k =0;k<8;k++){
            for (int g =0;g<8;g++){
                System.out.print("|");
                System.out.print(board[k][g]);
            }
            System.out.print("|");
            System.out.println("");    
    }
    System.out.println(""); 
    
    }
  

    /**
     * Main game loop. Handles player turns and checks for game end conditions.
     */
    private static void startGame() {
        // Implement the game loop, handling player input, turn switching, and win
        // condition checking.
        
        Scanner scan =new Scanner(System.in);
        
        boolean flag_error = false;
        
        String exitx = "exit";
        String tox ="to";
        String view ="View";
        int x1 =-1;
        int x2= -1;
        int y1 = -1;
        int y2=-1;
        
        while (true){
            while (true){
            String input = scan.nextLine();

        
            if (input.equals(exitx)){ //player one black
                System.out.println(""); 
                System.exit(0);
            }
            else if (input.equals(view)){
                System.out.println(""); 
                displayBoard();
            }
            else{
                String[] instruction = input.split(" ");
                if(instruction.length!=3){
                    flag_error = true;
                    break;
                }
                else{
                    String start = instruction[0];
                    String end = instruction[2];
                    String middle = instruction[1];
                   
                    if (start.length()!=2 || end.length()!=2 || !middle.equals(tox)){
                        flag_error = true;
                        break;
                    }
                    //start processing
                    char x1ch =start.charAt(0);
                    char x2ch =start. charAt(1); 
                    if (x1ch =='A'){
                        x1 =0;
                    }
                    else if (x1ch =='B'){
                        x1 =1;
                    }
                    else if (x1ch =='C'){
                         x1 =2;
                    }
                    else if (x1ch =='D'){
                        x1 =3;
                    }
                    else if (x1ch =='E'){
                        x1 =4;
                    }
                    else if (x1ch =='F'){
                        x1 =5;
                    }
                    else if (x1ch =='G'){
                        x1 =6;
                    }
                    else if (x1ch =='H'){
                        x1 =7;
                    }
                    else{
                        flag_error = true;
                        break;
                    }
                    try{
                    x2 = Integer.parseInt(String.valueOf(x2ch)); }
                    catch(Exception e){
                        flag_error = true;
                        break;
                    }
                    // System.out.println(x1+" "+x2); //convertion test
                    char y1ch =end.charAt(0);
                   
                  
                    
                    char y2ch =end.charAt(1); 
                    if (y1ch =='A'){
                        y1=0;
                    }
                    else if (y1ch =='B'){
                        y1 =1;
                    }
                    else if (y1ch =='C'){
                         y1 =2;
                    }
                    else if (y1ch =='D'){
                        y1 =3;
                    }
                    else if (y1ch =='E'){
                        y1 =4;
                    }
                    else if (y1ch =='F'){
                        y1 =5;
                    }
                    else if (y1ch =='G'){
                        y1 =6;
                    }
                    else if (y1ch =='H'){
                        y1 =7;
                    }
                    else{
                        flag_error = true;
                        break;
                    }
                    try{
                        y2 = Integer.parseInt(String.valueOf(y2ch)); }
                        catch(Exception e){
                            flag_error = true;
                            break;
                        }
                    // System.out.println(x1+" "+x2+" "+y1+" "+y2);//convertsion test
                    x2=x2-1;
                    y2=y2-1;
                    if((x2>7||x2<0)||(y2>7||y2<0)){
                        flag_error = true;
                        break;
                    }
                    if (flag_error==false){
                        isGameOver();
                        // x2=Math.abs(x2-8);
                        // y2=Math.abs(y2-8);
                        if(isValidMove(x2,x1,y2,y1)){
                            
                            processMove(x2,x1,y2,y1);
                            player_one=!player_one;
                            player_two=!player_two;
                            displayBoard();
                            isGameOver();
                        }
                        else{
                            flag_error = true;
                            break;
                        }
                        
                        
                    }
                    


                }

            }
        
        }
        if (flag_error==true){
        System.out.println("Error!");
        flag_error=false;
        displayBoard();
    }
        //break
    }
    }

    /**
     * Processes a player's move.
     * 
     * @param move A string representing the player's move (e.g., "C3 to D4").
     * @return true if the move is valid and executed, false otherwise.
     */
    private static void processMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Implement this method to process the player's move.
        // You should validate the move and execute it if it's valid.
        String start=board[fromRow][fromCol];
        String end =board[toRow][toCol];
        String black_small="b";
        String white_small="w";
        board[fromRow][fromCol]=" ";
        board[toRow][toCol]=start;
        for(int i=0;i<8;i++){
            if(board[0][i].equals(black_small)){
                board[0][i]="B";
            }
        }
        for(int i=0;i<8;i++){
            if(board[7][i].equals(white_small)){
                board[7][i]="W";
            }
        }
    }

    /**
     * Checks if a move is valid.
     * 
     * @param fromRow the starting row of the move.
     * @param fromCol the starting column of the move.
     * @param toRow   the ending row of the move.
     * @param toCol   the ending column of the move.
     * @return true if the move is legal, false otherwise.
     */

     private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Implement this method to check if a move is legal according to the rules of
        // Checkers.
        String black_small="b";
       String white_small="w";
        String black_k ="B";
        String white_k="W";
        
        int valid_x;
        int valid_y;

        String blank =" ";
        
        String start=board[fromRow][fromCol];
        String end =board[toRow][toCol];
        if (!end.equals(blank)){
            return false;
        }
        valid_x=toRow-fromRow;
        valid_y=toCol-fromCol;
        if (!((Math.abs(valid_x)==2 &&Math.abs(valid_y)==2) || (Math.abs(valid_x)==1 &&Math.abs(valid_y)==1))){
            return false;
                }
        if (player_one){
            if (!start.equals(black_k)&& !start.equals(black_small)){
                return false;
            }
            if (start.equals(black_small)){
                
                if(valid_x==-1 && Math.abs(valid_y)==1){
                    return true;
                }
                if(valid_x==-2 &&  Math.abs(valid_y)==2){
                    if (valid_y>0){
                        if(board[fromRow-1][fromCol+1].equals(white_small) ||board[fromRow-1][fromCol+1].equals(white_k) ){
                            board[fromRow-1][fromCol+1]=" ";
                            return true;
               }       else{
                        return false;
                }
            }
                    else if (valid_y<0){
                        if(board[fromRow-1][fromCol-1].equals(white_small) ||board[fromRow-1][fromCol-1].equals(white_k) ){
                            board[fromRow-1][fromCol-1]=" ";
                            return true;

                    }   else{
                        return false;
                    }
                } 

            } 
            
        }
            if(start.equals(black_k)){
                
                if(Math.abs(valid_x)==1 && Math.abs(valid_y)==1){
                    return true;
                }
                if(valid_x==-2 && valid_y==2 ){
                    if(board[fromRow-1][fromCol+1].equals(white_small) ||board[fromRow-1][fromCol+1].equals(white_k) ){
                        board[fromRow-1][fromCol+1]=" ";
                        return true;
                }   else{
                    return false;
                }
            }   
                if(valid_x==-2 && valid_y==-2 ){
                    if(board[fromRow-1][fromCol-1].equals(white_small) ||board[fromRow-1][fromCol-1].equals(white_k) ){
                        board[fromRow-1][fromCol-1]=" ";
                        return true;
                }   else{
                    return false;
                }
            }
                if(valid_x==2 && valid_y==-2 ){
                    if(board[fromRow+1][fromCol-1].equals(white_small) ||board[fromRow+1][fromCol-1].equals(white_k) ){
                        board[fromRow+1][fromCol-1]=" ";
                        return true;
                }   else{
                    return false;
                }
            } 
                if(valid_x==2 && valid_y==2 ){
                    if(board[fromRow+1][fromCol+1].equals(white_small) ||board[fromRow+1][fromCol+1].equals(white_k) ){
                        board[fromRow+1][fromCol+1]=" ";
                        return true;
                }   else{
                    return false;
                }
            }

        
    }
} 
    if (player_two){
        if (!start.equals(white_k)&& !start.equals(white_small)){
            return false;
        }
        if (start.equals(white_small)){
            
            if(valid_x==1 && Math.abs(valid_y)==1){
                return true;
            }
            if(valid_x==2 &&  Math.abs(valid_y)==2){
                if (valid_y>0){
                    if(board[fromRow+1][fromCol+1].equals(black_small) ||board[fromRow+1][fromCol+1].equals(black_k) ){
                        board[fromRow+1][fromCol+1]=" ";
                        return true;
           }       else{
                    return false;
            }
        }
                else if (valid_y<0){
                    if(board[fromRow+1][fromCol-1].equals(black_small) ||board[fromRow+1][fromCol-1].equals(black_k) ){
                        board[fromRow+1][fromCol-1]=" ";
                        return true;

                }   else{
                    return false;
                }
            } 

        } 
        
    }
        if(start.equals(white_k)){
            
            if(Math.abs(valid_x)==1 && Math.abs(valid_y)==1){
                return true;
            }
            if(valid_x==2 && valid_y==-2 ){
                if(board[fromRow+1][fromCol-1].equals(black_small) ||board[fromRow+1][fromCol-1].equals(black_k) ){
                    board[fromRow+1][fromCol-1]=" ";
                    return true;
            }   else{
                return false;
            }
        }   
            if(valid_x==2 && valid_y==2 ){
                if(board[fromRow+1][fromCol+1].equals(black_small) ||board[fromRow+1][fromCol+1].equals(black_k) ){
                    board[fromRow+1][fromCol+1]=" ";
                    return true;
            }   else{
                return false;
            }
        }
            if(valid_x==-2 && valid_y==+2 ){
                if(board[fromRow-1][fromCol+1].equals(black_small) ||board[fromRow-1][fromCol+1].equals(black_k) ){
                    board[fromRow-1][fromCol+1]=" ";
                    return true;
            }   else{
                return false;
            }
        } 
            if(valid_x==-2 && valid_y==-2 ){
                if(board[fromRow-1][fromCol-1].equals(black_small) ||board[fromRow-1][fromCol-1].equals(black_k) ){
                    board[fromRow-1][fromCol-1]=" ";
                    return true;
            }   else{
                return false;
            }
        }

    
}

    }
    return false;
    }
    

    /**
     * Checks if the game has ended.
     * 
     * The program should terminate if the game has finished.
     */
    private static void isGameOver() {
        // Implement this method to check for win conditions.
        
        String black_small="b";
        String white_small="w";
        String black_k ="B";
        String white_k="W";
        int cont1=0;
        int cont2=0;
        for (int i =0; i<board.length;i++){
            for (int j =0;j<board[i].length;j++){
                if(board[i][j].equals(black_small)||(board[i][j].equals(black_k))){
                    cont1+=1;
                }
                if(board[i][j].equals(white_k)||(board[i][j].equals(white_small))){
                    cont2+=1;
                }
            }
        }
        if (cont1==0||cont2==0){
            System.exit(0);
        }
        
    }

    /**
     * Main method to run the game.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        initialiseBoard();
        displayBoard();
        startGame();
    }
}