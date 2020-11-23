package agh.cs.lab2;

import agh.cs.lab2.IMapElement;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    Comparator YCmp=new YComparator();
    Comparator XCmp=new XComparator();
    SortedSet<IMapElement> ySorted=new TreeSet<IMapElement>(YCmp);
    SortedSet<IMapElement> xSorted=new TreeSet<IMapElement>(XCmp);
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        return;
    }
}
