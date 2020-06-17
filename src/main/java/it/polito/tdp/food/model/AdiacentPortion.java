package it.polito.tdp.food.model;

public class AdiacentPortion {
	String portionName;
	Double weight;
	
	public AdiacentPortion(String portionName, Double weight) {
		super();
		this.portionName = portionName;
		this.weight = weight;
	}

	public String getPortionName() {
		return portionName;
	}

	public Double getWeight() {
		return weight;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((portionName == null) ? 0 : portionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdiacentPortion other = (AdiacentPortion) obj;
		if (portionName == null) {
			if (other.portionName != null)
				return false;
		} else if (!portionName.equals(other.portionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdiacentPortion [portionName=" + portionName + ", weight=" + weight + "]";
	}
	
	
}
