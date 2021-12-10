package Michael.Penfield;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class BattleShip {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<player> Players = null;
        Players = getPlayers(input);

        System.out.println("Number of ships:");
        int amount = input.nextInt();
        placeBoats(Players, amount, input);
        boolean win = false;
        while(win == false){
            for(player P: Players) {
                int i = 0;
                if(checkwin(Players) == true){
                    win = true;
                    System.out.println("Nice job " + P + ", you killed them all");
                }
                if(P.getLost() == false){
                    while (i < Players.size()) {
                        pl_shoot(P, input, Players.get(i));
                        i++;
                        }
                    }
                }
            }
        }


    public static boolean checkwin(ArrayList<player> Players){
        int g = 0;
        for(player P: Players){
            if (P.getLost() == false){
                g += 1;
            }
        }

        if(g <= 1){
            return true;
        }
        return false;
    }


    public static void pl_shoot(player Pl,Scanner input, player victim){
        boolean j = false;
        while (j == false){
            Pl.getBoard().print(true);
            int inp[] = {0,0};
            System.out.println(Pl.getName());
            System.out.println("Your board");
            Pl.getBoard().print(false);
            System.out.println(victim.getName());
            victim.getBoard().print(true);
            System.out.println("Cols");
            inp[0] = input.nextInt();
            System.out.println("Rows");
            inp[1] = input.nextInt();
            if (inp[0] < 0 || inp[1] < 0
                    ||inp[0] > victim.getBoard().getCols() - 1
                    || inp[1] > victim.getBoard().getRows() - 1){
                j = false;
                System.out.println("Out of bounds of the board try again");
            }else if(victim.getBoard().getField().get(inp[0]).get(inp[1]).getHit() == true){
                System.out.println("You already shot at: " + inp[0] + "," + inp[1]);
            }else{
                j = true;
                boolean k = victim.getBoard().getField().get(inp[0]).get(inp[1]).setHit(true);
                if(k == true){
                    System.out.println("You sank: " + victim.getName() + "'s " + victim.getBoard().getField().get(inp[0]).get(inp[1]).getShip().getModel());
                    victim.setLost(checkloss(victim));
                }
            }

        }

    }

    public static boolean checkloss(player p){
        for (int i = 0; i < p.getBoard().getField().size(); i++) {
            for (int j = 0; j < p.getBoard().getField().get(i).size(); j++) {
                if (p.getBoard().getField().get(i).get(j).getShip() != null){
                    if(p.getBoard().getField().get(i).get(j).getShip().checkDead() == false){
                        return true;
                    }
                }
            }
        }
        return  true;
    }






    public static ArrayList<player> getPlayers(Scanner s){
        ArrayList<player> pl = new ArrayList<player>();
        System.out.println("Number of players: ");
        int i = s.nextInt();
        System.out.println("Number of Rows for board: ");
        int rows = s.nextInt();
        System.out.println("Number of columns for board:");
        int cols = s.nextInt();
        for(int j = 0; j < i; j++){
            player p = new player();
            System.out.println("Player " + (j + 1) + ", enter your name:");
            Board board = new Board(rows, cols);
            p.setBoard(board);
            p.setName(s.next());
            pl.add(p);
        }
        return pl;
    }

    public static void placeBoats(ArrayList<player> players, int amount, Scanner input){
        for(int i = 0; i < players.size(); i++){
            ArrayList<String> alreadySelected = new ArrayList<String>();

            for(int j = 0; j < amount; ){
                Boolean good = true;
                System.out.println(players.get(i).getName());
                System.out.println("Place the destroyer: D\nPlace the cruiser: C\nPlace the battleship:B \nPlace the submarine:S \nPlace the carrier: K");
                String inp = "";
                inp = input.next();
                inp = inp.toUpperCase(Locale.ROOT);

                String shipParams = "ADCBSK";
                if(shipParams.contains(inp) == false){
                    good = false;                           ///check that it is a valid ship
                }

                ////if (alreadySelected.size() != 0) {
                ////    for (int a = 0; a < alreadySelected.size(); ) {
                ////        if (inp == alreadySelected.get(a)){     ///checks if they already selecte a ship not work
                ////            good = false;
                ////        }
                ////    }
                ////}
                if (good == true) {
                    switch (inp) {
                        case "D":
                            System.out.println("Place the Destroyer");
                            placementParam(players.get(i),new ship(ship.shipType.destroyer), input);
                            j++;
                            break;
                        case "C":
                            System.out.println("Place the Cruiser");
                            placementParam(players.get(i),new ship(ship.shipType.cruiser), input);
                            j++;
                            break;
                        case "B":
                            System.out.println("Place the Battleship");
                            placementParam(players.get(i),new ship(ship.shipType.battleship), input);
                            j++;
                            break;
                        case "S":
                            System.out.println("Place the Submarine");
                            placementParam(players.get(i),new ship(ship.shipType.submarine), input);
                            j++;
                            break;
                        case "K":
                            System.out.println("Place the Carrier");
                            placementParam(players.get(i),new ship(ship.shipType.carrier), input);
                            j++;
                            break;
                    }


                }
            }
        }
    }
    public static void placementParam(player play, ship s, Scanner input){
        String inp = "";
        boolean acceptible = false;
        Board tempBoard = new Board(play.getBoard().getRows(), play.getBoard().getCols());///play.getBoard().Copy();
        tempBoard.Paste(play.getBoard());
        play.getBoard().print(false);
        boolean j = false;
        int[] tmp = s.getOrigin();
        while (j == false) {
            System.out.println("Place at a specific spot: rows, colums");
            System.out.println("Rows:");
            tmp[0] = input.nextInt();
            if(tmp[0] < play.getBoard().getCols() - 1){
                j = true;
            }
            System.out.println("Colums:");
            tmp[1] = input.nextInt();
            if (tmp[0] > 0){
                j = true;
            }
        }


        boolean chk = false;
        while (chk == false) {

            tmp = s.getOrigin();
            switch (inp) {
                case"T":
                    tmp[0] = tmp[0] - 1;
                    s.setOrigin(tmp);
                    break;
                case"H":
                    tmp[1] = tmp[1] + 1;
                    s.setOrigin(tmp);
                    break;
                case"G":
                    tmp[0] = tmp[0] + 1;
                    s.setOrigin(tmp);
                    break;
                case"F":
                    tmp[1] = tmp[1] - 1;
                    s.setOrigin(tmp);
                    break;
                case"N":
                    s.setShipDirection(ship.direction.North);
                    break;
                case"E":
                    s.setShipDirection(ship.direction.east);
                    break;
                case"S":
                    s.setShipDirection(ship.direction.south);
                    break;
                case"W":
                    s.setShipDirection(ship.direction.west);
                    break;
            }
            if (inp.equals("P")){
                if (acceptible == false) {
                    int[] z = s.getOrigin();
                    System.out.println("Cannot place ship here: " + z[0] + "," + z[1]);
                    tempBoard.Paste(play.getBoard());
                    inp = "";
                }else if (acceptible == true) {
                    tempBoard.placeShip(s);
                    chk = true;
                    play.getBoard().Paste(tempBoard);
                }
            }else{
                    System.out.println("3");
                tempBoard.Paste(play.getBoard());
                acceptible = tempBoard.placeShip(s);
                tempBoard.print(false);
                System.out.println("Confirm placement: enter 'P' to place,\n 'T' to go up,\n 'G' to go down,\n 'F' to go left, \n 'H' to go right, \n and NESW to change cardinal direction");
                int[] g = s.getOrigin();
                System.out.println(g[0] + ","+ g[1]);
                inp = input.next();
                inp = inp.toUpperCase(Locale.ROOT);}
        }

    }
}

