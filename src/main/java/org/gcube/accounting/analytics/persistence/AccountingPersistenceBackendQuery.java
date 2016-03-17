/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

import org.gcube.accounting.analytics.Filter;
import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.NumberedFilter;
import org.gcube.accounting.analytics.TemporalConstraint;
import org.gcube.documentstore.records.AggregatedRecord;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public abstract class AccountingPersistenceBackendQuery {

	public static final int KEY_VALUES_LIMIT = 25;

	protected abstract void prepareConnection(
			AccountingPersistenceBackendQueryConfiguration configuration)
			throws Exception;

	/**
	 * Query the persistence obtaining a Map where the date is the key and the
	 * #Info is the value. The result is relative to an Usage Record Type,
	 * respect a TemporalConstraint and can be applied one or more filters.
	 * 
	 * @param clz
	 *            the Record Class of interest
	 * @param temporalConstraint
	 *            the TemporalConstraint (interval and aggregation)
	 * @param filters
	 *            list of filter to obtain the time series. If null or empty
	 *            list get all data for the interested Record Class with the
	 *            applying temporal constraint. All Filter must have not null
	 *            and not empty key and value. The filters are must be related
	 *            to different keys and are in AND. If the list contains more
	 *            than one filter with the same key an Exception is thrown.
	 * @return the Map containing for each date in the required interval the
	 *         requested data
	 * @throws Exception
	 *             if fails
	 */
	public abstract SortedMap<Calendar, Info> getTimeSeries(
			Class<? extends AggregatedRecord<?,?>> clz,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception;

	/**
	 * Return a SortedMap containing the TimeSeries for top values for a certain
	 * key taking in account all Filters. The key is identified adding a Filter
	 * with a null value. Only one Filter with null value is allowed otherwise
	 * an Exception is thrown. The values are ordered from the most occurred
	 * value.
	 * 
	 * @param clz
	 *            the Usage Record Class of interest
	 * @param temporalConstraint
	 *            the TemporalConstraint (interval and aggregation)
	 * @param filters
	 *            list of filter to obtain the time series. If null or empty
	 *            list get all data for the interested Record Class with the
	 *            applying temporal constraint. All Filter (except one) must
	 *            have not null and not empty key and value. One Filter must
	 *            have not null and not empty key and a null value. The filters
	 *            are must be related to different keys and are in AND. If the
	 *            list contains more than one filter with the same key an
	 *            Exception is thrown. If the list contains more than one filter
	 *            with null value an Exception is thrown.
	 * @return a SortedMap
	 * @throws Exception
	 *             if fails
	 */
	public SortedMap<NumberedFilter, SortedMap<Calendar, Info>> getTopValues(
			Class<? extends AggregatedRecord<?,?>> clz,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		String orderingProperty = AccountingPersistenceQuery
				.getDefaultOrderingProperties(clz);
		return getTopValues(clz, temporalConstraint, filters,
				orderingProperty);
	}

	public abstract SortedMap<NumberedFilter, SortedMap<Calendar, Info>> getTopValues(
			Class<? extends AggregatedRecord<?,?>> clz,
			TemporalConstraint temporalConstraint, List<Filter> filters,
			String orderingProperty) throws Exception;

	/**
	 * Return the list of possible values for a key for a certain usageRecord
	 * taking in account all Filters. The value for a certain key is identified
	 * adding a Filter with a null value. Only one Filter with null value is
	 * allowed otherwise an Exception is thrown. The values are ordered from the
	 * most occurred value.
	 * 
	 * @param clz
	 *            the Usage Record Class of interest
	 * @param temporalConstraint
	 *            the TemporalConstraint (interval and aggregation)
	 * @param filters
	 *            list of filter to obtain the time series. If null or empty
	 *            list get all data for the interested Record Class with the
	 *            applying temporal constraint. All Filter (except one) must
	 *            have not null and not empty key and value. One Filter must
	 *            have not null and not empty key and a null value. The filters
	 *            are must be related to different keys and are in AND. If the
	 *            list contains more than one filter with the same key an
	 *            Exception is thrown. If the list contains more than one filter
	 *            with null value an Exception is thrown.
	 * @return a SortedSet containing the list of possible values
	 * @throws Exception
	 *             if fails
	 */
	public SortedSet<NumberedFilter> getNextPossibleValues(
			Class<? extends AggregatedRecord<?,?>> clz,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		String orderingProperty = AccountingPersistenceQuery
				.getDefaultOrderingProperties(clz);
		return getNextPossibleValues(clz, temporalConstraint,
				filters, orderingProperty);
	}

	public abstract SortedSet<NumberedFilter> getNextPossibleValues(
			Class<? extends AggregatedRecord<?, ?>> clz,
			TemporalConstraint temporalConstraint, List<Filter> filters,
			String orderingProperty) throws Exception;

	/**
	 * Close the connection to persistence
	 * 
	 * @throws Exception
	 *             if the close fails
	 */
	public abstract void close() throws Exception;

}
