package org.gcube.accounting.analytics;

import java.util.List;

public class TotalFilters {
	protected List<Filters> totalFilters;
	protected Double d;
	protected String orderingProperty;
	public TotalFilters(){}

	public TotalFilters(List<Filters> totalFilters, Number n, String orderingProperty) {
		super();
		this.totalFilters=totalFilters;
		this.d = n.doubleValue();
		this.orderingProperty = orderingProperty;
	}

	

	public List<Filters> getTotalFilters() {
		return totalFilters;
	}

	public void setTotalFilters(List<Filters> totalFilters) {
		this.totalFilters = totalFilters;
	}

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}

	public String getOrderingProperty() {
		return orderingProperty;
	}

	public void setOrderingProperty(String orderingProperty) {
		this.orderingProperty = orderingProperty;
	}
	@Override
	public String toString() {
		return "TotalFilters [totalFilters=" + totalFilters + ", d=" + d
				+ ", orderingProperty=" + orderingProperty + "]";
	}
}
