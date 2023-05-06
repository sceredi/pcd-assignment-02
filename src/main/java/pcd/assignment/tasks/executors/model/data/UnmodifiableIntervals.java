package pcd.assignment.tasks.executors.model.data;

import pcd.assignment.tasks.executors.model.data.monitor.UnmodifiableCounter;
import pcd.assignment.utilities.Pair;

import java.util.Map;

/**
 * Represents an {@link Intervals} that cannot be modified
 */
public interface UnmodifiableIntervals {
    Map<Pair<Integer, Integer>, UnmodifiableCounter> get();

}
