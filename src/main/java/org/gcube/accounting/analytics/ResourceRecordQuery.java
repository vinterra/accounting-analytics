/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.accounting.datamodel.SingleUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.ServiceUsageRecord;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public class ResourceRecordQuery {
	
	private static Logger logger = LoggerFactory.getLogger(ResourceRecordQuery.class);
	
	protected static Map<Class<? extends SingleUsageRecord>, Set<String>> resourceRecords = null;
	
	public static Map<Class<? extends SingleUsageRecord>, Set<String>> getResourceRecordsTypes() {
		if(resourceRecords==null){
			resourceRecords = new HashMap<Class<? extends SingleUsageRecord>, Set<String>>();
			Package usageRecordPackage = ServiceUsageRecord.class.getPackage();
			Reflections reflections = new Reflections(usageRecordPackage.getName());
			Set<Class<? extends SingleUsageRecord>> resourceRecordsTypes = reflections.getSubTypesOf(SingleUsageRecord.class);
			for(Class<? extends SingleUsageRecord> resourceRecordsType : resourceRecordsTypes){
				try {
					SingleUsageRecord singleUsageRecord = resourceRecordsType.newInstance();
					resourceRecords.put(resourceRecordsType, singleUsageRecord.getRequiredFields());
				} catch (InstantiationException | IllegalAccessException e) {
					logger.error(String.format("Unable to correctly istantiate %s", resourceRecordsType.getSimpleName()), e.getCause());
				}
			}
			
		}
		return resourceRecords;
	}
	
	
	protected AccountingPersistenceQuery accountingPersistenceQuery;
	
	protected ResourceRecordQuery(AccountingPersistenceQuery accountingPersistenceQuery){
		this.accountingPersistenceQuery = accountingPersistenceQuery;
	}
	
	public List<Info> getInfo(Class<? extends SingleUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		return accountingPersistenceQuery.query(usageRecordType, temporalConstraint, filters);
	}
	
	
	
}
