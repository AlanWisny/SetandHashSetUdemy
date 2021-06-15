package com.company;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyTypes {
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodytype) {
        this.key = new Key(name, bodytype);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }


    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Key getKey() {
        return key;
    }

    public boolean addSatellite(HeavenlyBody moon) {
        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }


    @Override
    public final int hashCode() {
//        System.out.println("hashcode called");
        return this.key.hashCode();
    }

    // Why it is unnecessary to write the equals method to compare a subclass of itself?
    // Because HeavenlyBody class is declared final, so it cannot be subclasses

    // To ensure the equals method returns the right value i.e., Labrador is an instance of Dog but Dog is not an instance of Labdrador, but the name is the same

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HeavenlyBody) {
            HeavenlyBody theObject = (HeavenlyBody) obj;
            return this.equals(theObject.getKey());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.key.name + ": " + this.key.bodytype + ", " + this.orbitalPeriod;
    }

    public static Key makeKey(String name, BodyTypes type) {
        return new Key(name, type);
    }

    public static final class Key {
        private String name;
        private BodyTypes bodytype;

        public Key(String name, BodyTypes bodytype) {
            this.name = name;
            this.bodytype = bodytype;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodytype() {
            return bodytype;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodytype.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            if(this.name.equals(key.getName())) {
                return(this.bodytype == key.getBodytype());
            }
            return false;
        }
    }
}
