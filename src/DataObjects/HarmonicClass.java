package DataObjects;

import java.util.Comparator;

public class HarmonicClass {
	
	/* Harmonic Class Fields */
	private int ClassId;
	private int MinClassSize;
	private int MaxClassSize;
	
	/* Constructor for Harmonic Class */
	public HarmonicClass(int classId, int minClassSize, int maxClassSize) {
		super();
		ClassId = classId;
		MinClassSize = minClassSize;
		MaxClassSize = maxClassSize;
	}
	
	/* Getters and Setters for Harmonic Class Fields */
	public int getClassId() {
		return ClassId;
	}

	public void setClassId(int classId) {
		ClassId = classId;
	}

	public int getMinClassSize() {
		return MinClassSize;
	}

	public void setMinClassSize(int minClassSize) {
		MinClassSize = minClassSize;
	}

	public int getMaxClassSize() {
		return MaxClassSize;
	}

	public void setMaxClassSize(int maxClassSize) {
		MaxClassSize = maxClassSize;
	}

	/* Comparator for sorting the harmonic class list by class Id */
	public static Comparator<HarmonicClass> HarmonicClassIdIdComparator = new Comparator<HarmonicClass>() {

		public int compare(HarmonicClass class1, HarmonicClass class2) {
			return class1.getClassId() - class2.getClassId();
		}
	};
}
