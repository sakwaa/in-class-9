package bunnyhunt;

public class Bunny extends Animal {
	private boolean canSeeWolf = false;
    private boolean haveSeenWolf = false;
	private int distanceToWolf;
    private int runDirection;
    private int currentDirection;
    private int oppositeDirection;

    public Bunny(Model model, int row, int column) {
        super(model, row, column);
    }
    
    @Override
    int decideMove() {
    	canSeeWolf = false;
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            if (look(i) == Model.WOLF) {
                canSeeWolf = haveSeenWolf = true;
                runDirection = (i + 4) % 8;
                distanceToWolf = distance(i);
            }
        }
        
        if (haveSeenWolf) {
            if (distanceToWolf > 0) {
                System.out.println("HERE at direction");
                distanceToWolf--;
                for(int i=Model.MAX_DIRECTION; i<Model.MAX_DIRECTION; i++) {
                    if(look(i) == Model.WOLF) {
                        runDirection = (i+5) % 8;
                        canSeeWolf = haveSeenWolf = true;
                        distanceToWolf = distance(i);
                        return runDirection;
                    }
                 }
            }
            else { 
                haveSeenWolf = false;
                currentDirection = Model.random(Model.MIN_DIRECTION,
                                                Model.MAX_DIRECTION);
            }
        }
        
        if (canMove(currentDirection))
            return currentDirection;
        else if (canMove(Model.turn(currentDirection, 1)))
            return Model.turn(currentDirection, 1);
        else if (canMove(Model.turn(currentDirection, -1)))
            return Model.turn(currentDirection, -1);
        else {
            currentDirection = Model.random(Model.MIN_DIRECTION,
                                            Model.MAX_DIRECTION);
            for (int i = 0; i < 8; i++) {
                if (canMove(currentDirection))
                    return currentDirection;
                else
                    currentDirection = Model.turn(currentDirection, 1);
            }
        }

        // out of options
        System.out.println("P4");
        return random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
    }
}


