/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gcube.accounting.analytics.Filter;
import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.TemporalConstraint;
import org.gcube.accounting.datamodel.UsageRecord;
import org.gcube.documentstore.records.AggregatedRecord;
import org.gcube.documentstore.records.Record;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 * 
 */
public class AccountingPersistenceQuery {

	private static final AccountingPersistenceQuery accountingPersistenceQuery;

	private AccountingPersistenceQuery() {
	}

	static {
		accountingPersistenceQuery = new AccountingPersistenceQuery();
	}

	protected static synchronized AccountingPersistenceQuery getInstance() {
		return accountingPersistenceQuery;
	}

	public static SortedSet<String> getQuerableKeys(
			@SuppressWarnings("rawtypes") AggregatedRecord instance)
			throws Exception {
		SortedSet<String> properties = new TreeSet<>(
				instance.getRequiredFields());

		properties.removeAll(instance.getAggregatedFields());
		properties.removeAll(instance.getComputedFields());
		properties.remove(Record.ID);
		properties.remove(Record.CREATION_TIME);
		properties.remove(Record.RECORD_TYPE);
		properties.remove(UsageRecord.SCOPE);

		return properties;
	}

	public static SortedSet<String> getQuerableKeys(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass)
			throws Exception {
		AggregatedRecord<?,?> instance = aggregatedRecordClass.newInstance();
		return getQuerableKeys(instance);
	}

	public Map<Calendar, Info> getTimeSeries(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		return AccountingPersistenceBackendQueryFactory.getInstance()
				.getTimeSeries(aggregatedRecordClass, temporalConstraint, 
						filters);
	}

	public static SortedMap<Filter, Map<Calendar, Info>> getTopValues(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		return AccountingPersistenceBackendQueryFactory.getInstance()
				.getTopValues(aggregatedRecordClass, temporalConstraint,
						filters);
	}

	public static SortedSet<Filter> getNextPossibleValues(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		return AccountingPersistenceBackendQueryFactory.getInstance()
				.getNextPossibleValues(aggregatedRecordClass,
						temporalConstraint, filters);
	}

	/**
	 * Close the connection to persistence
	 * 
	 * @throws Exception
	 *             if the close fails
	 */
	public void close() throws Exception {
		AccountingPersistenceBackendQueryFactory.getInstance().close();
	}

}
