package Util;

public class Util {
	public static double random(double low, double high) {
		return low + Math.random() * (high - low);
	}
	
	public static float random(float low, float high) {
		return low + (float) Math.random() * (high - low);
	}
	
	public static float random(float high) {
		return (float) Math.random() * high;
	}

	public static double radians(double angle){
		return angle/180*Math.PI;		
	}
	
	public static float radians(float angle){
		return angle/180*(float)Math.PI;		
	}
	
	
}
