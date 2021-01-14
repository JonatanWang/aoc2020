package Day11;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SeatingSystem extends Template {

    private Board b;

    public SeatingSystem(String fileName) {
        super(fileName);
        // Parse the map
        b = new Board(input);
    }

    @Override
     public void task1() {
        boolean isRunning = true;

        // Loop until stopping condition is met
        while(isRunning) {
            b = b.integrate();
            isRunning = b.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b.count('#'));
    }

    @Override
    public void task2() {
        b = new Board(input);
        b.findVisible();

        boolean isRunning = true;

        // Loop until stopping condition is met
        while(isRunning) {
            b = b.integrate2();
            isRunning = b.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b.count('#'));
    }

    private class Board {
        char[][] map;
        boolean mutate;
        int[][] dir = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        Set<Point> consider;
        private Map<Point, Map<Point, Point>> cache;

        public Board(String[] input) {
            map = new char[input.length][];
            for(int y = 0; y < input.length; y++) {
                map[y] = new char[input[y].length()];
                for(int x = 0; x < input[y].length(); x++) {
                    map[y][x] = input[y].charAt(x);
                }
            }
            mutate = false;
            cache = new HashMap<>();
            consider = new HashSet<>();
            init();
        }

        public Board(Board b) {
            map = new char[b.map.length][b.map[0].length];
            mutate = false;
            cache = b.cache;
            consider = b.consider;
        }

        private void init() {
            for(int y = 0; y < map.length; y++) {
                for(int x = 0; x < map[y].length; x++) {
                    if(map[y][x] != '.') {
                        consider.add(new Point(x, y));
                    }
                }
            }
        }

        public Board integrate() {
            Board b = new Board(this);
            for(Point p : consider) {
                char c = map[p.y][p.x];
                int neighborCount = checkNeighbors(p.x, p.y, '#');
                switch(c) {
                    case 'L':
                        if(neighborCount == 0) {
                            b.set(p.x, p.y, '#');
                            b.mutate |= true;
                        } else {
                            b.set(p.x, p.y, 'L');
                        }
                        break;
                    case '#':
                        if(neighborCount >= 4) {
                            b.set(p.x, p.y, 'L');
                            b.mutate |= true;
                        } else {
                            b.set(p.x, p.y, '#');
                        }
                }
            }
            return b;
        }

        public Board integrate2() {
            Board b = new Board(this);
            for(Point p : consider) {
                char c = map[p.y][p.x];
                int neighborCount = checkVisibleNeighbors(p.x, p.y, '#');
                switch(c) {
                    case 'L':
                        if(neighborCount == 0) {
                            b.set(p.x, p.y, '#');
                            b.mutate |= true;
                        } else {
                            b.set(p.x, p.y, 'L');
                        }
                        break;
                    case '#':
                        if(neighborCount >= 5) {
                            b.set(p.x, p.y, 'L');
                            b.mutate |= true;
                        } else {
                            b.set(p.x, p.y, '#');
                        }
                }
            }
            return b;
        }

        public boolean isMutated() {
            return mutate;
        }

        public void set(int x, int y, char c) {
            map[y][x] = c;
        }

        private int checkNeighbors(int x, int y, char c) {
            int count = 0;
            for(int i = 0; i < dir.length; i++) {
                int[] a = dir[i];
                if(safeCheck(x + a[0], y + a[1])) {
                    count += map[y + a[1]][x + a[0]] == c ? 1 : 0;
                }
            }
            return count;
        }

        private int checkVisibleNeighbors(int x, int y, char c) {
            int count = 0;
            Point key = new Point(x, y);
            // Doin' it dirty
            for(Point p : cache.get(key).values()) {
                count += map[p.y][p.x] == '#' ? 1 : 0;
                if(count == 5) {
                    return count;
                }
            }
            return count;
        }

        private int count(char c) {
            int count = 0;
            for(Point p : consider) {
                count += map[p.y][p.x] == c ? 1 : 0;
            }
            return count;
        }

        private boolean safeCheck(int x, int y) {
            return (y > -1 && y < map.length && x > -1 && x < map[y].length);
        }

        public void findVisible() {
            for(Point p : consider) {
                for(int[] i : dir) {
                    findFirstVisible(p.x, p.y, new Point(i[0], i[1]));
                }
            }
        }

        private void findFirstVisible(int x, int y, Point p) {
            Point point = new Point(x, y);
            if(!cache.containsKey(point)) {
                cache.put(point, new HashMap<>());
            }
            while(safeCheck(x += p.x, y += p.y)) {
                Point p1 = new Point(x, y);
                if(consider.contains(p1)) {
                    cache.get(point).put(p, p1);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        var fileName = "/home/zyw/dev/aoc/2020/src/Day11/input.txt";
        var seatingSystem = new SeatingSystem(fileName);
        seatingSystem.doTasks();
    }
}
