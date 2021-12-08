package Michael.Penfield;

public class Cell {
    private ship Ship;
    private boolean hit;


    Cell(){
        this.Ship = null;
        this.hit = false;
    }

    public ship getShip() {
        return Ship;
    }

    public void setShip(ship ship) {
        Ship = ship;
    }

    public boolean getHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
        this.Ship.addDamage();
    }

    public char getDispChar() {
       if (this.Ship != null){return Ship.getDspChar();
       }else if(this.hit = true && this.Ship != null){return 'X';
       }else if(this.hit = true && this.Ship != null){return 'M';}
       else{return '~';}
    }


    public Cell Copy(){
        Cell C = new Cell();
        C.setShip(this.Ship);
        C.setHit(this.hit);
        return C;
    }

}
