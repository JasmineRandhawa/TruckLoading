package Performance;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Constants;
import DataObjects.Item;
import DataObjects.Output;
import DataObjects.PerformanceComparison;
import DataObjects.Truck;

/*compute the performance based on results from offline and online algorithms for Truck Laoding */
public class ComputePerformance {

	// Compute Performance
	public static List<PerformanceComparison> GetResult(List<Item> inputItems,Output resultOPT_FFD, Output resultOnline_FirstFit,
			Output resultOnline_BestFit, Output resultOnline_HarmonicFit) {
		List<PerformanceComparison> comparisonResult = new ArrayList<PerformanceComparison>();
		int totalInputItemSize = GetTotalSizeOfInputItems(inputItems);
		int totalTruckcapacity = GetTotalSizeOfInputItems(inputItems);
		PerformanceComparison optPerf = ComputeResults(resultOPT_FFD,Constants.OPTAlgoName , 0 ,totalInputItemSize); 
		comparisonResult.add(optPerf);
		comparisonResult.add(ComputeResults(resultOnline_FirstFit,Constants.FirstFitAlgoName,optPerf.getTotalWastage(),totalInputItemSize));
		comparisonResult.add(ComputeResults(resultOnline_BestFit,Constants.BestFitAlgoName, optPerf.getTotalWastage(),totalInputItemSize));
		comparisonResult.add(ComputeResults(resultOnline_HarmonicFit,Constants.HarmonicFitAlgoName , optPerf.getTotalWastage(),totalInputItemSize));
		return comparisonResult;
	}

	/*Private Methods*/

	// compute algorithm results
	private static PerformanceComparison ComputeResults(Output result, String algorithmName , int optWastage ,int totalInputItemSize) {
	
		PerformanceComparison perf = new PerformanceComparison();
		if(result!=null)
		{
			perf.setAlgorthmName(algorithmName);
			List<Truck> openTrucks = result.getOpenTrucks();
			List<Truck> closedTrucks = result.getClosedTrucks();
			
			if(openTrucks!=null && openTrucks.size()>0)
				perf.setNoOfOpenTrucks(openTrucks.size());
			if(closedTrucks!=null && closedTrucks.size()>0)
			{
				int totalWastage = GetTotalWastage(closedTrucks);
				totalWastage = totalWastage == 0 ? 1 : totalWastage;
				perf.setTotalWastage(totalWastage);
				perf.setTotalInputSize(totalInputItemSize);
				int totalSizeOfDeliveredItems = GetTotalSizeOfDeliveredItems(closedTrucks);
				perf.setTotalSizeOfDeliveredItems(totalSizeOfDeliveredItems);
				perf.setNoOfClosedTrucks(closedTrucks.size());
				perf.setNoOfItemsDelivered(GetTotalNumberOfDeliveredItems(closedTrucks));
				perf.setNoOfOpenTrucks(openTrucks.size());
				perf.setTotalSizeOfExpeditedItems(GetTotalSizeOfExpeditedItems(closedTrucks));
				//if(optWastage > 0)
					//perf.setCompetitiveRatio(totalWastage/optWastage);
			}
		}
		return perf;
	}
	
	//calculate total size Of  DeliveredItems
	private static int GetTotalSizeOfDeliveredItems(List<Truck> closedTrucks) {
		int totalSize = 0;
		for(Truck truck:closedTrucks )
			for(Item item: truck.getTruckItems() )
				totalSize+= item.getItemSize();
		return totalSize;
	}
	
	//calculate total wastage in closed trucks
	private static int GetTotalWastage(List<Truck> closedTrucks) {
		int totalWastage = 0;
		for(Truck truck:closedTrucks )
			totalWastage+= truck.getRemainingCapacity();
		return totalWastage;
	}
	
	//calculate total number Of  DeliveredItems
	private static int GetTotalNumberOfDeliveredItems(List<Truck> closedTrucks) {
		int totalNumberOfDeliveredItems = 0;
		for(Truck truck:closedTrucks )
			for(Item item: truck.getTruckItems() )
				totalNumberOfDeliveredItems++;;
		return totalNumberOfDeliveredItems;
	}
	
	//calculate total size Of  ExpeditedItems
	private static int GetTotalSizeOfExpeditedItems(List<Truck> closedTrucks) {
		int totalNumberOfExpeditedItems = 0;
		for(Truck truck:closedTrucks )
			totalNumberOfExpeditedItems+= truck.getTotalSizeOfExpeditedItems();
		return totalNumberOfExpeditedItems;
	}
	
	//calculate total size Of  ExpeditedItems
	private static int GetTotalSizeOfInputItems(List<Item> inputItems) {
		int totalSizeOfInputItems = 0;
		for(Item item :inputItems )
			totalSizeOfInputItems+= item.getItemSize();
		return totalSizeOfInputItems;
	}
}
