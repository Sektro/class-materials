package model.animal;

import model.*;

import java.util.PriorityQueue;
import java.util.*;

public class PathFinder {
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        Node parent;
        double gCost;
        double hCost;
        double fCost;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.fCost, other.fCost);
        }
    }

    public static List<int[]> findPath(int startX, int startY, int targetX, int targetY, Tile[][] map) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();

        Node startNode = new Node(startX, startY);
        Node targetNode = new Node(targetX, targetY);

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            if (currentNode.x == targetNode.x && currentNode.y == targetNode.y) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode.x + "," + currentNode.y);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int neighborX = currentNode.x + i;
                    int neighborY = currentNode.y + j;

                    if (neighborX < 0 ||neighborY < 0 || neighborX >= map.length || neighborY >= map[0].length || map[neighborX][neighborY].getLand() == LandTypes.WATER || closedSet.contains(neighborX + "," + neighborY)) {
                        continue;
                    }

                    Node neighbor = new Node(neighborX, neighborY);
                    neighbor.parent = currentNode;
                    neighbor.gCost = currentNode.gCost + 1;
                    neighbor.hCost = calculateHeuristic(neighbor, targetNode);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;

                    openSet.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<int[]> reconstructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(new int[]{node.x, node.y});
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static double calculateHeuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
