package com.patrickdurke.trackremindtodo.ui.track.track_area;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TrackingAreaRecordRepository {

    private List<TrackingAreaRecord> trackingAreaRecordList;
    private MutableLiveData<List<TrackingAreaRecord>> trackingAreaRecordListLiveData;

    public TrackingAreaRecordRepository() {
        trackingAreaRecordList = new ArrayList<>();
        this.trackingAreaRecordListLiveData = new MutableLiveData<>();
        //DummyData
        trackingAreaRecordList.add(new TrackingAreaRecord(1, "2021-03-22-09-17-09", 1));
        trackingAreaRecordList.add(new TrackingAreaRecord(1, "2021-03-25-18-17-44", 1));
        trackingAreaRecordList.add(new TrackingAreaRecord(1, "2021-03-26-14-17-11", 1));
        trackingAreaRecordList.add(new TrackingAreaRecord(1, "2021-03-29-15-16-12", 1));
        trackingAreaRecordList.add(new TrackingAreaRecord(1, "2021-03-29-18-17-36", 1));

        trackingAreaRecordListLiveData.setValue(trackingAreaRecordList);
    }

    public MutableLiveData<List<TrackingAreaRecord>> getTrackingAreaRecordListLiveData(int selectedItemId) {
        List<TrackingAreaRecord> sortedTrackingAreaRecordList = new ArrayList<>();

        for (TrackingAreaRecord record: trackingAreaRecordList)
            if (record.getId() == selectedItemId) sortedTrackingAreaRecordList.add(record);

        trackingAreaRecordListLiveData.setValue(sortedTrackingAreaRecordList);

        return trackingAreaRecordListLiveData;
    }
}
