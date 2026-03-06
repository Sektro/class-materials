package model.road;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.GameConstants;

public class RoadNetwork {
    private Set<RoadSegment> segments;
    private RoadSegment entrance; 
    private RoadSegment exit;
    private RoadSegment lastAddedRoadSegment;

    public RoadNetwork() {
        this.segments = new HashSet<>();

        generateEntranceAndExit();
    }

    private void generateEntranceAndExit() {
      Random random = new Random();
      int entranceEdge = random.nextInt(4);
      int exitEdge;

      do {
          exitEdge = random.nextInt(4);
      } while (exitEdge == entranceEdge);
      
      entrance = new RoadSegment(generateEntranceAndExitSegments(entranceEdge, random));
      exit = new RoadSegment(generateEntranceAndExitSegments(exitEdge, random));

      segments.add(entrance);
      segments.add(exit);
    }

    private Point[] generateEntranceAndExitSegments(int edge, Random random) {
      Point[] road = new Point[4];

      int maxRow = GameConstants.MAX_SCREEN_ROW;
      int maxCol = GameConstants.MAX_SCREEN_COLUMN;

      int edge_buffer = 10;

      int x, y;
        
      switch (edge) {
        //Always build 2x2 grid from top left corner of the tile
        case 0: 
            x = edge_buffer + random.nextInt(maxCol - 2 * edge_buffer);
            y = 0; // Top

            road[0] = new Point(x, y); // Top left
            road[1] = new Point(x + 1, y); // Top right 
            road[2] = new Point(x, y + 1); // Bottom left
            road[3] = new Point(x + 1, y + 1); // Bottom right
            break;
            
        case 1: 
            x = maxCol - 1;
            y = edge_buffer + random.nextInt(maxRow - 2 * edge_buffer); // Right

            road[0] = new Point(x - 1, y);
            road[1] = new Point(x, y);
            road[2] = new Point(x - 1, y + 1);
            road[3] = new Point(x, y + 1);
            break;
            
        case 2: 
            x = edge_buffer + random.nextInt(maxCol - 2 * edge_buffer);
            y = maxRow - 1; // Bottom

            road[0] = new Point(x, y - 1);
            road[1] = new Point(x + 1, y - 1);
            road[2] = new Point(x, y);
            road[3] = new Point(x + 1, y);
            break;
            
        case 3: 
            x = 0;
            y = edge_buffer + random.nextInt(maxRow - 2 * edge_buffer); // Left

            road[0] = new Point(x, y);
            road[1] = new Point(x + 1, y);
            road[2] = new Point(x, y + 1);
            road[3] = new Point(x + 1, y + 1);
            break;
            
        default: 
            x = 0;
            y = 0;

            road[0] = new Point(x, y);
            road[1] = new Point(x + 1, y);
            road[2] = new Point(x, y + 1);
            road[3] = new Point(x + 1, y + 1);
            break;
      }
      
      return road;
    }
        
    public boolean buildRoad(int worldX, int worldY) {
        //Convert world coordinates to tile coordinates
        int tileX = worldX / GameConstants.TILE_SIZE;
        int tileY = worldY / GameConstants.TILE_SIZE;

        //Create road
        Point[] road = new Point[4];
        road[0] = new Point(tileX, tileY);
        road[1] = new Point(tileX + 1, tileY);
        road[2] = new Point(tileX, tileY + 1);
        road[3] = new Point(tileX + 1, tileY + 1);

        //Enterance or road connection
        ArrayList<Point[]> connections = isConnectedCorrectly(road, entrance.getRoad());
        if(!connections.isEmpty()) {
            //Create bidirectional connection
            RoadSegment newRoad = new RoadSegment(road);

            RoadConnection entranceToNewSegment = new RoadConnection(entrance, newRoad, connections.get(0), connections.get(1));
            RoadConnection newSegmentToEntrance = new RoadConnection(newRoad, entrance, connections.get(1), connections.get(0));

            entrance.addConnection(entranceToNewSegment);
            newRoad.addConnection(newSegmentToEntrance);

            segments.add(newRoad);
            lastAddedRoadSegment = newRoad;

            return true;
        } else{
            connections.clear();

            Set<RoadSegment> segmentsCopy = new HashSet<>(segments);
            
            for(RoadSegment segment : segmentsCopy) {
                Point[] segmentRoad = segment.getRoad();
                connections = isConnectedCorrectly(road, segmentRoad);
                if(!connections.isEmpty()) {
                    RoadSegment newRoad = new RoadSegment(road);
    
                    RoadConnection segmentToNewSegment = new RoadConnection(segment, newRoad, connections.get(0), connections.get(1));
                    RoadConnection newSegmentToSegment = new RoadConnection(newRoad, segment, connections.get(1), connections.get(0));
    
                    segment.addConnection(segmentToNewSegment);
                    newRoad.addConnection(newSegmentToSegment);
    
                    segments.add(newRoad);
                    lastAddedRoadSegment = newRoad;

                    return true;
                }
            }
        }

        return false; // No valid connection found
    }
    private ArrayList<Point[]> isConnectedCorrectly(Point[] source, Point[] target) {
        ArrayList<Point[]> connections = new ArrayList<>(); // Store connections
        
        for (Point sourcePoint : source) {
            for (Point targetPoint : target) {
                if (isConnected(sourcePoint, targetPoint)) {
                    // Create a new connection point pair
                    Point[] connection = new Point[2];
                    connection[0] = new Point(sourcePoint); // Copy to avoid reference issues
                    connection[1] = new Point(targetPoint);
                    connections.add(connection);
                    
                    // We need exactly 2 connections for a valid road connection
                    if (connections.size() == 2) {
                        return connections;
                    }
                }
            }
        }
        
        // If we don't have exactly 2 connections, return empty list (invalid)
        if (connections.size() != 2) {
            connections.clear();
        }
        
        return connections;
    }

    private boolean isConnected(Point pointSource, Point pointTarget) {
        // Check if the two points are adjacent (horizontally or vertically)
        return (Math.abs(pointSource.x - pointTarget.x) == 1 && pointSource.y == pointTarget.y) ||
               (pointSource.x == pointTarget.x && Math.abs(pointSource.y - pointTarget.y) == 1);
    }

    public Set<RoadSegment> getSegments() {
        return segments;
    }

    public RoadSegment getEntrance() {
        return entrance;
    }

    public RoadSegment getExit() {
        return exit;
    }

    public RoadSegment getLastAddedRoadSegment() {
        return lastAddedRoadSegment;
    }
}
