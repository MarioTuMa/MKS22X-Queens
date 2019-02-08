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
        to_return = to_return + (board[i][j]);
      }
      to_return = to_return+"/";
    }
    return to_return;
  }



  public boolean solve(){
    if(queen_counter == board.length){
      System.out.println(board.toString());
      return true;
    }
    else{

      for(int i = 0; i < board.length; i ++){
        if(board[queen_counter][i] == 0){
          addQueen(queen_counter,i);
          if(solve()){
            return true;
          }
          removeQueen(queen_counter,i);
        }
      }
      return false;
    }
  }

  public int countSolutions(){
    return 3;
  }
  public static void main(String args[]){
    System.out.println("hi");
    QueenBoard qb = new QueenBoard(8);
    System.out.println(qb.toString());
    qb.addQueen(1,1);
    System.out.println(qb.toString());
    qb.removeQueen(1,1);
    System.out.println(qb.toString());
    qb.solve();
    System.out.println(qb.toString());
  }
}
