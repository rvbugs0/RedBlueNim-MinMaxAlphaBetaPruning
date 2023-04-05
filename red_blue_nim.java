import java.util.*;

class red_blue_nim {

    private static int RED_VALUE = 2;
    private static int BLUE_VALUE = 3;

    public static int score(int redMarbles, int blueMarbles) {
        return redMarbles * RED_VALUE + blueMarbles * BLUE_VALUE;
    }

    public static void main(String[] args) {
        
        if (args.length < 2) {
            System.out.println("Usage: RedBlueNim <num-red> <num-blue> <first-player> <depth>");
            System.exit(1);
        }

        int numRed = Integer.parseInt(args[0]);
        int numBlue = Integer.parseInt(args[1]);
        String firstPlayer = "computer";
        int depth = 4;

        if (args.length >= 3) {
            firstPlayer = args[2];
        }

        if (args.length == 4) {
            depth = Integer.parseInt(args[3]);
        }
        red_blue_nim r = new red_blue_nim();

        r.game_start(numRed, numBlue, firstPlayer, depth);

        
    }

    public String human_turn() {
        
        String pile = "";
        System.out.print("Select a pile (red or blue): ");
        Scanner scanner = new Scanner(System.in);    
        while (!pile.equals("red") && !pile.equals("blue")) {
            
            pile = scanner.nextLine();
            pile = pile.toLowerCase();
            
        }
        
        return pile;
    }



    public String computer_turn(int redCount, int blueCount, int depth) {
        double bestScore = Double.NEGATIVE_INFINITY;
        String selected_pile = "";
        for (int pile = 0; pile <= 1; pile++) {
            int count = (pile == 0) ? redCount : blueCount;
            if (count > 0) {
                int newRedCount = (pile == 0) ? redCount - 1 : redCount;
                int newBlueCount = (pile == 1) ? blueCount - 1 : blueCount;
                int score = minValue(newRedCount, newBlueCount, depth - 1, -999999, 9999999);
                if (score > bestScore) {
                    bestScore = score;
                    selected_pile = (pile == 0) ? "red" : "blue";
                }
            }
        }
        return selected_pile;
    }

    public static int minValue(int redCount, int blueCount, int depth, int alpha, int beta) {
        if (redCount == 0 || blueCount == 0) {
            return score(redCount, blueCount);
        }
        if (depth == 0) {
            return eval(redCount,blueCount);
        }
        for (int pile = 0; pile <= 1; pile++) {
            int count = (pile == 0) ? redCount : blueCount;
            if (count > 0) {
                for (int i = 1; i <= count; i++) {
                    int newRedCount = (pile == 0) ? redCount - i : redCount;
                    int newBlueCount = (pile == 1) ? blueCount - i : blueCount;
                    int score = maxValue(newRedCount, newBlueCount, depth - 1, alpha, beta);
                    beta = Math.min(beta, score);
                    if (beta <= alpha) {
                        return beta;
                    }
                }
            }
        }
        return beta;
    }
    
    public static int maxValue(int redCount, int blueCount, int depth, int alpha, int beta) {
        if (redCount == 0 || blueCount == 0) {
            return score(redCount, blueCount);
        }
        if (depth == 0) {
            return eval(redCount,blueCount); 
        }
        for (int pile = 0; pile <= 1; pile++) {
            int count = (pile == 0) ? redCount : blueCount;
            if (count > 0) {
                for (int i = 1; i <= count; i++) {
                    int newRedCount = (pile == 0) ? redCount - i : redCount;
                    int newBlueCount = (pile == 1) ? blueCount - i : blueCount;
                    int score = minValue(newRedCount, newBlueCount, depth - 1, alpha, beta);
                    alpha = Math.max(alpha, score);
                    if (beta <= alpha) {
                        return alpha;
                    }
                }
            }
        }
        return alpha;
    }

    public static int eval(int redCount, int blueCount) {
    // Calculate the difference between the number of red and blue marbles
    int diff = redCount - blueCount;
    // If the difference is positive, the computer is winning
    if (diff > 0) {
        // Add a bonus based on the difference
        return diff + (int) Math.pow(diff, 2);
    } else if (diff < 0) {
        // If the difference is negative, the human is winning
        // Subtract a penalty based on the difference
        return diff - (int) Math.pow(Math.abs(diff), 2);
    } else {
        // If the difference is zero, it's a tie
        return 0;
    }
}

    

    public void game_start(int redCount, int blueCount, String firstPlayer, Integer depth) {
        
        Map<String, Integer> gameState = new HashMap<String, Integer>();
        gameState.put("red", redCount);
        gameState.put("blue", blueCount);
        String player = firstPlayer;
        Map<String, Integer> score = new HashMap<String, Integer>();
        score.put("human", 0);
        score.put("computer", 0);

        
        while (true) {
            
            System.out.println("\n========> Game statistics: " + gameState+"\n");
            

            
            if (gameState.get("red") == 0 || gameState.get("blue") == 0) {
                String winner = player.equals("computer") ? "human" : "computer";
                int finalScore = score.get(winner) + 2 * gameState.get("red") + 3 * gameState.get("blue");
                System.out.println("\n" + winner + " wins with a score of " + finalScore + "!");
                break;
            }

            
            if (player.equals("computer")) {
                String pile = computer_turn(gameState.get("red"), gameState.get("blue"), depth);
                System.out.println("Computer chooses pile " + pile);
                gameState.put(pile, gameState.get(pile) - 1);
            } else {
                String pile = human_turn();
                System.out.println("Human chooses pile " + pile);
                gameState.put(pile, gameState.get(pile) - 1);
            }

            player = player.equals("computer") ? "human" : "computer";
        }
    }
}
