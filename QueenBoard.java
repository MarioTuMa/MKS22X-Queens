import java.util.*;
public class QueenBoard{

  private int[][]board;
  private int queen_counter = 0;
  public static boolean attacks(int row1, int col1, int row2, int col2){
    return (row1 == row2) || (col1 == col2) || (Math.abs(col1 - col2) == Math.abs(row1 - row2));
  }
  public QueenBoard(int size){
    board = new int[size][size];
    for(int i = 0; i < size; i ++){
      for(int j = 0; j < size; j ++){
        board[i][j]=0;
      }
    }
  }
  private boolean addQueen(int row, int col){
    if(board[row][col]==0){
      queen_counter++;
      board[row][col] = -1;
      for(int i = row + 1; i < board.length; i ++){
        for(int j = 0; j < board.length; j ++){
          if(attacks(i,j,row,col)){
            board[i][j] += 1;
          }
        }
      }
      return true;
    }
    else{
      return false;
    }
  }
  private boolean removeQueen(int row, int col){
    if(board[row][col]==-1){
      queen_counter--;
      board[row][col] = 0;
      for(int i = row + 1; i < board.length; i ++){
        for(int j = 0; j < board.length; j ++){
          if(attacks(i,j,row,col)){
            board[i][j] -= 1;
          }
        }
      }
      return true;
    }
    else{
      return false;
    }
  }
  public String toString(){
    String to_return = "";
    for(int i = 0; i < board.length; i ++){
      for(int j = 0; j < board.length; j ++){
        if(board[i][j]==-1){
          to_return += "Q ";
        }
        else{
          to_return += "_ ";
        }
      }
      to_return = to_return+"\n";
    }
    return to_return;
  }



  public boolean solve(){

    if(queen_counter == board.length){
      //System.out.println(toString());
      return true;
    }

    else{
      boolean no_spaces = true;
      int good_spot = -1;

      for(int i = 0; i < board.length; i ++){

        if(board[queen_counter][i] == 0){
          addQueen(queen_counter,i);
          if(solve()){
            return true;
          }
          removeQueen(queen_counter-1,i);
        }


      }

      return false;

    }
  }


  private boolean backtrack(){
    int last_q_index = -1;
    if(queen_counter !=0){
      for(int i = 0; i < board.length; i ++){

        if(board[queen_counter - 1][i] == -1){
          last_q_index = i;
        }

      }
      removeQueen(queen_counter-1,last_q_index);
    }
    else{
      return false;
    }
    boolean didnt_do_stuff = true;

    for(int i = last_q_index + 1; i < board.length; i ++){
      if(board[queen_counter][i]==0){
        addQueen(queen_counter,i);
        didnt_do_stuff = false;
        return true;
      }
    }
    if(didnt_do_stuff){
      return backtrack();
    }
    if(last_q_index == -1){
      return false;
    }
    else{
      return false;
    }


  }

  public int countSolutions(){
    int counter =0;
    if(solve()){
      counter++;
    };
    while(backtrack()){
      if(solve()){
        counter++;
        //System.out.println(counter);
      }
    }

    return counter;
  }

  public static void main(String args[]){
    QueenBoard qb = new QueenBoard(8);

    System.out.println(qb.solve());
    System.out.println(qb.toString());
    QueenBoard qb1 = new QueenBoard(16);
     //
    System.out.println(qb1.countSolutions());


  }
}
