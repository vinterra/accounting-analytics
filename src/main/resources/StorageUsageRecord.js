{
   "_id": "_design/StorageUsageRecord",
   "language": "javascript",
   "views": {
       "dataVolume": {
           "map": "function(doc) {\n\tif(doc.usageRecordType == \"StorageUsageRecord\"){\n\t\temit(myKey(doc.creationTime), doc.dataVolume);\n\t}\n}\n\n\nfunction myKey(timestamp){\n\tvar date = new Date(timestamp);\n\tvar key = [];\n\tkey.push(date.getFullYear());\n\tkey.push(date.getUTCMonth());\n\tkey.push(date.getUTCDay());\n\tkey.push(date.getUTCHours());\n\tkey.push(date.getUTCMinutes());\n\tkey.push(date.getUTCSeconds());\n\tkey.push(date.getUTCMilliseconds());\n\treturn key;\n}\n\n",
           "reduce": "function(keys, values){\n\treturn sum(values);\n}"
       }
   }
}