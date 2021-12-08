package Michael.Penfield;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Cell>> field;
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setField(ArrayList<ArrayList<Cell>> field) {this.field = field;}
    public ArrayList<ArrayList<Cell>> getField() {
        return field;
    }

    ///places the ship on the board, returns true if it can false if it can't
    public boolean placeShip(ship Ship){
        int[] pl = Ship.getOrigin();
        int i = 0;

        if(pl[0] < 0){              ///checks to see if the input is out of bounds
            return false;
        }else if(pl[1] >  rows){
            return false;
        }else if(pl[1]  < 0){
            return false;
        } if(pl[0]  > cols){
            return false;}

        while (i < Ship.getLength()) {
            switch(Ship.getShipDirection()) {
                case North:
                    if (pl[0] - i < 0) {
                        return false;
                    } else if (this.field.get(pl[0] - i).get(pl[1]).getShip() != null) {
                        return false;
                    } else {
                        this.field.get(pl[0] - i).get(pl[1]).setShip(Ship);
                    }
                    break;
                case east:
                    if (pl[1] + i > rows - 1) {
                        return false;
                    } else if (this.field.get(pl[0]).get(pl[1] + i).getShip() != null) {
                        return false;
                    } else {
                        this.field.get(pl[0]).get(pl[1] + i).setShip(Ship);
                    }
                    break;
                case west:
                    if (pl[1] - i < 0) {
                        return false;
                    } else if (this.field.get(pl[0]).get(pl[1] - i).getShip() != null) {
                        return false;
                    } else {
                        this.field.get(pl[0]).get(pl[1] - i).setShip(Ship);
                    }
                    break;
                case south:
                    if(pl[0] + i > cols - 1){
                        return false;
                    } else if (this.field.get(pl[0]+ i).get(pl[1] ).getShip() != null){
                        return false;
                    }else{this.field.get(pl[0]+ i).get(pl[1]).setShip(Ship);}
                    break;
                 }
            i++;}
        return true;

    }

    public Board(){

    }

    public Board(int rows, int cols) {
        this.setRows(rows);
        this.setCols(cols);
        this.field = new ArrayList();
        for (int i = 0; i < rows; i++) {
            this.field.add(new ArrayList());
            for (int j = 0; j < cols; j++) {
                this.field.get(i).add(new Cell());
            }
        }
    }

    public void print(boolean hide){
        for(int i = 0; i < field.size(); i++)
        {
            for(int j = 0; j < field.get(i).size(); j++)
            {
                System.out.print(" ");
                if (hide == false){
                    System.out.print(field.get(i).get(j).getDispChar());
                }else{
                    if(field.get(i).get(j).getShip() == null && field.get(i).get(j).getHit() == false){System.out.print("~");}
                    else if (field.get(i).get(j).getShip() != null && field.get(i).get(j).getHit() == false){System.out.print("~");}
                    else if(field.get(i).get(j).getShip() == null && field.get(i).get(j).getHit() == true){System.out.print("o");}
                    else if(field.get(i).get(j).getShip() != null && field.get(i).get(j).getHit() == true){System.out.print("X");}
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Board Copy(){
        Board B = new Board(this.getRows(), this.getCols());
        B.setRows(this.getRows());
        B.setCols(this.getCols());
        for (int i = 0; i < field.size(); i++) {
            ///B.getField().add(new ArrayList());
            for (int j = 0; j < field.get(i).size(); j++) {

                B.getField().get(i).add(this.field.get(i).get(j).Copy());
            }
        }

        return B;
    }


    public void Paste(Board nBoard){
        for (int i = 0; i < field.size(); i++) {
            ///B.getField().add(new ArrayList());
            for (int j = 0; j < field.get(i).size(); j++) {
                this.field.get(i).set(j, nBoard.getField().get(i).get(j).Copy());
            }
        }
    }
}
