/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bruce.sailer
 */
abstract class TrackEditorController {

    private TrackNumber trackNumber;
    private TrackRecordController trackRecord;

    public TrackNumber getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(TrackNumber trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setTrackRecord(TrackRecordController trackRecord) {
        this.trackRecord = trackRecord;
    }

    public TrackRecordController getTrackRecord() {
        return this.trackRecord;
    }
    
    public abstract TrackEditorController refreshEditor();
}
