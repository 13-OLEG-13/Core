/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.spleefleague.core.utils;

import net.spleefleague.core.io.DBEntity;
import net.spleefleague.core.io.DBLoad;
import net.spleefleague.core.io.DBLoadable;
import net.spleefleague.core.io.DBSave;
import net.spleefleague.core.io.DBSaveable;
import org.bukkit.Location;

/**
 *
 * @author Jonas
 */
public class Area extends DBEntity implements DBLoadable, DBSaveable {
    
    @DBLoad(fieldName = "low", typeConverter = TypeConverter.LocationConverter.class)
    @DBSave(fieldName = "low", typeConverter = TypeConverter.LocationConverter.class)
    private Location low;
    @DBLoad(fieldName = "low", typeConverter = TypeConverter.LocationConverter.class)
    @DBSave(fieldName = "low", typeConverter = TypeConverter.LocationConverter.class)
    private Location high;
    
    public Area() {
        
    }
    
    public Area(Location loc1, Location loc2) {
        setLocations(loc1, loc2);
    }
    
    //Private for now
    private void setLocations(Location loc1, Location loc2) {
        if(loc1.getWorld() != loc2.getWorld()) {
            throw new UnsupportedOperationException("Worlds have to be equal");
        }
        low = new Location(loc1.getWorld(), Math.min(loc1.getX(), loc2.getX()), Math.min(loc1.getY(), loc2.getY()), Math.min(loc1.getZ(), loc2.getZ()));
        high = new Location(loc1.getWorld(), Math.max(loc1.getX(), loc2.getX()), Math.max(loc1.getY(), loc2.getY()), Math.max(loc1.getZ(), loc2.getZ()));
    }
    
    public Location getHigh() {
        return high;
    }
    
    public Location getLow() {
        return low;
    }
    
    public boolean isInArea(Location loc) {
        if(loc.getWorld() == low.getWorld()) {
            if(loc.getX() >= low.getX() && loc.getX() <= high.getX()) {
                if(loc.getY() >= low.getY() && loc.getY() <= high.getY()) {
                    if(loc.getZ() >= low.getZ() && loc.getZ() <= high.getZ()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}