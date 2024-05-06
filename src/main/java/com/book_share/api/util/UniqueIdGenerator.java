package com.book_share.api.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import java.util.UUID;

public final class UniqueIdGenerator {

    private static final NoArgGenerator V4_GEN = Generators.randomBasedGenerator();
    private static final NoArgGenerator V7_GEN = Generators.timeBasedEpochGenerator();

    private UniqueIdGenerator() {
        // Utility class, hence no instantiation
    }

    public static UUID v4() {
        return V4_GEN.generate();
    }

    public static UUID v7() {
        return V7_GEN.generate();
    }

}
