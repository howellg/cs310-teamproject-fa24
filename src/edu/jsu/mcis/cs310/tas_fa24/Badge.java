package edu.jsu.mcis.cs310.tas_fa24;

import java.util.zip.CRC32;

public class Badge {

    private final String id, description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Badge(String description) {
        this.description = description;
        
        CRC32 crc = new CRC32();
        crc.update(description.getBytes());
        long checksum = crc.getValue();

        //sets it to 8hex
        this.id = String.format("%08X", checksum);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();

    }

}
