namespace HF1
{
    public enum Content
    {
        EMPTY,
        WALL,
        TREASURE,
        GHOST
    }
    public struct Direction
    {
        public int x;
        public int y;
    }

    public class Labirynth
    {
        private Content[,] map;

        public Labirynth(Content[,] map)
        {
            this.map = map;
        }

        public Content WhatIsThere(int x, int y, Direction dir)
        {
            if (!(0<=(x + dir.x) && map.GetLength(0)>(x + dir.x) && 0 <= (y + dir.y) && map.GetLength(1) > (y + dir.y) && (dir.x == 0 || dir.y == 0)))
                throw new Exception();

            return map[x+dir.x,y+dir.y];
        }

        public void Collect(int x, int y)
        {
            if (map[x, y] != Content.TREASURE)
                throw new Exception();
            map[x,y] = Content.EMPTY;
        }
    }
}
