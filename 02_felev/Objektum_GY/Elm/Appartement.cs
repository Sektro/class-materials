namespace Elm
{
    public class Appartement
    {
        private int floor;
        private int door;
        private int area;
        private int comfort;
        private House house;
        private List<Owner> owners;

        public Appartement(int floor, int door, int area, int comfort, House house)
        {
            this.floor = floor;
            this.door = door;
            this.area = area;
            this.comfort = comfort;
            this.house = house;
            owners = new List<Owner>();
        }

        public int getArea() { return area; }
        public int getComfort() { return comfort; }
    }
}
