#!/bin/bash
  for i in {1..10}
 do
    curl -d '{"name": "Gatot Kaca","abilities": [{ "name": "Bones of Steel" },{ "name": "Muscles of Iron"},{ "name": "Fly" }]}' -H "Content-Type: application/json" -X POST http://localhost:8080/hero        
 done