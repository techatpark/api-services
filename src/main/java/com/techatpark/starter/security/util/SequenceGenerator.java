package com.techatpark.starter.security.util;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class SequenceGenerator {
    /**
     * s.
     */
    private static final int TOTAL_BITS = 64;
    /**
     * b.
     */
    private static final int EPOCH_BITS = 42;
    /**
     * cxx.
     */
    private static final int MACHINE_ID_BITS = 10;
    /**
     * f.
     */
    private static final int SEQUENCE_BITS = 12;
    /**
     * max.
     */
    private static final int MAXMACHINE_ID =
            (int) (Math.pow(2, MACHINE_ID_BITS) - 1);
    /**
     * maaa.
     */
    private static final int MAX_SEQUENCE =
            (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
    /**
     * oosos.
     */
    private static final long CUSTOM_EPOCH = 1420070400000L;
    /**
     * skskks.
     */
    private final int machineId;
    /**
     * nnn.
     */
    private long lastTimestamp = -1L;
    /**
     * jj.
     */
    private long sequence = 0L;

    /**
     * ss.
     *
     * @param machineIdq
     */
    // Create Snowflake with a machineId
    public SequenceGenerator(final int machineIdq) {
        if (machineIdq < 0 || machineIdq > MAXMACHINE_ID) {
            throw new IllegalArgumentException(String
                    .format(
                            "MachineId must be between %d and %d",
                            0, MAXMACHINE_ID));
        }
        this.machineId = machineIdq;
    }

    /**
     * ddd.
     */
    // Let Snowflake generate a machineId
    public SequenceGenerator() {
        this.machineId = createMachineId();
    }

    // Get current timestamp in milliseconds, adjust for the custom epoch.
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    /**
     * d.
     *
     * @return d.
     */
    public long nextId() {
        long currentTimestamp = timestamp();

        synchronized (this) {
            if (currentTimestamp < lastTimestamp) {
                throw new IllegalStateException("Invalid System Clock!");
            }

            if (currentTimestamp == lastTimestamp) {
                sequence = (sequence + 1) & MAX_SEQUENCE;
                if (sequence == 0) {
                    // Sequence Exhausted, wait till next millisecond.
                    currentTimestamp = waitNextMillis(currentTimestamp);
                }
            } else {
                // reset sequence for next millisecond
                sequence = 0;
            }

            lastTimestamp = currentTimestamp;
        }

        long id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
        id |= (machineId << (TOTAL_BITS - EPOCH_BITS - MACHINE_ID_BITS));
        id |= sequence;
        return id;
    }

    // Block and wait till next millisecond
    private long waitNextMillis(final long currentTimestamp) {
        long t = currentTimestamp;
        while (t == lastTimestamp) {
            t = timestamp();
        }
        return t;
    }

    private int createMachineId() {
        int machineIdq;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface =
                        networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                }
            }
            machineIdq = sb.toString().hashCode();
        } catch (Exception ex) {
            machineIdq = (new SecureRandom().nextInt());
        }
        machineIdq = machineIdq & MAXMACHINE_ID;
        return machineIdq;
    }
}
