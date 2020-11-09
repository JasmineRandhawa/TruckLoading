package Performance;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Output;
import DataObjects.PerformanceComparison;

/*compute the performance based on results from offline and online algorithms for Truck Laoding */
public class ComputePerformance {

	// Compute Performance
	public static List<PerformanceComparison> GetResult(Output resultOPT_FFD, Output resultOnline_FirstFit,
			Output resultOnline_BestFit, Output resultOnline_HarmonicFit) {
		List<PerformanceComparison> comparisonResult = new ArrayList<PerformanceComparison>();
		return comparisonResult;

	}
}
