{
   "_id": "_design/StorageUsageRecord",
   "language": "javascript",
   "views": {
       "dataVolume": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"StorageUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"dataVolume\" : doc.dataVolume ? doc.dataVolume : 0,\n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount :  1,\n\t\t\t\n\t\t};\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}\n\n",
           "reduce": "function(keys, values, rereduce){\n\tvar total = 0;\n\tvar dataVolume = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tdataVolume += values[i].dataVolume;\n\t}\n\t\n\treturn {\n\t\t\"operationCount\" : total,\n\t\t\"dataVolume\" : dataVolume\n\t};\n}"
       }
   }
}