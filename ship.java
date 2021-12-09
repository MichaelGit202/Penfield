package Michael.Penfield;

public class ship {

    private int damage;
    private char dspChar;
    private int origin[] = new int[2];
    private ship.direction ShipDirection;
    private ship.shipType Model;
    private int length;

    public int getLength() {
        return length;
    }

    public boolean addDamage(){
        this.damage += 1;
        return checkDead();
    }

    public boolean checkDead(){
        if (this.damage == this.length){return true;}
        return false;
    }


    ship(shipType type){
        this.ShipDirection = direction.east;
        this.Model = type;
        int[] coord = new int[2];
        coord[0] = 0;
        coord[1] = 0;

        setOrigin(coord);
        if (Model != null){
            switch (Model) {
                case carrier:
                    this.dspChar = 'K';
                    this.length = 5;
                    break;
                case destroyer:
                    this.dspChar = 'D';
                    this.length = 2;
                    break;
                case cruiser:
                    this.dspChar = 'C';
                    this.length = 3;
                    break;
                case submarine:
                    this.dspChar = 'S';
                    this.length = 4;
                    break;
                case battleship:
                    this.dspChar = 'B';
                    this.length = 4;
                    break;
         }
        }
    }

    public shipType getModel() {
        return Model;
    }

    public direction getShipDirection() {
        return ShipDirection;
    }

    public void setShipDirection(direction shipDirection) {
        ShipDirection = shipDirection;
    }
    public int[] getOrigin() {
        return origin;
    }

    public void setOrigin(int[] origin) {
        this.origin = origin;
    }


    public char getDspChar() {
        return dspChar;
    }


    public static enum direction{
        North,
        south,
        east,
        west
    }




    public static enum shipType{
        carrier,
        destroyer,
        cruiser,
        submarine,
        battleship,
    }


}
