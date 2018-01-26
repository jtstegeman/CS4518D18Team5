package com.bignerdranch.android.criminalintent.database;

public class CrimeDbSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String FACE = "fDetect";
        }
    }
    public static final class CrimeImageTable {
        public static final String NAME = "imgs";

        public static final class Cols {
            public static final String CRIME_UUID = "uuid";
            public static final String IMG_DATA = "img";
            public static final String IMG_WIDTH = "w";
            public static final String IMG_HEIGHT = "h";
            public static final String IMG_CONFIG = "c";
        }
    }
}
