import java.util.HashMap;

public class SudokuSolve {
	
	public static boolean isSolvable(Integer[][] board) {
		//TODO
		
		//checkrow
        HashMap<String,Integer> a= new HashMap<String,Integer>();
        for(int i=0;i<board.length;i+=1){
            for(int j=0;j<board[i].length;i+=1){
                if(a.containsKey(board[i][j])){
                    a.put(board[i][j],a.get(board[i][j])+1);
                }
                else{
                    a.put(board[i][j],a.get(1));
                }
                
            }
        }
        
        for(int i:a.keySet()){
            if(c!=null&&a.get(i)>1){
                return false;
            }

        }
	}
	
	public static void main(String[] args) {
		//Write some tests if you want check here or change b
		Integer[][] b = { 
				{ 1, 2, 3},
				{ null, null, null},
				{ null, null, null}
			};
		if(isSolvable(b)) {
			System.out.println("Yes this is solvable!");
		}
	}
	
}