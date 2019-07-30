package org.firefly.provider.leveldb;

import org.apache.commons.codec.binary.Hex;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LevelDbDemo {
    public static void main(String[] args) {
        DB db = null;
        try {
            db = JniDBFactory.factory.open(new File(".\\leveldb\\target\\MyLevelDb"), options());

            db.put(JniDBFactory.bytes("dbType"), JniDBFactory.bytes("LevelDb"));
            System.out.println("dbType: " + JniDBFactory.asString(db.get(JniDBFactory.bytes("dbType"))));

            WriteOptions writeOptions = new WriteOptions();
            writeOptions.sync(true);
            db.put(JniDBFactory.bytes("dbType"), JniDBFactory.bytes("LevelDb"), writeOptions);

            ReadOptions readOptions = new ReadOptions();
            readOptions.snapshot(db.getSnapshot());
            DBIterator iterator = db.iterator(readOptions);
            iterator.close();
            readOptions.snapshot().close();

            long[] sizes = db.getApproximateSizes(
                    new Range(JniDBFactory.factory.bytes("a"), JniDBFactory.factory.bytes("m")),
                    new Range(JniDBFactory.factory.bytes("p"), JniDBFactory.factory.bytes("x"))
            );
            System.out.println(Arrays.asList(sizes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Options options() {
        Options options = new Options();

        options.createIfMissing(true);
        options.comparator(new DBComparator() {
            @Override
            public String name() {
                return "hex comparator";
            }

            @Override
            public byte[] findShortestSeparator(byte[] start, byte[] limit) {
                return new byte[0];
            }

            @Override
            public byte[] findShortSuccessor(byte[] key) {
                return new byte[0];
            }

            @Override
            public int compare(byte[] o1, byte[] o2) {
                return Hex.encodeHexString(o1).compareTo(Hex.encodeHexString(o2));
            }
        });
        options.logger(message -> System.out.println("MyLevelDb: " + message));
        options.compressionType(CompressionType.NONE);
        options.cacheSize(100 * 1048576);

        return options;
    }
}
