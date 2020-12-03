package DataObjects;

import java.util.List;

import Utility.Common;

/*  Data object to store Algorithm output and performance results */
public class Output 
{
	/* Output Class Fields */
	private List<Truck> ClosedTrucks;
	private double TotalProfit;
	private double TotalWaste;
	private double WasteMinusProfit;
	private double WasteProfitRatio;
	private String AlgorthmName;
	private String AlgorithmApproach;

	/* Constructor for Output Class */
	public Output(List<Truck> closedTrucks, double totalProfit, 
			      double totalWaste,
			      String algorthmName,
			      String algorithmApproach) 
	{
		super();
		ClosedTrucks = closedTrucks;
		TotalProfit = Common.RoundDecimal(totalProfit);
		TotalWaste = Common.RoundDecimal(totalWaste);
		WasteMinusProfit =  Common.RoundDecimal(totalWaste - totalProfit) ;
		WasteProfitRatio = Common.RoundDecimal(totalWaste/totalProfit); 
		AlgorthmName = algorthmName;
		AlgorithmApproach = algorithmApproach;
	}

	/* Getters and Setters for Output Class Fields */
	public List<Truck> getClosedTrucks() {
		return ClosedTrucks;
	}

	public double getTotalProfit() {
		return TotalProfit;
	}

	public double getTotalWaste() {
		return TotalWaste;
	}
	
	public double getWasteMinusProfit() {
		return WasteMinusProfit;
	}
	
	public double getWasteProfitRatio() {
		return WasteProfitRatio;
	}

	public String getAlgorthmName() {
		return AlgorthmName;
	}

	public String getAlgorithmApproach() {
		return AlgorithmApproach;
	}
}
