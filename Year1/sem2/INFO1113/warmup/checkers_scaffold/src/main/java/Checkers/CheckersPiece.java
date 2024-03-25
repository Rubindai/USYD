// General Description
// Package: Checkers
// Purpose: Represents a single piece in the checkers game, holding information about the piece's color ('w' for white, 'b' for black) and its position on the board.
// Methods:
// Constructors to set the piece's color.
// getColour() and setPosition(Cell p) for accessing and updating the piece's color and position.
// getPosition() for retrieving the current position.
// getAvailableMoves(Cell[][] board) (not implemented) intended to calculate possible moves.
// capture() and promote() methods (not fully implemented) for handling captures and promotions of pieces.
// draw(App app) for drawing the piece on the board using Processing methods.

package Checkers;

import java.util.*;

public class CheckersPiece {

	// The color of the checkers piece ('w' for white, 'b' for black)
	private char colour;

	// The current position of the piece on the board
	private Cell position;
	public boolean isking;
	// Constructor: Initializes a new piece with a given color
	public CheckersPiece(char c) {
		this.colour = c;
	}

	// Returns the color of the piece
	public char getColour() {
		return this.colour;
	}

	// Sets the position of the piece to a given cell
	public void setPosition(Cell p) {
		this.position = p;
	}

	// Returns the current position of the piece
	public Cell getPosition() {
		return this.position;
	}
	
	public HashSet<Cell> getAvailableMoves(CheckersPiece p,Cell[][] board) {
		//TODO: Get available moves for this piece depending on the board layout, and whether this piece is a king or not
		//How to record if the move is a capture or not? Maybe make a new class 'Move' that stores this information, along with the captured piece?
		king(board);
		HashSet <Cell> avlmoves=new HashSet<Cell>();
		int x_board=p.getPosition().getrealx();
		int y_board =p.getPosition().getrealy();
		
		

		Cell c1=board[x_board][y_board];
		if(p.getColour()=='b'){
			
				try{
				c1=board[x_board-1][y_board+1];
				if(c1.getPiece()==null){
					avlmoves.add(c1);
				}
				else if(c1.getPiece().getColour()=='w'){
					c1=board[x_board-2][y_board+2];
					if(c1.getPiece()==null){
						
						avlmoves.add(c1);
					}
				}
			} catch(Exception e){
				;
			}
			try{
				c1=board[x_board-1][y_board-1];
				if(c1.getPiece()==null){
					avlmoves.add(c1);
				}
				else if(c1.getPiece().getColour()=='w'){
					c1=board[x_board-2][y_board-2];
					if(c1.getPiece()==null){
						
						avlmoves.add(c1);
					}
				}
				
			}catch(Exception e){
				;
			}
			if(p.isking){
				try{
					c1=board[x_board+1][y_board-1];
					if(c1.getPiece()==null){
						avlmoves.add(c1);
					}
					else if(c1.getPiece().getColour()=='w'){
						c1=board[x_board+2][y_board-2];
						if(c1.getPiece()==null){
							
							avlmoves.add(c1);
						}
					}
				} catch(Exception e){
					;
				}
				try{
					c1=board[x_board+1][y_board+1];
					if(c1.getPiece()==null){
						avlmoves.add(c1);
					}
					else if(c1.getPiece().getColour()=='w'){
						c1=board[x_board+2][y_board+2];
						if(c1.getPiece()==null){
							
							avlmoves.add(c1);
						}
					}
					
				}catch(Exception e){
					;
				}
			}
		    
		}
		else if(p.getColour()=='w'){
			
			try{
			c1=board[x_board+1][y_board-1];
			if(c1.getPiece()==null){
				avlmoves.add(c1);
			}
			else if(c1.getPiece().getColour()=='b'){
				c1=board[x_board+2][y_board-2];
				if(c1.getPiece()==null){
					
					// System.out.println('H');
					avlmoves.add(c1);
				}
			}
		} catch(Exception e){
			;
		}
		try{
			c1=board[x_board+1][y_board+1];
			if(c1.getPiece()==null){
				avlmoves.add(c1);
			}
			else if(c1.getPiece().getColour()=='b'){
				c1=board[x_board+2][y_board+2];
				if(c1.getPiece()==null){
					avlmoves.add(c1);
				}
			}
			
		}catch(Exception e){
			;
		}
		if(p.isking){
			try{
				c1=board[x_board-1][y_board-1];
				if(c1.getPiece()==null){
					avlmoves.add(c1);
				}
				else if(c1.getPiece().getColour()=='b'){
					c1=board[x_board-2][y_board-2];
					if(c1.getPiece()==null){
						
						avlmoves.add(c1);
					}
				}
			} catch(Exception e){
				;
			}
			try{
				c1=board[x_board-1][y_board+1];
				if(c1.getPiece()==null){
					avlmoves.add(c1);
				}
				else if(c1.getPiece().getColour()=='b'){
					c1=board[x_board-2][y_board+2];
					if(c1.getPiece()==null){
						
						avlmoves.add(c1);
					}
				}
				
			}catch(Exception e){
				;
			}
		}
		
	}
		return avlmoves;
		}
		

		
	
	
	public void capture() {
		//capture this piece
	}
	
	public void king(Cell[][] board) {
		//promote this piece
			for(int i=0;i<board.length;i++){
				// System.out.println(i);
				try{
				if(board[0][i].getPiece().getColour()=='b'){
					board[0][i].getPiece().isking=true;
				}
			} catch (Exception e){
				;
			}
			}
			for(int i=0;i<board.length;i++){
				try{
				if(board[7][i].getPiece().getColour()=='w'){
					board[7][i].getPiece().isking=true;
				}}
				catch(Exception e){
					;
				}
			}


	}
	
	// Draws the piece on the board using the Processing library.
    // This method takes an instance of the App class, which extends PApplet from Processing, to access drawing methods.
	public void draw(App app)
	{
		// Set the stroke weight for the outline of the piece
		app.strokeWeight(5.0f);

		if (colour == 'w') {
			// White piece
			app.fill(255); // white fill
			app.stroke(0); // black stroke
		} else if (colour == 'b') {
		    // Black piece
			app.fill(0); // black fill
			app.stroke(255);// white stroke
		}

		// Draw the piece as an ellipse (circle) at the piece's position, adjusting the coordinates based on the cell size
		// The method elipse takes 4 parameters
		// Syntax:  ellipse(a, b, c, d)	
		// Parameters
		// a	(float)	x-coordinate of the ellipse
		// b	(float)	y-coordinate of the ellipse
		// c	(float)	width of the ellipse by default
		// d	(float)	height of the ellipse by default
		if(isking){
		app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.8f, App.CELLSIZE*0.8f);
		app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.4f, App.CELLSIZE*0.4f);
		}
		else{
			app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.8f, App.CELLSIZE*0.8f);

		}
		
		// Disable the stroke for subsequent drawings
		app.noStroke();
	}
}