/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author bruce.sailer
 */
public class RhythmBank extends ConcurrentHashMap<Integer, uMusicRhythm>{
    public Integer add(uMusicRhythm rhythm) {
        Set<Integer> keys = this.keySet();
        Integer key = keys.size();
        for (int i=0; i<keys.size(); i++) {
            if (!keys.contains(i)) {
                key = i;
                break;
            }
        }
        this.put(key, rhythm);
        return key;
    }
}
