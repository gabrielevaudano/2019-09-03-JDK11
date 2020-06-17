package it.polito.tdp.food.model;

public class Arco {
	private String portUno;
	private String portDue;
	private int weight;
	
	public Arco(String portUno, String portDue, int weight) {
		super();
		this.portUno = portUno;
		this.portDue = portDue;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPortUno() {
		return portUno;
	}

	public String getPortDue() {
		return portDue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((portDue == null) ? 0 : portDue.hashCode());
		result = prime * result + ((portUno == null) ? 0 : portUno.hashCode());
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
		Arco other = (Arco) obj;
		if (portDue == null) {
			if (other.portDue != null)
				return false;
		} else if (!portDue.equals(other.portDue))
			return false;
		if (portUno == null) {
			if (other.portUno != null)
				return false;
		} else if (!portUno.equals(other.portUno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arco [portUno=" + portUno + ", portDue=" + portDue + ", weight=" + weight + "]";
	}
	
	
}
