package dev.ThreadProblems;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Maze {
	private static final int MAZE_SIZE=4;
	private final String[][] cells= new String[MAZE_SIZE][MAZE_SIZE];

	public Maze() {
		for (String[] row : cells) {
			Arrays.fill(row,"");
		}
	}
	public int[] getNextLocation(int[] lastSpot){
		int[] nextSpot=new int[2]; //[row,column]
		nextSpot[1]=(lastSpot[1]==Maze.MAZE_SIZE-1)?0:lastSpot[1]+1;
		nextSpot[0]=lastSpot[0];
		if (nextSpot[1]==0){
			nextSpot[0]=(lastSpot[0]==Maze.MAZE_SIZE-1)?0:lastSpot[0]+1;  //come back to begining of maze fromlast row or increment row
		}
		return nextSpot;
	}

	public void moveLocation(int locX,int locY,String name){
		cells[locX][locY]=name.substring(0,1);
		resetSearchCells(name);
	}

	public void resetSearchCells(String name){
		for (String[] cell : cells) {
			Arrays.asList(cell).replaceAll(c->c.equals("!"+name.charAt(0))?"":c);
		}
	}

	public boolean searchCell(String partner,int[] nextSpot,int[] lastSpot){
		if (cells[nextSpot[0]][nextSpot[1]].equals(partner.substring(0,1))){
			return true;
		}
		cells[lastSpot[0]][lastSpot[1]]="!"+partner.charAt(0);
		return false;

	}

	@Override
	public String toString() {
		return Arrays.deepToString(cells);
	}

}
