package DataObjects;

import java.util.Comparator;

/*  Data object to store Harmonic class data */
public class HarmonicClass 
{
	/* Harmonic Class Fields */
	private int ClassId;
	private double MinClassSize;
	private double MaxClassSize;

	/* Constructor for Harmonic Class */
	public HarmonicClass(int classId, double minClassSize, double maxClassSize) {
		super();
		ClassId = classId;
		MinClassSize = minClassSize;
		MaxClassSize = maxClassSize;
	}

	/* Getters and Setters for Harmonic Class Fields */
	public int getClassId() {
		return ClassId;
	}

	public double getMinClassSize() {
		return MinClassSize;
	}

	public double getMaxClassSize() {
		return MaxClassSize;
	}

	/* Comparator for sorting the Harmonic class list by class Id */
	public static Comparator<HarmonicClass> HarmonicClassIdIdComparator = new Comparator<HarmonicClass>() {

		public int compare(HarmonicClass class1, HarmonicClass class2) {
			return class1.getClassId() - class2.getClassId();
		}
	};
}
