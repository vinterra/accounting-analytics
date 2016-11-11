package org.gcube.accounting.analytics;

import java.util.List;

import org.gcube.accounting.datamodel.AggregatedUsageRecord;
/**
 * Object for calculate quota 
 * 
 * 
 * @author pieve
 *
 */
public class TotalFilters {
	protected Class<? extends AggregatedUsageRecord<?, ?>> clz;
	protected TemporalConstraint temporalConstraint;		
	protected List<Filters> totalFilters;
	

	protected String identifier;
	protected Double d;
	protected String orderingProperty;
	
	
	public TemporalConstraint getTemporalConstraint() {
		return temporalConstraint;
	}

	public void setTemporalConstraint(TemporalConstraint temporalConstraint) {
		this.temporalConstraint = temporalConstraint;
	}

	public Class<? extends AggregatedUsageRecord<?, ?>> getClz() {
		return clz;
	}

	public void setClz(Class<? extends AggregatedUsageRecord<?, ?>> clz) {
		this.clz = clz;
	}
	
	public TotalFilters(){}
	
	public TotalFilters(String identifier,Class<? extends AggregatedUsageRecord<?, ?>> clz,TemporalConstraint temporalConstraint,List<Filters> totalFilters){
		super();
		this.totalFilters=totalFilters;
		this.clz=clz;
		this.temporalConstraint=temporalConstraint;
		this.identifier=identifier;
		
	}
	
	public List<Filters> getTotalFilters() {
		return totalFilters;
	}

	public void setTotalFilters(List<Filters> totalFilters) {
		this.totalFilters = totalFilters;
	}

	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
		return "TotalFilters [clz=" + clz + ", temporalConstraint="
				+ temporalConstraint + ", totalFilters=" + totalFilters
				+ ", identifier=" + identifier + ", d=" + d
				+ ", orderingProperty=" + orderingProperty + "]";
	}

	
	
	
}
