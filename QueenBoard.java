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

  private boolean backtrack(){
    int last_q_index = -1;
    for(int i = 0; i < board.length; i ++){

      if(board[queen_counter - 1][i] == -1){
        last_q_index = i;
      }

    }



    removeQueen(queen_counter-1,last_q_index);
    // System.out.println("removing queen");
    // System.out.println(toString());
    for(int i = last_q_index + 1; i < board.length; i ++){
      if(board[queen_counter][i]==0){
        addQueen(queen_counter,i);
        return solve();
      }
    }
    return backtrack();
  }

  public boolean solve(){

    if(queen_counter == board.length){

      return true;
    }
    if(board[0][(board.length + 1)/2] == -1){

      return false;
    }
    else{
      boolean no_spaces = true;
      int good_spot = -1;

      for(int i = 0; i < board.length; i ++){

        if(board[queen_counter][i] == 0){
          no_spaces = false;
          if(good_spot == -1){
            good_spot = i;
          }
        }

      }


      if(no_spaces){
        return backtrack();

      }
      else{
            addQueen(queen_counter,good_spot);
            return solve();
      }
    }
  }

  public int countSolutions(){

    int counter = 0;
    while(solve()){
      backtrack();
      counter++;
    }
    counter *= 2;

    if(board.length % 2 ==1){
      QueenBoard nb = new QueenBoard(board.length);
      nb.addQueen(0,board.length/2);
      while(nb.solve()){
        nb.backtrack();
        counter--;
      }
    }
    return counter;
  }
  public static void main(String args[]){

    QueenBoard qb = new QueenBoard(10);

    System.out.println(qb.countSolutions());


  }
}
