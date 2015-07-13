{
   "_id": "_design/ServiceUsageRecord",
   "language": "javascript",
   "views": {
       "all": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "serviceClass": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.serviceClass);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "serviceName": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.serviceClass);\n\t\tdataKey.unshift(doc.serviceName);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "scope": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.scope);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "consumerId": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.consumerId);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "host": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.host);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "callerHost": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.callerHost);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "mixed": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.calledMethod);\n\t\tdataKey.unshift(doc.serviceName);\n\t\tdataKey.unshift(doc.serviceClass);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       },
       "calledMethod": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"ServiceUsageRecord\"){\n\t\tvar data = {\n\t\t\t\"duration\" : doc.duration, \n\t\t\t\"operationCount\" : doc.operationCount ? doc.operationCount : 1,\n\t\t\t\"maxInvocationTime\" : doc.maxInvocationTime ? doc.maxInvocationTime : doc.duration, \n\t\t\t\"minInvocationTime\" : doc.minInvocationTime ? doc.minInvocationTime : doc.duration\n\t\t}\n\t\tvar dataKey = getDataKey(doc.creationTime);\n\t\tdataKey.unshift(doc.calledMethod);\n\t\temit(dataKey, data);\n\t}\n}\n\n\nfunction getDataKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth()+1);\n\tkey.push(date.getUTCDate());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}",
           "reduce": "function(keys, values, rereduce){\n\tvar maxInvocationTime = values[0].maxInvocationTime;\n\tvar minInvocationTime = values[0].minInvocationTime;\n\tvar total = 0;\n\tvar numerator = 0;\n\tfor(i=0; i<values.length; i++){\n\t\ttotal += values[i].operationCount;\n\t\tnumerator += values[i].operationCount * values[i].duration;\n\t\tif(maxInvocationTime<=values[i].maxInvocationTime){\n\t\t\tmaxInvocationTime = values[i].maxInvocationTime;\n\t\t}\n\t\tif(minInvocationTime>=values[i].minInvocationTime){\n\t\t\tminInvocationTime = values[i].minInvocationTime;\n\t\t}\n\t}\n\t\n\treturn {\n\t\t\"duration\" : numerator/total,\n\t\t\"operationCount\" : total,\n\t\t\"maxInvocationTime\" : maxInvocationTime,\n\t\t\"minInvocationTime\" : minInvocationTime\n\t};\n}"
       }
   }
}