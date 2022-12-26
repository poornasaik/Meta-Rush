package com.metarush.logictests;
public class PaintJob {
    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        } else {
            double areaOfRoom = width * height;
            double bucketCount = Math.ceil(areaOfRoom / areaPerBucket);
            int relevantBucketCount = (int) (bucketCount - extraBuckets);
            return relevantBucketCount;
        }
    }

    public static int getBucketCount(double width, double height, double areaPerBucket) {
        int relevantBucketCount = getBucketCount(width, height, areaPerBucket, 0);
        return relevantBucketCount;

        /*if (width <= 0 || height <= 0 || areaPerBucket <= 0 ) {
            return -1;
        } else {
            double areaOfRoom = width * height;
            double bucketCount = Math.ceil(areaOfRoom / areaPerBucket);
            return (int) bucketCount;
        }*/
    }

    public static int getBucketCount(double areaOfRoom, double areaPerBucket) {
        int relevantBucketCount = getBucketCount(1, areaOfRoom, areaPerBucket, 0);
        return relevantBucketCount;
        /*if(areaOfRoom <= 0 || areaPerBucket <= 0)
        {
            return -1;
        }
        else {
            double bucketCount = Math.ceil(areaOfRoom / areaPerBucket);
            return (int) bucketCount;
        }*/
    }

}