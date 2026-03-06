package model.road;

import java.awt.Point;

public class RoadConnection {
  private RoadSegment from;
  private RoadSegment to;
  private Point[] firstConnectedPoints;
  private Point[] secondConnectedPoints;

  public RoadConnection(RoadSegment from, RoadSegment to, Point[] firstConnectedPoints, Point[] secondConnectedPoints) {
    this.from = from;
    this.to = to;
    this.firstConnectedPoints = firstConnectedPoints;
    this.secondConnectedPoints = secondConnectedPoints;
  }

  // Getters
  public RoadSegment getFrom() { return from; }
  public RoadSegment getTo() { return to; }
  public Point[] getConnectionPointFrom() { return firstConnectedPoints; }
  public Point[] getConnectionPointTo() { return secondConnectedPoints; }
}
