package com.dmac.analytics.spark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.storage.StorageLevel;

public class SparkRDDParallelize {

	public static void main(String args[]) {

		SparkConf sparkConfig = new SparkConf()
						.setAppName("ReadDataFromArray")
						.setMaster("local[5]");
						
		
		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConfig);

		
		List<LatLong> locationList = new ListSource().retrieveList();
		
		
		// RDD can be calculated from an existing collection.
		JavaRDD<LatLong> locRDD = javaSparkContext.parallelize(locationList);

		locRDD.collect().forEach(z -> System.out.println(z.getId()));
		
		javaSparkContext.close();
	}
}






