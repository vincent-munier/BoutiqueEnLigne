package fr.umlv.m2.jee.database.cassandra;

import java.util.HashMap;
import java.util.Map;

public class CassandraUtils {
		public static Map<String, Integer> getAllProduct(){
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put("B002BRZ9G0", 1);
			result.put("B000FQ9R4E", 1);
			result.put("B004PAGJNS", 1);
			result.put("B0065NP39E", 1);
			
			result.put("1401203817", 2);
			result.put("1401206425", 2);	
			return result;
		}
}
