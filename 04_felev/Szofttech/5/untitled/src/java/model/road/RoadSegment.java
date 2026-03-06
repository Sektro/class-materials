package model.road;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class RoadSegment {
    private Point[] road; // 4 points representing the road segment (2x2)
    private Set<RoadConnection> connections; // links to other segments

    public RoadSegment(Point[] road) {
        this.road = road;
        this.connections = new HashSet<>();
    }

    public void addConnection(RoadConnection connection) {
       if(connection != null) {
           connections.add(connection);
       }
    }

    public Set<RoadConnection> getConnections() {
        return connections;
    }
    
    public Point[] getRoad() {
        return road;
    }
}
