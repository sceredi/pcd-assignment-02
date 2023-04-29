package pcd.assignment.tasks.executors.model.data.monitor;

import pcd.assignment.tasks.executors.model.data.FileInfo;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class LongestFilesQueueImpl implements LongestFilesQueue {
    private final int filesToKeep;
    private final Queue<FileInfo> queue = new PriorityQueue<>(Comparator.comparingLong(FileInfo::getLineCount));

    public LongestFilesQueueImpl(int filesToKeep) {
        this.filesToKeep = filesToKeep;
    }

    @Override
    public synchronized void put(FileInfo fileInfo) {
        unSyncPut(fileInfo);
    }

    private void unSyncPut(FileInfo fileInfo) {
        if (this.queue.size() < this.filesToKeep ||
                fileInfo.getLineCount() > Objects.requireNonNull(this.queue.peek()).getLineCount()) {
            this.queue.offer(fileInfo);
            if (this.queue.size() > this.filesToKeep) {
                this.queue.poll();
            }
        }
    }

    @Override
    public void putAll(LongestFilesQueue filesQueue) {
        filesQueue.get().forEach(this::unSyncPut);
    }

    @Override
    public int getFilesToKeep() {
        return filesToKeep;
    }

    @Override
    public Queue<FileInfo> get() {
        return this.queue;
    }
}
