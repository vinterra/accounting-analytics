/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.HashSet;
import java.util.Set;

import org.gcube.accounting.datamodel.BasicUsageRecord;
import org.gcube.accounting.datamodel.SingleUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.JobUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.PortletUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.ServiceUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.StorageUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.TaskUsageRecord;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public class ResourceRecordQueryTest {

	public class TestUsageRecord extends BasicUsageRecord implements SingleUsageRecord {

		/**
		 * Generated Serial Version UID
		 */
		private static final long serialVersionUID = 1939161386352514727L;
		
	}
	
	public static Set<Class<? extends SingleUsageRecord>> getExpectedResourceRecordsTypes(){
		Set<Class<? extends SingleUsageRecord>> expected = new HashSet<Class<? extends SingleUsageRecord>>();
		expected.add(ServiceUsageRecord.class);
		expected.add(StorageUsageRecord.class);
		expected.add(JobUsageRecord.class);
		expected.add(TaskUsageRecord.class);
		expected.add(PortletUsageRecord.class);
		return expected;
	}
	
	@Test
	public void testGetResourceRecordsTypes(){
		Set<Class<? extends SingleUsageRecord>> expected = getExpectedResourceRecordsTypes();
		Set<Class<? extends SingleUsageRecord>> found = ResourceRecordQuery.getResourceRecordsTypes().keySet();
		Assert.assertTrue(expected.containsAll(found));
		Assert.assertTrue(found.containsAll(expected));
	}
	
	
	@Test
	public void testGetResourceRecordsTypesWithFakeClass(){
		Set<Class<? extends SingleUsageRecord>> expected = getExpectedResourceRecordsTypes();
		expected.add(TestUsageRecord.class);
		Set<Class<? extends SingleUsageRecord>> found = ResourceRecordQuery.getResourceRecordsTypes().keySet();
		Assert.assertTrue(expected.containsAll(found));
		Assert.assertFalse(found.containsAll(expected));
	}
	
	
	
	
}
