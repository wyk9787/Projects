package com.gamewerks.blocky.engine;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import java.util.Random;

public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    
    private Board board;
    private Piece activePiece;
    private Direction movement;
    
    private int lockCounter;
    private static int index;
    private static PieceKind pieces[];
    
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        index = 0;
        pieces = PieceKind.ALL;
        shuffle(pieces);
        trySpawnBlock();
    }
    
    private static void shuffle(PieceKind pieces[]) {
		Random rand = new Random();
    	for (int i = 0; i < pieces.length - 1; i++) {
    		int  j = rand.nextInt(pieces.length - i) + i;
    		PieceKind temp = pieces[j];
    		pieces[j] = pieces[i];
    		pieces[i] = temp;
    	}
    }
    
    private void trySpawnBlock() {
    	if(index > pieces.length - 1) {
    		shuffle(pieces);
    		index = 0;
    	}
        if (activePiece == null) {
            activePiece = new Piece(pieces[index++], new Position(4, Constants.BOARD_WIDTH / 2 - 2));
            if (board.collides(activePiece)) {
                System.exit(0);
            }
        }
    }
    
    private void processMovement() {
        Position nextPos;
        switch(movement) {
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
            break;
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToWell(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    public void step() {
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() { return activePiece; }
    public void setDirection(Direction movement) { this.movement = movement; }
    public void rotatePiece(boolean dir) { 
    	activePiece.rotate(dir); 
    	if(board.collides(activePiece)) {
    		activePiece.rotate(!dir);
    	}
    }
}
