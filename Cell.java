package Michael.Penfield;

public class Cell {
    private ship Ship;
    private boolean hit;


    Cell(){
        this.Ship = null;
        this.hit = false;
    }

    public ship getShip() {
        return this.Ship;
    }

    public void setShip(ship ship) {
        Ship = ship;
    }

    public boolean getHit() {
        return this.hit;
    }

    public boolean setHit(boolean hit) {
        this.hit = hit;
        if (this.Ship != null){
            return this.Ship.addDamage();  ///bad practice to return a methods return
        }
            return false;
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
        C.hit= this.hit;///had to do this because of confusion with the purpose behind set 'sethit'
        return C;
    }

}
